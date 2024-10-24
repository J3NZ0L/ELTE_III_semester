#include <iostream>
#include <clocale>

#define NULL 0;

struct Point {
    int x;
    int y;
};

void move(Point &p, int mx, int my = 0){ //ha nincs a p elott a cimkepzo operator, akkor ertek szerinti parameteratadas tortenik
    p.x +=  mx;
    p.y += my;
}

/*
referenciarol feltesszuk h sosem NULL
tehat referenciat tudjuk ugy kezelni mint egy valtozot, nem foglalkozunk a dinamikussagaval
ha esetleg megis egy dinamikus memoriaban levo futas kozben felszabadulo valtozonak a referenciajat hasznalnank fel, ami mar nem a mienk akkor az problemat szul
pointernel tudunk NULL-ra csekkolni, referencianal nem
*/

int main(){
    int x = 5;
    int * y = new int(6);
    /*
    y es x stackben tarolodnak,
    yban tarolt memoriacim szabadul fel
    new int() az nem! Hanem, mivel mi hoztuk letre, fel is kell szabaditani, meg a sajat scopejaban, utana mar nem tudunk hozzaferni - > delete()
    */
   /*
   malloc memoriacimet ad vissza, sikeres memoriafoglalas eseten
   */

    std::cout << x << std::endl;
    // std::cout << y << std::endl; //             0x5bc1b15782b0
    std::cout << *y << std::endl; // 6
    
    delete(y);

    Point p1;
    Point *p2 = new Point;
    p1.x = 4;
    p1.y = 5;

    /*
    main.cpp:32:8: error: request for member ‘x’ in ‘p2’, which is of pointer type ‘Point*’ (maybe you meant to use ‘->’ ?)
   32 |     p2.x = 6;
      |        ^
main.cpp:33:8: error: request for member ‘y’ in ‘p2’, which is of pointer type ‘Point*’ (maybe you meant to use ‘->’ ?)
   33 |     p2.y = 7;
      |        ^
    */
    /*
    p2.x = 6;
    p2.y = 7;
    */   
    // pointeren keresztul adattagot C++ -ban a '->' operatorral erjuk el - megegyezik a (*p2).x kifejezessel
    p2->x = 6;
    p2->y = 7;

    std::cout << "p2.x: " << p2->x << std::endl;
    
    delete(p2);
   
    /*
    Point* p3, p4; // p4 igy nem lenne pointerkent definialva, hiszen nincs a valtozonev elott a *
    //erdemes a valtozonev ele irni a csillagot
    p3 = new Point;
    p4 = new Point; //nem fordulna le
    */
    Point *p3, *p4; 
    p3 = new Point;


    p3->x = 9;
    p3->y = 11;

    move(*p3, 3, 4);
    std::cout << "p3.x, p3.y: " << p3->x << " " << p3->y << std::endl;

    Point *p5 = NULL;

    delete(p3);
    delete(p4);

    int x = 5;
    const int* const p = &x;
    const int* const p1 = &x;

    int const * const* p3 = &p;
    p3 = &p1;

    return 0;
}