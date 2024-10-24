#include <iostream>
#include <vector>

int main(){


  // -W kapcsoloval forditva kapunk warningot az osszehasonlitasra.
  // main0.cpp: In function ‘int main()’:
  // main0.cpp:9:18: warning: comparison of integer expressions of different signedness: ‘int’ and ‘std::vector<int>::size_type’ {aka ‘long unsigned int’} [-Wsign-compare]
  // 9 |   for (int i=0; i<v.size(); ++i){

  std::vector<int> v = {3, 5, 4};
  for (int i=0; i<v.size(); ++i){
    std::cout << v[i] << std::endl;
  }

  std::cout << "-----------" << std::endl;

  for (size_t i=0; i<v.size(); ++i){
    std::cout << v[i] << std::endl;
  }

  // itt most bantjukk az iteratort, push_front lenne a megfelelo
  for (int i=0; i<5; i++){
    v.push_back(90-i);
  }
  
  std::vector<int>::iterator b = v.begin();
  v.insert(b, 9);

  b += 1;
  v.erase(b);

  //iteratorral:
   
  std::cout << "-----------" << std::endl;
  //lehet novelni az iteratorokat hasonloan mint a pointereket
  // ez az iterator egy vector<int>!!! iterator, nem sima vector iterator
  for (std::vector<int>::iterator i = v.begin(); i!=v.end(); ++i){
    std::cout << *i << std::endl;
  }
  // kovetkezo iteracio nem helyes, HF megnezni mivel lehet kijavitani, ciklusfeltetel, rbegin rend lehet kerdeses, pointer aritmetikaval valo egyszerusite, ciklusfeltetel, rbegin rend lehet kerdeses, pointer aritmetikaval valo egyszerusitess:
  for (std::vector<int>::reverse_iterator r = v.rend(); r!= v.rbegin() ;++r){
    std::cout << *r << std::endl; 
  }

  for (int i=0; i<5; i++){
    v.push_back(90-i);
  }

  return 0;
}
