#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <string.h>
#include <signal.h>
#include <time.h>

pid_t governor_pid;

// Function to handle SIGUSR1 signal in the governor process
void handle_signal(int signo) {
    if (signo == SIGUSR1) {
        printf("Governor received SIGUSR1 from the committee.\n");

        // Open files
        FILE *record_file = fopen("winery_record.txt", "r");
        FILE *normative_file = fopen("normative.txt", "r");

        if (!record_file || !normative_file) {
            perror("Error opening files");
            exit(EXIT_FAILURE);
        }

        // Count lines in winery_record file to select a random line
        int line_count = 0;
        char buffer[256];
        while (fgets(buffer, sizeof(buffer), record_file)) {
            line_count++;
        }

        // Choose a random line
        rewind(record_file);
        int random_line = rand() % line_count;
        char selected_key[256] = {0};
        for (int i = 0; i <= random_line; i++) {
            fgets(buffer, sizeof(buffer), record_file);
        }

        // Parse the selected line to get the key (2nd column, delimited by '|')
        char *token = strtok(buffer, "|"); // Get the first column
        token = strtok(NULL, "|");        // Get the second column
        if (token) {
            strcpy(selected_key, token);
        } else {
            fprintf(stderr, "Error parsing winery record file\n");
            fclose(record_file);
            fclose(normative_file);
            return;
        }

        // Search for the key in the normative file (delimited by ':')
        char normative_value[256] = "Not Found";
        while (fgets(buffer, sizeof(buffer), normative_file)) {
            char *key = strtok(buffer, ":");
            char *value = strtok(NULL, ":");
            if (key && value && strcmp(key, selected_key) == 0) {
                // Remove newline from value if present
                value[strcspn(value, "\n")] = '\0';
                strcpy(normative_value, value);
                break;
            }
        }

        printf("Selected Record: Key = %s, Normative Value = %s\n", selected_key, normative_value);

        // Close files
        fclose(record_file);
        fclose(normative_file);
    }
}

void initiate_supervisement_of_wineries(const char *winery_record_filename, const char *normative_filename) {
    srand(time(NULL));
    pid_t committee_and_governor;
    governor_pid = getpid();

    // Set up signal handler in the governor process
    struct sigaction sa;
    sa.sa_handler = handle_signal;
    sa.sa_flags = 0;
    sigemptyset(&sa.sa_mask);
    sigaction(SIGUSR1, &sa, NULL);

    while (1) {
        sleep(1);
        if (rand() % 4 == 3) {
            printf("%s\n", "The governor woke up, and chose violence (sending out the committee to audit the wineries)");
            committee_and_governor = fork();
            if (committee_and_governor < 0) {
                perror("Error while creating the child (committee) process");
                exit(EXIT_FAILURE);
            }

            if (committee_and_governor > 0) {
                // GOVERNOR
                continue;
            } else {
                // COMMITTEE
                printf("Committee sending SIGUSR1 to the governor...\n");
                kill(governor_pid, SIGUSR1); // Send signal to governor
                exit(EXIT_SUCCESS);
            }
        } else {
            printf("The governor is sleeping instead of working...\n");
        }
    }
}

