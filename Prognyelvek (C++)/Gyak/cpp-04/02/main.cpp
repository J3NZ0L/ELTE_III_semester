#include <iostream>

char toUpper(char c) {
  // ez nem teljes
  // if ('A' <= c && 'Z' >= c) { return c; }
  if (!('a' <= c && 'z' >= c)) { return c; }
  int diff = 'a'-'A';
  return (c - diff);
}

int main() {
  char name[256];
  std::cout << "Szia! Hogy hivnak?" << std::endl << ">>> ";
  std::cin >> name;

  // null karakter van a string vegen, ez terminalja. '\0'
  // ezzel el lehet jatszadozni.
  // name[3] = '\0';

  name[0] = toUpper(name[0]);

  std::cout << "Viszlat " << name << "!" << std::endl;

  return 0;
}
