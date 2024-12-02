#include <stdlib.h>
#include <time.h>
#include <signal.h>


assign_audit(char * winery_record_filename[], char * normative_filename[] ){
  srand(time(null));
  pid_t committee_and_governor;
  while (true) {
    sleep(1);
    if (rand()%4==3){
      printf("%s\n", "The governor woke up, and chose violence (sending out the committee to audit the wineries)");
      committee_and_governor = fork();
      if (committee_and_governor==0){
        perror("Error while creating the child (committee) process");
        exit(EXIT_FAILURE);
      }
      if (committee_and_governor>0){
        //GOVERNOR

      }
      else {
        //COMMITTEE

        //TODO: send signal to the governor 
      }
    }
    else {
      printf("The governor is sleeping instead of working...");
    }
  }
  return EXIT_SUCCESS;
}
