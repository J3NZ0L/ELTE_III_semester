#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <stdio.h>
#include <errno.h>
#include <stdlib.h>
#include <fcntl.h>
#include <unistd.h>

int main(){
  int fd;
  char * filename;
  sprintf(filename, "/tmp/pipe");
  printf("WR mkfifo");
  fd = mkfifo(filename, S_IRUSR | S_IWUSR);
  if (fd == -1){
    printf("There was a problem with creating the pipe file. Exiting...");
    printf("Error number: %d", errno);
    exit(EXIT_FAILURE);
  }
  printf("WR openfifo");
  fd = open(filename, O_WRONLY);
  printf("WR printtofifo");
  write(fd, "Dark moon dancing\n", 18);
  close(fd);
  printf("WR closefifo");
}
