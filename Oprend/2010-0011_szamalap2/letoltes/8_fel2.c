#include <sys/ipc.h> 
#include <sys/sem.h> 
#include <sys/shm.h> 
#include <sys/types.h> 
#include <sys/stat.h> 
#include <stdio.h> 
#include <stdlib.h> 
#include <string.h> 
#include <unistd.h> 
 
 
#if !defined(__GNU_LIBRARY__) || defined (_SEM_SEMUN_UNDEFINED) 
union semun { 
     int val; 
     struct semid_ds * buf; 
     unsigned short int * array; 
     struct seminfo * __buf; 
}; 
#endif 
 
int shm_id;        /* A megosztott mem�ria azonos�t�ja (ID). */ 
int sem_mutex;     /* A szemafor a sz�ml�l� v�delm�hez. */ 
int sem_db;        /* A szemafor a kiz�r�lagos el�r�shez. */ 
int * rc;          /* Az olvas�kat sz�ml�l� megosztott mem�ria c�me. */ 
 
/* Megosztott mem�riam�veletek: */ 
 
int sh_mem_create(const char * str, int mem_size) { 
     key_t kulcs; 
     kulcs = ftok(str, 1); 
     if(kulcs < 0) 
          return kulcs; 
     return shmget( kulcs, mem_size,  IPC_CREAT | S_IRUSR | S_IWUSR ); 
} 
 
int sh_mem_free(int sh_mem_id) { 
     return shmctl( sh_mem_id, IPC_RMID, NULL ); 
} 
 
/* A szemafor l�trehoz�sa: */ 
 
int sem_create(const char * str,int v) { 
     key_t kulcs; 
     kulcs = ftok(str,v); 
     if(kulcs < 0) 
          return kulcs; 
     return semget(kulcs, 1, IPC_CREAT | 0700 ); 
} 
 
/* A szemafor kezdeti �rt�k�nek a be�ll�t�sa: */ 
 
int sem_init(int szemafor_id, int val) { 
     return semctl(szemafor_id, 0, SETVAL, val); 
} 
 
/* A szemafor t�rl�se: */ 
 
int sem_delete(int szemafor_id) { 
     return semctl(szemafor_id, 0, IPC_RMID); 
} 
 
/* A szemafor down m�velete: */ 

int down(int szemafor_id) { 
     struct sembuf allapot; 
     allapot.sem_num = 0; 
     allapot.sem_op = -1; 
     allapot.sem_flg = 0; 
     return semop(szemafor_id, &allapot, 1); 
} 
 
/* A szemafor up m�velete: */ 
 
int up(int szemafor_id) { 
     struct sembuf allapot; 
     allapot.sem_num = 0; 
     allapot.sem_op = 1; 
     allapot.sem_flg = 0; 
     return semop(szemafor_id, &allapot, 1); 
} 
 
/* �r�s az adatt�bl�ba: */ 
 
void iras() { 
     printf("IRAS kezdete: %d\n", getpid()); 
     sleep(1); 
     printf("IRAS vege: %d\n", getpid()); 
} 
 
/* Olvas�s az adatt�bl�b�l: */ 
 
void olvasas() { 
     printf("OLVASAS kezdete: %d\n", getpid()); 
     sleep(1); 
     printf("OLVASAS vege: %d\n", getpid()); 
} 
 
/* Az �r�s m�velete kiz�r�lagos z�rol�ssal: */ 
 
void iro_muvelet() { 
     down(sem_db); 
     iras(); 
     up(sem_db); 
} 
 
/* Az olvas� m�velete megosztott hozz�f�r�ssel: */ 
  
void olvaso_muvelet() { 
     down(sem_mutex); 
     (*rc)++; 
     if((*rc) == 1) 
          down(sem_db); 
     up(sem_mutex); 
     olvasas(); 
     down(sem_mutex); 
     (*rc)--; 
     if((*rc) == 0) 
          up(sem_db); 
     up(sem_mutex); 
 } 
 
int main (int argc, char * argv[]) { 
     pid_t child; 
     int i; 
     
     /* L�trehozzuk a megosztott v�ltoz�t �s a k�t szemafort: */ 
     
     shm_id = sh_mem_create(argv[0], sizeof(int)); 
     rc = shmat(shm_id, NULL, 0); 
     *rc = 0; 
     sem_mutex = sem_create(argv[0], 2); 
     sem_init(sem_mutex, 1); 
     sem_db = sem_create(argv[0], 3); 
     sem_init(sem_db, 1); 
     
     /* L�trehozunk �t gyerekfolyamatot: */ 
     
     child = 1; 
     for ( i = 0; i < 5 && child > 0; ++i) 
          child = fork(); 
     
     /* A folyamatok �v�letlenszer�en� �rnak vagy olvasnak: */ 
     
     for ( i = 0; i < 5; ++i ){ 
          if ( getpid() % 2 == 0 ) 
               olvaso_muvelet(); 
          else 
               iro_muvelet(); 
     } 
     
     return 0; 
} 
