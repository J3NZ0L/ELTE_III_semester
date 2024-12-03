#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <sys/shm.h>
#include <time.h>

// Function to calculate the remainder for gifts or punishment
int iterate_over_children(int childnum) {
    int remainder = 0;
    for (int i = childnum; i > 0; i--) {
        if (rand() % 5 == 4) {
            remainder++;
        }
    }
    return remainder;
}

int main(int argc, char *argv[]) {
    srand(time(NULL));
    if (argc != 3) {
        perror("Number of command line arguments is non-sufficient");
        return -1;
    }

    int ngoodchild = atoi(argv[1]);
    int nbadchild = atoi(argv[2]);

    // Create a shared memory segment
    int shmid = shmget(IPC_PRIVATE, 2 * sizeof(int), IPC_CREAT | 0666);
    if (shmid == -1) {
        perror("Shared memory creation failed");
        exit(EXIT_FAILURE);
    }

    // Attach to shared memory in parent process
    int *shared_mem = (int *)shmat(shmid, NULL, 0);
    if (shared_mem == (void *)-1) {
        perror("Shared memory attach failed");
        exit(EXIT_FAILURE);
    }

    // Initialize shared memory
    shared_mem[0] = 0; // Remainder for gifts
    shared_mem[1] = 0; // Remainder for punishment

    // Create pipes for communication
    int goodpipefd[2], badpipefd[2];
    if (pipe(goodpipefd) == -1 || pipe(badpipefd) == -1) {
        perror("Pipe creation failed");
        exit(EXIT_FAILURE);
    }

    pid_t goodchildfork = fork();
    if (goodchildfork == -1) {
        perror("Fork failed");
        exit(EXIT_FAILURE);
    }

    if (goodchildfork > 0) { // Parent process
        pid_t badchildfork = fork();
        if (badchildfork == -1) {
            perror("Fork failed");
            exit(EXIT_FAILURE);
        }

        if (badchildfork > 0) { // Parent process
            // Write to pipes
            close(goodpipefd[0]);
            close(badpipefd[0]);
            write(goodpipefd[1], &ngoodchild, sizeof(ngoodchild));
            write(badpipefd[1], &nbadchild, sizeof(nbadchild));
            close(goodpipefd[1]);
            close(badpipefd[1]);

            // Wait for child processes to finish
            wait(NULL);
            wait(NULL);

            // Read results from shared memory
            printf("Remainder presents: %d\n", shared_mem[0]);
            printf("Remainder virgacs: %d\n", shared_mem[1]);

            // Detach and remove shared memory
            shmdt(shared_mem);
            shmctl(shmid, IPC_RMID, NULL);

        } else { // Second child process (krampusz)
            close(badpipefd[1]);
            int input;
            read(badpipefd[0], &input, sizeof(input));
            close(badpipefd[0]);
            printf("Received %d presents for krampusz\n", input);

            int remaindervirgacs = iterate_over_children(input);

            // Attach to shared memory
            int *shared_mem_child = (int *)shmat(shmid, NULL, 0);
            if (shared_mem_child == (void *)-1) {
                perror("Child shared memory attach failed");
                exit(EXIT_FAILURE);
            }

            // Write result to shared memory
            shared_mem_child[1] = remaindervirgacs;

            // Detach shared memory
            shmdt(shared_mem_child);
            exit(EXIT_SUCCESS);
        }
    } else { // First child process (angel)
        close(goodpipefd[1]);
        int input;
        read(goodpipefd[0], &input, sizeof(input));
        close(goodpipefd[0]);
        printf("Received %d presents for angel\n", input);
        int remainderpresents = iterate_over_children(input);

        // Attach to shared memory
        int *shared_mem_child = (int *)shmat(shmid, NULL, 0);
        if (shared_mem_child == (void *)-1) {
            perror("Child shared memory attach failed");
            exit(EXIT_FAILURE);
        }

        // Write result to shared memory
        shared_mem_child[0] = remainderpresents;

        // Detach shared memory
        shmdt(shared_mem_child);
        exit(EXIT_SUCCESS);
    }

    return 0;
}

