
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "winery_record_handling.h"

void add_record(const char *filename) {
    HarvestRecord record;
    FILE *file = fopen(filename, "a");
    if (!file) {
        perror("File opening failed");
        return;
    }

    printf("Enter winery name: ");
    getchar();  // consume newline char
    fgets(record.winery, MAX_NAME_LEN, stdin);
    record.winery[strcspn(record.winery, "\n")] = 0;  // remove newline

    printf("Enter grape type: ");
    fgets(record.grape_type, MAX_TYPE_LEN, stdin);
    record.grape_type[strcspn(record.grape_type, "\n")] = 0; //remove newline

    printf("Enter volume in liters: ");
    scanf("%d", &record.volume);
    printf("Enter sugar content: ");
    scanf("%d", &record.sugar_content);

    // write to file
    fprintf(file, "%s|%s|%d|%d\n", record.winery, record.grape_type, record.volume, record.sugar_content);
    fclose(file);
}

void list_records(const char *filename) {
    HarvestRecord record;
    FILE *file = fopen(filename, "r");  // open file for reading
    if (!file) {
        perror("File opening failed"); // print file opening error (from errno)
        return;
    }

    printf("Winery\t\tGrape Type\tVolume (L)\tSugar Content\n");
    printf("----------------------------------------------------------\n");

    // format specifier breakdown: x characters until '|', then expects and skips the '|', and this is done two times, and two integers are scanned after that.
    while (fscanf(file, " %49[^|]|%29[^|]|%d|%d\n", record.winery, record.grape_type, &record.volume, &record.sugar_content) == 4) {
        printf("%s\t%s\t%d\t%d\n", record.winery, record.grape_type, record.volume, record.sugar_content);
    }
    fclose(file);
}

void modify_record(const char *filename) {
    HarvestRecord record;
    char winery[MAX_NAME_LEN], grape_type[MAX_TYPE_LEN];
    int found = 0;

    printf("Enter winery name to modify: ");
    getchar();  // consume newline
    fgets(winery, MAX_NAME_LEN, stdin);
    winery[strcspn(winery, "\n")] = 0; //remove newline

    printf("Enter grape type: ");
    fgets(grape_type, MAX_TYPE_LEN, stdin);
    grape_type[strcspn(grape_type, "\n")] = 0; //remove newline

    FILE *file = fopen(filename, "r");
    FILE *temp = fopen("temp.txt", "w");  // create temporary file for easing the modification process
    if (!file || !temp) {
        perror("File opening failed");
        return;
    }

    // search for record, query new data if found
    // format string specifier is explained a bit above in list_records method
    while (fscanf(file, " %49[^|]|%29[^|]|%d|%d\n", record.winery, record.grape_type, &record.volume, &record.sugar_content) == 4) {
        if (strcmp(record.winery, winery) == 0 && strcmp(record.grape_type, grape_type) == 0) {
            printf("Enter new volume in liters: ");
            scanf("%d", &record.volume);
            printf("Enter new sugar content: ");
            scanf("%d", &record.sugar_content);
            found = 1;
        }
        fprintf(temp, "%s|%s|%d|%d\n", record.winery, record.grape_type, record.volume, record.sugar_content);
    }

    fclose(file);
    fclose(temp);
    remove(filename);
    rename("temp.txt", filename);

    if (!found) {
        printf("Record not found.\n");
    }
}

void delete_record(const char *filename) {
    HarvestRecord record;
    char winery[MAX_NAME_LEN], grape_type[MAX_TYPE_LEN];
    int found = 0;

    printf("Enter winery name to delete: ");
    getchar();  // consume newline character from buffer
    fgets(winery, MAX_NAME_LEN, stdin);
    winery[strcspn(winery, "\n")] = 0;  // remove newline character

    printf("Enter grape type: ");
    fgets(grape_type, MAX_TYPE_LEN, stdin);
    grape_type[strcspn(grape_type, "\n")] = 0;  // remove newline character

    FILE *file = fopen(filename, "r");
    FILE *temp = fopen("temp.txt", "w");  // temporary file for all other records
    if (!file || !temp) {
        perror("File opening failed"); // print file opening error (from errno)
        return;
    }

    // copy each record to the temporary file except the one to be deleted
    // format string specifier is explained a bit above in list_records method
    while (fscanf(file, " %49[^|]|%29[^|]|%d|%d\n", record.winery, record.grape_type, &record.volume, &record.sugar_content) == 4) {
        if (strcmp(record.winery, winery) == 0 && strcmp(record.grape_type, grape_type) == 0) {
            found = 1;
        } else {
            // write all non-matching records to temp file, to be able to keep them intact
            fprintf(temp, "%s|%s|%d|%d\n", record.winery, record.grape_type, record.volume, record.sugar_content);
        }
    }

    fclose(file);
    fclose(temp);

    // update the original file, if the record was found
    if (found) {
        remove(filename);
        rename("temp.txt", filename);
        printf("Record deleted successfully.\n");
    } else {
        remove("temp.txt"); 
        printf("Record not found.\n");
    }
}


