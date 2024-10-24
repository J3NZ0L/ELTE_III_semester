#include <iostream>
#include <list>

int main(){
  std::list<int> l= {4, 2, 1, 5};

  // betevos-kitevos peldak hasonloan vannak implementalva mint deque-nal - ennel konstans muveletigenyuek
  // superscript ([i]) operator hasznalata nem konstans muveletigenyu

  //  std::cout << l[2] << std::endl; //nincs is [] operator pont az elozo pont miatt: nem kooltseghatekony, nem tombrol beszelunk ugye
  std::list<int>::iterator it = l.begin();
  for (int i = 0; i < 2; ++i){
    ++it;
  }
  std::cout << *it << std::endl;
  return 0;
}
