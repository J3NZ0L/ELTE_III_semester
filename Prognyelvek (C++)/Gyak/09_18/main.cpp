#include <iostream>
// olyan hibak, amik nem torik el a forditot de nem szep kodot eredmenyez
// pl beegetett ertekek

//TOKEN kiemelesere lehetosegek:
const int TOKEN = 420; //globalis konstans elnevezesere konvencio a nagybetus irasmod.

int magicTokenToCoin(int token){
    //return 420 * token; // itt peldaul, mivel arfolyamot hasznalunk, ezert nagyon valoszinu hogy nem valos erteket tukroz a valtas, amiatt hogy a 420 be van "egetve" a kodba
    return TOKEN * token; //g++ -w -Wall -DTOKEN=420 main.cpp -o main (ezzel lehet igy is forditani, helyes, de nem a legjobb, lyuk a kodban), emeljuk ki!
}

// kontextustol fuggetlen mukodik, nincs fuggoseg konstanstol
// szintaktikus cukorka az alapertelmezett TOKEN ertek a masodik parameternel

int magicTokenToCoinwRate(int token, int rate=TOKEN){
    return rate * token;
}

int main(){
    // input parameter is ertelmesebb lenne itt:
    //std::cout << "5 tokenem " << magicTokenToCoin(5) << " coint er" << std::endl;

    int t;
    std::cout << "mennyi tokened van? >>> ";
    std::cin >> t ;
    std::endl(std::cout);
    std::cout << t << " tokenem " << magicTokenToCoinwRate(t, 450) << " coint er." << std::endl;

    return 0;
}