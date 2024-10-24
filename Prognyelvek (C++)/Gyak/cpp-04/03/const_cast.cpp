#include <iostream>

int inc(int a) { return a+1; }
void unsafeInc(int &a) { a += 1; }

int main() {
  const int x = 5;

  std::cout << inc(x) << std::endl;
  
  // nem mukodik, pointer vagy ref kell neki.
  // const_cast<int>(x);
  unsafeInc(x);

  std::cout << x << std::endl;

  int y = 6;

  unsafeInc(y);

  return 0;
}
