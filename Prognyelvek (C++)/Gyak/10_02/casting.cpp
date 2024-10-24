#include <iostream>

struct P  { int x; };

int main (int argc, char *argv[]) {
  int a=6;
  char c = static_cast<char>(a);
  
  P *p = new P;
  p->x = 5;
  
  int *px = reinterpret_cast<int*>(p);

  std::cout << *px << std::endl;

  delete p;

  return 0;
}
