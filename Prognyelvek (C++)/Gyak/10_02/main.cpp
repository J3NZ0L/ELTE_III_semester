#include <iostream>
#include <cstddef>

int main(){
    int t[ ] = {1, 2, 7, 6};
    /*
    while (t != NULL){
        std::cout << *t << std::endl;
    }
    */
    /*
     //Segfaultot okoz:
    for (int *i = t; i!=NULL; i+=1){
        std::cout << *i<< std::endl;
    }
    */
    // helyes kivitelezese az elozo hibas kodnak:
    for (int *i = t; i!=t+3; i+=1){
        std::cout << *i<< std::endl;
    } 
    return 0;
}