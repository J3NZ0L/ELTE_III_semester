#include <iostream>
int f() {
int x = 0;
++x;
return x;
}
int main() {
for (int i = 0; i < 5; ++i)
std::cout << f() << std::endl;
}
