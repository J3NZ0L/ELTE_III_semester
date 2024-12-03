#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#include <errno.h>

int main(int argc, char *argv[])
{
  if (argc!= 2){
    printf("Incorrect usage, please specify number of child processes\n");
    return 0; 
  };
  
  uint childnum = atoi(argv[1]);
  int status[childnum];
  int statpar;
  for (uint i =0; i<childnum; i++){
    pid_t child=fork();
    if (child<0){perror("The fork calling was unsuccessful");};
    if (child>0){
      printf("parent i: %d\n", i);
      printf("Parent (PID: %d, i: %d)\n", getpid(), i);
    }
    if (child==0){
      printf("Child (PID: %d, Parent PID: %d, i: %d)\n", getpid(), getppid(), i);
      printf("child i: %d\n", i);
      exit(0);
    }
  }
  // for (int i=0; i<childnum; i++) {
  //   wait(&status[i]);
  // }
  return EXIT_SUCCESS;
}
