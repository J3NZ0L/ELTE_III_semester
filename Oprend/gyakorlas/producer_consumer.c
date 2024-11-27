#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

#define N 5  // Shelf capacity

// Shared resource
int shelf[N];
int count = 0;  // Current number of items on the shelf
int in = 0;     // Producer index
int out = 0;    // Consumer index

// Mutex and condition variables
pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;
pthread_cond_t not_full = PTHREAD_COND_INITIALIZER;
pthread_cond_t not_empty = PTHREAD_COND_INITIALIZER;

// Producer function
void* producer(void* arg) {
    int bread = 0;
    while (1) {
        // Produce a new item
        bread++;
        usleep(500000);  // Simulate production time (0.5 seconds)

        pthread_mutex_lock(&mutex);

        // Wait if the shelf is full
        while (count == N) {
            printf("Shelf is full. Producer is sleeping...\n");
            pthread_cond_wait(&not_full, &mutex);
        }

        // Place the bread on the shelf
        shelf[in] = bread;
        printf("Producer placed Bread-%d on the shelf (Current size: %d)\n", bread, count + 1);
        in = (in + 1) % N;
        count++;

        // Notify the consumer that the shelf is not empty
        pthread_cond_signal(&not_empty);
        pthread_mutex_unlock(&mutex);
    }
    return NULL;
}

// Consumer function
void* consumer(void* arg) {
    while (1) {
        pthread_mutex_lock(&mutex);

        // Wait if the shelf is empty
        while (count == 0) {
            printf("Shelf is empty. Consumer is sleeping...\n");
            pthread_cond_wait(&not_empty, &mutex);
        }

        // Take the bread from the shelf
        int bread = shelf[out];
        printf("Consumer took Bread-%d from the shelf (Current size: %d)\n", bread, count - 1);
        out = (out + 1) % N;
        count--;

        // Notify the producer that the shelf is not full
        pthread_cond_signal(&not_full);
        pthread_mutex_unlock(&mutex);

        // Consume the bread
        printf("Consumer is eating Bread-%d\n", bread);
        usleep(1000000);  // Simulate consumption time (1 second)
    }
    return NULL;
}

// Main function
int main() {
    pthread_t producer_thread, consumer_thread;

    // Create producer and consumer threads
    pthread_create(&producer_thread, NULL, producer, NULL);
    pthread_create(&consumer_thread, NULL, consumer, NULL);

    // Wait for threads to finish (they won't, as this program runs indefinitely)
    pthread_join(producer_thread, NULL);
    pthread_join(consumer_thread, NULL);

    // Clean up resources (not reached in this example)
    pthread_mutex_destroy(&mutex);
    pthread_cond_destroy(&not_full);
    pthread_cond_destroy(&not_empty);

    return 0;
}

