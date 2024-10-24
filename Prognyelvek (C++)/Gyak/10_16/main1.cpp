#include <iostream>
#include <deque>

int main(){
  std::deque<int> d = {3, 7, 4, 2};

  // "forditott" osszehasonlitas biztonsagosabb - ha veletlenul egy egyenlosegjel kerul odairasra,
  for(std::deque<int>::iterator i = d.begin(); d.end() != i;  ++i){
    std::cout << *i << " ";
  }
  std::endl(std::cout);

  d.push_front(1);

  // "forditott" osszehasonlitas biztonsagosabb - ha veletlenul egy egyenlosegjel kerul odairasra,
  for(std::deque<int>::iterator i = d.begin(); d.end() != i;  ++i){
    std::cout << *i << " ";
  }
  std::endl(std::cout);


  d.push_front(1);
  return 0;
}
