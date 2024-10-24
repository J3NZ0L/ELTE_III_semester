#include <iostream>

// +/- majd a kovi utani oran!!

int main(){ 
    int t[] = {1, 2, 3};

    for (int i=0; i<3; i++){
        std::cout << t[i] << std::endl;
    }
    /*
    for (int i=0; i<3; i++) //problema lehet a {} mellozese:
        std::cout << t[i];
        std::cout << std::endl; //ez a sor bar ugy tunhet elsore hogy a ciklusmaghoz tartozik, csak az indentacio miatt nez ki igy, es egyebkent a cikluson kivul fut le, feltetel nelkul
        // mindig hasznaljunk {}-t
    */

   for (int *i = t; i != t+3; i++){
        std::cout << *i << std::endl; 
   }
   /*
    pointer aritmetika:
    t+3 : tudjuk h mekkora a tarolt elem merete, ezt skalazzuk amikor hozzaadunk egy szamot, es 3* a tarolt elemek alapmeretevel noveljuk
    i++ ugyanez
   */
    return 0;
}