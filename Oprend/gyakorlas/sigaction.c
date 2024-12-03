#include <stdio.h>
#include <signal.h>
#include <time.h>
#include <stdlib.h>
#include <string.h>
#include <fcntl.h>
#include <unistd.h>

void defsighandler(int signum) {
    if (signum != SIGINT && signum != SIGUSR1) {
        printf("Wrong signal received! Expected SIGINT or SIGUSR1, got %d\n", signum);
        return;
    }
    printf("Expected signal received: %d\n", signum);
}

void write_to_log(int signum) {
    static FILE *log_file = NULL;
    time_t now = time(NULL); // Correctly declare `now` and initialize
    char *time_str;
    printf("Log file status: %d", !log_file);
    if (!log_file) {
        log_file = fopen("signal.log", "a"); // Use fopen instead of open for easier file handling
        if (!log_file) {
            perror("Error opening log file");
            return;
        }
    }

    time_str = ctime(&now); // Convert time to string
    time_str[strlen(time_str) - 1] = '\0'; // Remove newline from ctime output

    fprintf(log_file, "Received signal %d at %s\n", signum, time_str);
    fflush(log_file); // Ensure data is written to the file immediately
}

void notsighandler(int signum) {
    printf("Signal received, number: %d\n", signum);
    write_to_log(signum);
}

int main(int argc, char *argv[]) {
    struct sigaction sadef;
    sadef.sa_handler = defsighandler;
    sadef.sa_flags = 0; // No special flags
    sigemptyset(&sadef.sa_mask); // No signals blocked during handler execution

    // Set the handler for SIGINT and SIGUSR1
    if (sigaction(SIGINT, &sadef, NULL) == -1) {
        perror("Error setting SIGINT handler");
        exit(EXIT_FAILURE);
    }
    if (sigaction(SIGUSR1, &sadef, NULL) == -1) {
        perror("Error setting SIGUSR1 handler");
        exit(EXIT_FAILURE);
    }

    printf("Signal handlers installed. Waiting for signals...\n");
    while (1) {
        pause(); // Wait for signals indefinitely
    }

    return 0;
}

