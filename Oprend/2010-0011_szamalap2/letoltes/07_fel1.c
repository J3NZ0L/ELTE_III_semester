//-------------------- 
// SigKuldKap.c 
//-------------------- 
#include <stdlib.h> 
#include <stdio.h> 
#include <unistd.h>         // P�ld�ul a fork f�ggv�ny. 
#include <signal.h>         // P�ld�ul a kill f�ggv�ny. 
#include <sys/types.h>      // P�ld�ul a pid_t t�pus. 
 
Void kezdjbele(int jelzes) { 
     // A callback f�ggv�ny, param�tere a szign�l sz�m�rt�ke 
     // a gyermekfolyamatnak sz�l� jelz�shez tartoz� f�ggv�ny. 
     Printf(�Most kaptam egy jelzest � elkezdek dolgozni\n�); 
     int i; 
     for ( i = 0; i < 10; i++) { 
          printf(�%i,�, rand() % 100); 
     } 
     printf(�\n�); 
     kill(getppid(), SIGUSR2); 
     // K�ld egy jelz�st a sz�l�folyamatnak, hogy k�sz van. 
} 
 
void kesz(int jelzes) { 
     // A callback f�ggv�ny, param�tere a szign�l sz�m�rt�ke 
     // a sz�l�folyamatnak sz�l� jelz�shez tartoz� f�ggv�ny. 
     Printf(�Vegre kesz!\n�); 
} 
 
int main(int argc, char * argv[]) { 
     pid_t child; 
     int status; 
     int i; 
     printf(�Kezdem\n�); 
     child = fork(); // A gyermekfolyamat l�trehoz�sa. 
     If (child < 0) { 
          perror(�hiba\n�); 
          exit(1); 
     } 
     if (child == 0) { // A gyermek. 
          Signal(SIGUSR1, kezdjbele); 
          // A gyermekfolyamathoz tartoz� jelz�skezel� be�ll�t�sa. 
          For( i = 0; i < 10; i++) { 
               printf(�Unatkozom\n�); 
               sleep(1); 
          } 
     } else { 
          signal(SIGUSR2, kesz); 
          // A sz�l�folyamathoz tartoz� jelz�skezel� be�ll�t�sa. 
          Int ido = rand() % 5; 
          printf(�%i s mulva jelzest kuldok a gyermeknek\n�, ido); 
          sleep(ido); 
          kill(child, SIGUSR1); 
          // Jelz�st k�ld a gyermeknek, hogy kezdjen bele a 
          // v�letlen sz�m gener�l�s�ba. 
          Waitpid(child, &status, 0); 
          printf(�Vege a szulofolyamatnak\n�); 
       }; 
       return 0; 
} 
//-------------------- 
