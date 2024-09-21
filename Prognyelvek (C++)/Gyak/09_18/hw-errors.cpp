#include <string>
#include <iostream>
// #nclude <iostream> 
// hw-errors.cpp:1:2: error: invalid preprocessing directive #nclude; did you mean #include?


void greet(std::string name){
    std::cout << "Hello, " << name << std::endl;
}

int main() {
    // std::cout << "Hello world! << std::endl;
    // hw-errors.cpp:1:2: error: invalid preprocessing directive #nclude; did you mean #include?
    // Lexikalis elemzo szol erte (szintaktikai es szemantiakai hibat is von maga utan)
    // correct usage:

    std::cout << "Hello world!" << std::endl;

    // int x = 5
    // hw-errors.cpp:12:5: error: expected ‘,’ or ‘;’ before ‘std’ //nem biztos h szintaktikai elemzo szol erte, de tudni lehet hogy olyan tipusu a hiba
    // correct usage:

    int x = 5;

    
    //std::cout << "The value of x: " << y;
    // hw-errors.cpp:17:40: error: ‘y’ was not declared in this scope
    // correct usage:
    
    std::cout << "The value of x: " << x;

    //greet(x);
    //hw-errors.cpp:28:11: error: could not convert ‘x’ from ‘int’ to ‘std::string’ {aka ‘std::__cxx11::basic_string<char>’}
    // correct usage:
    greet("Zoli");

    std::endl(std::cout);

    return 0;
}