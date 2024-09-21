#include <sys/ipc.h> 
#include <sys/sem.h> 
#include <sys/types.h> 
#include <stdio.h> 
#include <stdlib.h> 
#include <unistd.h> 
#include <sys/stat.h> 
#include <wait.h> 
 
#define MAXDB 3 
#define PROCDB 5 
 
int sem_id; 
 
#if !defined(__GNU_LIBRARY__) || defined (_SEM_SEMUN_UNDEFINED) 
union semun { 
     int val; 
     struct semid_ds * buf; 
     unsigned short int * array; 
     struct seminfo * __buf; 
}; 
#endif 
 
/* A szemaforkészlet létrehozása két szemaforral, majd kezdõértéküket beállítjuk nullára (a nulladik szemafor/foglalt helyek), illetve a kapacitásnak megfelelõre (az elsõ szemafor/üres helyek). */ 
 
int sem_create(const char * filename) { 
     int kulcs = ftok(filename,1); 
     if( (sem_id = semget(kulcs, 2, IPC_CREAT | S_IRUSR | S_IWUSR )) < 0 ) 
          perror("sem get"); 
     if(semctl(sem_id, 0, SETVAL, 0) < 0) 
          perror("sem init"); 
     if(semctl(sem_id, 1, SETVAL, MAXDB) < 0) 
          perror("sem init"); 
     return sem_id; 
} 
 
/* A szemaforkészlet törlése. */ 
 
void sem_delete() { 
     if(semctl(sem_id, 0, IPC_RMID) < 0) 
          perror("sem del"); 
} 
 
/* A berakásnál az üres helyek csökkennek (az elsõ szemafor), és a foglalt helyek pedig nõnek (nulladik szemafor), ezt egyetlen semop hívással érvényesítjük. */ 
 
void berak() { 
     struct sembuf allapot[2]; 
     allapot[0].sem_num = 0; 
     allapot[0].sem_op = 1; 
     allapot[1].sem_num = 1; 
     allapot[1].sem_op = -1; 
     
     if(semop(sem_id, allapot, 2) < 0) 
          perror("semop berak"); 
} 
 
/* A kivételnél a foglalt helyek csökkennek (nulladik szemafor), az üres helyek pedig nõnek (elsõ szemafor), ezt egyetlen semop hívással érvényesítjük. */ 
 
void kivesz() { 
     struct sembuf allapot[2]; 
     allapot[0].sem_num = 0; 
     allapot[0].sem_op = -1; 
     allapot[1].sem_num = 1; 
     allapot[1].sem_op = 1; 
     
     if(semop(sem_id, allapot, 2) < 0) 
          perror("semop kivesz"); 
} 
 
/* A gyártófolyamat meghívja a berak függvényt. */ 
 
void gyarto() { 
     while(1) { 
          berak(); 
          printf("berak %d\n", getpid()); 
          sleep(rand() % 2 + 1); 
     } 
} 
 
/* A fogyasztó folyamat meghívja a kivesz függvényt. */ 
 
void fogyaszto() { 
     while(1) { 
          kivesz(); 
          printf("kivesz %d\n", getpid()); 
          sleep(rand() % 2 + 1); 
     } 
} 
 
int main (int argc, char * argv[]) { 
     pid_t child = 1; 
     int i; 
     int ch[PROCDB]; 
     
     sem_id = sem_create(argv[0]);  /* Létrehozzuk a szemaforkészletet. */ 
     
     for(i = 0; i < PROCDB && child > 0; ++i) /* Létrehozunk PROCDB darabnyi 
                                                 gyerekfolyamatot */ 
          child = fork(); 
          
          if( child > 0 ) { 
               gyarto();  /* A szülõfolyamat a gyártó lesz. */ 
          } else if ( child == 0 ){ /* A gyerekfolyamatok a pidnek 
                                       megfelelõen gyártók vagy fogyasztók 
                                       lesznek. */ 
               if( getpid() % 2 == 0 ) 
                    gyarto(); 
               else 
                    fogyaszto(); 
          } 
          
     return 0; 
} 
