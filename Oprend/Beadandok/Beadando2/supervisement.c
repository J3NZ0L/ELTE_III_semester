#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <string.h>
#include <signal.h>
#include <time.h>
#include <sys/wait.h>

pid_t governor_pid;
int pipe_fd[2]; // Pipe file descriptors: [0] for reading, [1] for writing

// Function to handle SIGUSR1 signal in the governor process - the governor sends the data to be supervised, with the normaitve, through a pipe
void handle_signal(int signo) {
    if (signo == SIGUSR1) {
        static const char *filename = "harvest_records.txt";
        static const char *f_normatives = "sugar_level_normatives.txt";

        printf("Governor received SIGUSR1 from the committee.\n");

        // Open files
        FILE *record_file = fopen(filename, "r");
        FILE *normative_file = fopen(f_normatives, "r");

        if (!record_file || !normative_file) {
            perror("Error opening files");
            exit(EXIT_FAILURE);
        }

        // Count lines in the winery_record file to select a random line
        int line_count = 0;
        char buffer[256];
        while (fgets(buffer, sizeof(buffer), record_file)) {
            line_count++;
        }

        if (line_count == 0) {
            fprintf(stderr, "Error: No records in file '%s'.\n", filename);
            fclose(record_file);
            fclose(normative_file);
            return;
        }

        // Choose a random line
        rewind(record_file);
        int random_line = rand() % line_count;
        char selected_key[256] = {0};
        for (int i = 0; i <= random_line; i++) {
            fgets(buffer, sizeof(buffer), record_file);
        }

        // Parse the selected line to get the key
        char *token = strtok(buffer, "|"); // First column
        token = strtok(NULL, "|");        // Second column
        if (token) {
            strcpy(selected_key, token);
        } else {
            fprintf(stderr, "Error parsing winery record file.\n");
            fclose(record_file);
            fclose(normative_file);
            return;
        }

        // Search for the key in the normative file
        rewind(normative_file); // Ensure file is read from the start
        char normative_value[256] = "Not Found";
        while (fgets(buffer, sizeof(buffer), normative_file)) {
            char *key = strtok(buffer, ":");
            char *value = strtok(NULL, ":");
            if (key && value && strcmp(key, selected_key) == 0) {
                value[strcspn(value, "\n")] = '\0'; // Remove newline
                strcpy(normative_value, value);
                break;
            }
        }

        // Send message through the pipe
        char message[512];
        snprintf(message, sizeof(message), "Record Key: %s, Normative Value: %s", selected_key, normative_value);

        if (write(pipe_fd[1], message, strlen(message) + 1) == -1) {
            perror("Error writing to pipe");
        } else {
            printf("Governor sent: %s\n", message);
        }

        fclose(record_file);
        fclose(normative_file);
    }
}

void initiate_supervisement_of_wineries() {
    srand(time(NULL));
    governor_pid = getpid();
    
    signal(SIGUSR1, handle_signal);

    while (1) {
        sleep(1);
        if (rand() % 4 == 3) {
            printf("The governor woke up, and chose violence (sending out the committee to audit the wineries).\n");
            //TODO: megkerdezni Szabitol!!!
            int pipe_fd[2]; // Create a new pipe for each fork
            if (pipe(pipe_fd) == -1) {
                perror("Pipe creation failed");
                exit(EXIT_FAILURE);
            }

            pid_t committee_pid = fork();
            if (committee_pid < 0) {
                perror("Error while creating the child (committee) process");
                exit(EXIT_FAILURE);
            }

            if (committee_pid > 0) {
                // GOVERNOR
                pause();
                close(pipe_fd[0]); // Close reading end in governor
                waitpid(committee_pid, NULL, 0); // Wait for committee to finish
                close(pipe_fd[1]); // Close writing end in governor after use
            } else {
                // COMMITTEE
                close(pipe_fd[1]); // Close writing end in committee
                printf("Committee sending SIGUSR1 to the governor...\n");
                kill(governor_pid, SIGUSR1); // Send signal to governor

                // Read message from the pipe
                char message[512];
                ssize_t bytes_read = read(pipe_fd[0], message, sizeof(message) - 1);
                if (bytes_read > 0) {
                    message[bytes_read] = '\0'; // Null-terminate the message
                    printf("Committee received: %s\n", message);
                } else {
                    perror("Error reading from pipe");
                }

                close(pipe_fd[0]); // Close reading end in committee
                exit(EXIT_SUCCESS);
            }
        } else {
            printf("The governor is sleeping instead of working...\n");
        }
    }
}
