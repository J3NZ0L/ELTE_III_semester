#ifndef WINERY_RECORD_HANDLING_H
#define WINERY_RECORD_HANDLING_H

#define MAX_NAME_LEN 50
#define MAX_TYPE_LEN 30

typedef struct {
    char winery[MAX_NAME_LEN];
    char grape_type[MAX_TYPE_LEN];
    int volume;
    int sugar_content;
} HarvestRecord;

void add_record(const char *filename);
void list_records(const char *filename);
void modify_record(const char *filename);
void delete_record(const char *filename);

#endif // HARVEST_RECORD_HANDLING_H



