#include <stdio.h>
#include <stdlib.h>
#include <sys/time.h>
#include <time.h>
#include <unistd.h>

int main()
{ 
  srand(time(NULL)); //the starting value of random number generation
  int r=rand()%100; //number between 0-99
  int r2 = rand();
  printf("%d", r2);
  printf("Random number %i\n",r);
  return 0;
}
