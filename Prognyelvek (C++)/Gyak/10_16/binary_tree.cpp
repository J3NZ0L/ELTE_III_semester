#include "binary_tree.hpp"

int main(){
  Btree b;
  b.push(5);
  b.push(4);
  b.push(6);
  b.push(1);
  b.push(4);
  b.push(7);

  Btree c(b);

  Btree d = b;

  return 0;
}
