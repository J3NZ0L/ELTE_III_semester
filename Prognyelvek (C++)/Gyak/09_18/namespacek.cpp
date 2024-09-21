#include <iostream>

// mellozzuk a std:: specifikalasat a benne talalhato metodusok hasznalata soran
// azert hasznaljunk namespaceket, hogy olvashatobb legyen a kod
// valojaban a namespace, amellett h rendezettebbnek tunik, segit elkerulni a nevutkozest
using namespace std; // forraskod hatralevo reszeben elerheto a hasznalata

namespace foo{
    int x;
    namespace bar{
        int x;
    }
}


int main(){
    // std::azonosito -> fully qualified identifier (ha hasznalunk "using" "direktivat" ra, akkor opcionalis )
    cout << "Hello world!" << endl;

    cout << foo::x << std::endl;
    cout << foo::bar::x << std::endl;


    return 0;
}