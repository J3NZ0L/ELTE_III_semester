#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <stdio.h>
#include <errno.h>
#include <stdlib.h>
#include <fcntl.h>
#include <unistd.h>

int main(int argc, char * argv[]){
  char * filename = "/tmp/pipe";
  int fd;
  printf("RD makefifo");
  // if (-1 == mkfifo(filename, S_IRUSR | S_IWUSR)){
  //   printf("An error occurred while attempting to read from the pipe: %d", errno);
  //   exit(EXIT_FAILURE);
  // }
  printf("RD openfd");
  fd = open(filename, O_RDONLY);
  char inputs[1000];
  read(fd, inputs, sizeof(inputs));
  printf("Read from pipe: %s", inputs);
  close(fd);
  printf("RD closefd");
  return 0;
}
