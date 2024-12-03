#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

int main(int argc, char *argv[]) {
    // Ensure the user provides the number of child processes
    if (argc != 2) {
        fprintf(stderr, "Usage: %s <number_of_child_processes>\n", argv[0]);
        return EXIT_FAILURE;
    }

    int childnum = atoi(argv[1]); // Convert argument to an integer
    if (childnum <= 0) {
        fprintf(stderr, "Error: Number of child processes must be a positive integer.\n");
        return EXIT_FAILURE;
    }

    for (int i = 0; i < childnum; i++) {
        pid_t pid = fork();

        if (pid < 0) {
            perror("Fork failed");
            return EXIT_FAILURE;
        }

        if (pid == 0) { // Child process
            printf("Child %d created with PID: %d\n", i + 1, getpid());
            exit(EXIT_SUCCESS); // Child exits to avoid continuing the loop
        } else { // Parent process
            printf("Parent process created child %d with PID: %d\n", i + 1, pid);
        }
    }

    // Parent waits for all children to complete
    for (int i = 0; i < childnum; i++) {
        int status;
        pid_t child_pid = wait(&status);
        if (child_pid > 0) {
            if (WIFEXITED(status)) {
                printf("Child with PID: %d exited with status: %d\n", child_pid, WEXITSTATUS(status));
            } else {
                printf("Child with PID: %d did not exit successfully.\n", child_pid);
            }
        }
    }

    return EXIT_SUCCESS;
}

