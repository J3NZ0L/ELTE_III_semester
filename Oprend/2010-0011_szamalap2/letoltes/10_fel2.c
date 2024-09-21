#include <stdio.h> 
#include <sys/types.h> 
#include <sys/ipc.h> 
#include <sys/shm.h> 

// A t�roland� sz�veg max. m�rete: 

#define MAXMERET 100 

// A mem�ri�ba �r�s elj�r�sa: 

void writeshm(int shmid, char * segptr, char * text); 

// Olvas�s az osztott mem�ri�b�l: 

void readshm(int shmid, char * segptr); 

// T�rl�s a mem�ri�b�l: 

void removeshm(int shmid); 
void changemode(int shmid, char * mode); 
void usage(void); 
int main(int argc, char *argv[]) { 
     key_t key; 
     int shmid; 
     char *segptr; 
     if (argc < 1) 
          exit(1);
     key = ftok(".", 'S'); 
     if ((shmid = shmget(key, MAXMERET, IPC_CREAT|IPC_EXCL|0666)) == -1) { 
          printf("az osztott memoriazona letezik � megnyitom\n"); 
          if ((shmid = shmget(key, MAXMERET, 0)) == -1) { 
               perror("shmget hiba"); 
               exit(1); 
          } 
     } else 
          printf("letrehozok egy uj osztott memoriazonat\n"); 

// A mem�riac�m hozz�rendel�se: 

          if ((segptr = shmat(shmid, 0, 0)) == (void *) -1) { 
               perror("shmat hiba"); 
               exit(1); 
          };
          
          switch (tolower(argv[1][0])) { 
               case 'w':     // megadott sz�veg �r�sa 
                    writeshm(shmid, segptr, argv[2]); 
                    break; 
               case 'r':     // osztott mem�riaz�na kiolvas�sa 
                    readshm(shmid, segptr); 
                    break; 
               case 'd':     // t�rl�s 
                    removeshm(shmid); 
                    break; 
               case 'm':     // jogok m�dos�t�sa 
                    changemode(shmid, argv[2]); 
                    break; 
               default:     // hib�s opci� 
                    usage(); 
          } 
     } 

// A m�veletek

void writeshm(int shmid, char * segptr, char * text) { 
     strcpy(segptr, text); 
     printf("kesz...\n"); 
} 

void readshm(int shmid, char * segptr) { 
     printf("segptr: %s\n", segptr); 
} 

void removeshm(int shmid) { 
     shmctl(shmid, IPC_RMID, 0); 
     printf("torolve\n"); 
} 

void changemode(int shmid, char * mode) { 
     struct shmid_ds myshmds; 
     shmctl(shmid, IPC_STAT, &myshmds); 
     printf("a regi jogok: %o\n", myshmds.shm_perm.mode); 
     sscanf(mode, "%o", &myshmds.shm_perm.mode); 
     shmctl(shmid, IPC_SET, &myshmds); 
     printf("az uj jogok: %o\n", myshmds.shm_perm.mode); 
} 

void usage(void) { 
     printf("shmexam � osztott memoria menedzselo rendszer\n\n"); 
     printf("HASZNALAT: shmexam (w)rite <szoveg>\n"); 
     printf("                      (r)ead\n"); 
     printf("                      (d)elete\n"); 
     printf("                      (m)ode change <oktalis_mod>\n"); 
     exit(1); 
} 
