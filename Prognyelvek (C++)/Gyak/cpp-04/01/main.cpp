#include <iostream>
#include <cstddef>

int main() {
  int t[] = {7, 5, 6};

  for (int *i = t; i != t+3; i += 1) {
    std::cout << *i << std::endl;
  }

  return 0;
}
