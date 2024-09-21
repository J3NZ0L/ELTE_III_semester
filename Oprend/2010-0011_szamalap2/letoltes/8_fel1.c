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
 
/* A szemafork�szlet l�trehoz�sa k�t szemaforral, majd kezd��rt�k�ket be�ll�tjuk null�ra (a nulladik szemafor/foglalt helyek), illetve a kapacit�snak megfelel�re (az els� szemafor/�res helyek). */ 
 
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
 
/* A szemafork�szlet t�rl�se. */ 
 
void sem_delete() { 
     if(semctl(sem_id, 0, IPC_RMID) < 0) 
          perror("sem del"); 
} 
 
/* A berak�sn�l az �res helyek cs�kkennek (az els� szemafor), �s a foglalt helyek pedig n�nek (nulladik szemafor), ezt egyetlen semop h�v�ssal �rv�nyes�tj�k. */ 
 
void berak() { 
     struct sembuf allapot[2]; 
     allapot[0].sem_num = 0; 
     allapot[0].sem_op = 1; 
     allapot[1].sem_num = 1; 
     allapot[1].sem_op = -1; 
     
     if(semop(sem_id, allapot, 2) < 0) 
          perror("semop berak"); 
} 
 
/* A kiv�teln�l a foglalt helyek cs�kkennek (nulladik szemafor), az �res helyek pedig n�nek (els� szemafor), ezt egyetlen semop h�v�ssal �rv�nyes�tj�k. */ 
 
void kivesz() { 
     struct sembuf allapot[2]; 
     allapot[0].sem_num = 0; 
     allapot[0].sem_op = -1; 
     allapot[1].sem_num = 1; 
     allapot[1].sem_op = 1; 
     
     if(semop(sem_id, allapot, 2) < 0) 
          perror("semop kivesz"); 
} 
 
/* A gy�rt�folyamat megh�vja a berak f�ggv�nyt. */ 
 
void gyarto() { 
     while(1) { 
          berak(); 
          printf("berak %d\n", getpid()); 
          sleep(rand() % 2 + 1); 
     } 
} 
 
/* A fogyaszt� folyamat megh�vja a kivesz f�ggv�nyt. */ 
 
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
     
     sem_id = sem_create(argv[0]);  /* L�trehozzuk a szemafork�szletet. */ 
     
     for(i = 0; i < PROCDB && child > 0; ++i) /* L�trehozunk PROCDB darabnyi 
                                                 gyerekfolyamatot */ 
          child = fork(); 
          
          if( child > 0 ) { 
               gyarto();  /* A sz�l�folyamat a gy�rt� lesz. */ 
          } else if ( child == 0 ){ /* A gyerekfolyamatok a pidnek 
                                       megfelel�en gy�rt�k vagy fogyaszt�k 
                                       lesznek. */ 
               if( getpid() % 2 == 0 ) 
                    gyarto(); 
               else 
                    fogyaszto(); 
          } 
          
     return 0; 
} 
