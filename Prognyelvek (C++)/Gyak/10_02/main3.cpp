#include <iostream>

int inc(int a){
    return a++;
}

void unsafeInc(int &a){
    a++;
}

int main(){
    const int x = 5; //csak jelzeserteku a forditonak, igazabol nem letezik futasidoben "konstans" valtozo

    std::cout << inc(x) << std::endl;
    const_cast<int>(x);
    unsafeInc(x);
    std::cout << x << std::endl;

    int y = 6;

    unsafeInc(y);

    return 0;
}