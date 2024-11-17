#include "../11_06/binary_tree.hpp"

int main(){
  Btree b;
  b.push(5);
  b.push(4);
  b.push(6);
  b.push(1);
  b.push(4);
  b.push(7);
  return 0;

  // Node privat, igy ez hibat dobna
//  Node *p = new NOde(9);
  b.push(9);
  b.push(6);
  Btree c(b);

  Btree d = b;
  d = c;

  std::cout << b << std::endl;
  return 0;
}
