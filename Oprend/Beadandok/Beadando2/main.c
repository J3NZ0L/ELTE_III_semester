#include <stdio.h>

#include "winery_record_handling.h"
#include "winery_record_handling.c"
#include "supervisement.c"


int main() {
    const char *filename = "harvest_records.txt";
    const char *f_normatives = "sugar_level_normatives.txt";
    int choice;

    do {
        printf("\n--- Winery Harvest Record System ---\n");
        printf("1. Add Record\n");
        printf("2. List Records\n");
        printf("3. Modify Record\n");
        printf("4. Delete Record\n");
        printf("5. Exit\n");
        printf("Select an option: ");
        scanf("%d", &choice);

        switch (choice) {
            case 1:
                add_record(filename);
                break;
            case 2:
                list_records(filename);
                break;
            case 3:
                modify_record(filename);
                break;
            case 4:
                delete_record(filename);
                break;
            case 5:
                initiate_supervisement_of_wineries(filename, f_normatives);
                break;
            case 6:
                printf("Exiting.\n");
            default:
                printf("Invalid choice. Please try again.\n");
        }
    } while (choice != 6);

    return 0;
}

