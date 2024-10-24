#include <iostream>

char toUpper(char c){
    if (c>='a' && c<='z'){
        //return c+('A'-'a');
        return c-('a'-'A'); //implicit castolas
    }
    return c;
    /*
    //guards:
    if (c>='A' && c<='Z'){
        return c;
    }
    if (c<'a' || c>'z'){
        return c;
    }

    //if everything checks:
    return c+('A'-'a')
    */
}

int main(){
    char name[256];
    std::cout << "Hello, what is your name?" << std::endl;
    std::cin >> name ;

    //null karakter terminalja a karakterfolyamot: '\0'
    //name[3]='\0'; //manualisan is be lehet allitani
    name[0] = toUpper(name[0]);

    std::cout << "Goodbye, " << name << "!" << std::endl;
    return 0;
}