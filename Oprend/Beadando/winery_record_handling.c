
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
    getchar();  
    fgets(record.winery, MAX_NAME_LEN, stdin);
    record.winery[strcspn(record.winery, "\n")] = 0;  

    printf("Enter grape type: ");
    fgets(record.grape_type, MAX_TYPE_LEN, stdin);
    record.grape_type[strcspn(record.grape_type, "\n")] = 0;  

    printf("Enter volume in liters: ");
    scanf("%d", &record.volume);
    printf("Enter sugar content: ");
    scanf("%d", &record.sugar_content);

    
    fprintf(file, "%s|%s|%d|%d\n", record.winery, record.grape_type, record.volume, record.sugar_content);
    fclose(file);
}

void list_records(const char *filename) {
    HarvestRecord record;
    FILE *file = fopen(filename, "r");  
    if (!file) {
        perror("File opening failed");
        return;
    }

    printf("Winery\t\tGrape Type\tVolume (L)\tSugar Content\n");
    printf("----------------------------------------------------------\n");

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
    getchar();  
    fgets(winery, MAX_NAME_LEN, stdin);
    winery[strcspn(winery, "\n")] = 0;

    printf("Enter grape type: ");
    fgets(grape_type, MAX_TYPE_LEN, stdin);
    grape_type[strcspn(grape_type, "\n")] = 0;

    FILE *file = fopen(filename, "r");
    FILE *temp = fopen("temp.txt", "w");  
    if (!file || !temp) {
        perror("File opening failed");
        return;
    }

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
    getchar();  
    fgets(winery, MAX_NAME_LEN, stdin);
    winery[strcspn(winery, "\n")] = 0;  

    printf("Enter grape type: ");
    fgets(grape_type, MAX_TYPE_LEN, stdin);
    grape_type[strcspn(grape_type, "\n")] = 0;  

    FILE *file = fopen(filename, "r");
    FILE *temp = fopen("temp.txt", "w");  
    if (!file || !temp) {
        perror("File opening failed");
        return;
    }

    
    while (fscanf(file, " %49[^|]|%29[^|]|%d|%d\n", record.winery, record.grape_type, &record.volume, &record.sugar_content) == 4) {
        if (strcmp(record.winery, winery) == 0 && strcmp(record.grape_type, grape_type) == 0) {
            found = 1;  
        } else {
            
            fprintf(temp, "%s|%s|%d|%d\n", record.winery, record.grape_type, record.volume, record.sugar_content);
        }
    }

    fclose(file);
    fclose(temp);

    
    if (found) {
        remove(filename);
        rename("temp.txt", filename);
        printf("Record deleted successfully.\n");
    } else {
        remove("temp.txt");  
        printf("Record not found.\n");
    }
}


