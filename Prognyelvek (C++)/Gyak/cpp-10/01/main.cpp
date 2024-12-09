#include "vector.hpp"

int main() {
  vector<int> v(2);
  v.push_back(3);
  v.push_back(4);
  v.push_back(5);

  v.pop_back();
  std::cout << v.size() << " " << v[1] << std::endl;

  std::cout << "vector content:" << std::endl;
  for (size_t i = 0; i < v.size(); ++i) {
    std::cout << v[i] << std::endl;
  }

  vector<int> v1(2);
  v1.push_back(2);
  
  // ha csak a vector::c valtozot hasonlitjuk ossze,
  // igazat ad, ami nem jo.
  std::cout << "v1.begin() == v.begin()?: "
    << (v.begin() == v1.begin()) << std::endl; 

  std::cout << "vector content:" << std::endl;
  for (vector<int>::iterator i = v.begin(); i != v.end(); ++i) {
    std::cout << *i << std::endl;
  }

  vector<int>::iterator it = v1.begin();
  vector<int>::value_type newElem = *(v1.insert(it, 3));

  std::cout << "new item: " << newElem << std::endl;
  std::cout << "second item: " << *it << std::endl;

  --it;
  v1.erase(it);


  std::cout << "v1:" << std::endl;
  for (vector<int>::iterator i = v1.begin(); i!= v1.end(); ++i){
    std::cout << *i << std::endl;
  }

  v1.clear();

  std::cout << "v1:" << std::endl;
  for (vector<int>::iterator i = v1.begin(); i!= v1.end(); ++i){
    std::cout << *i << std::endl;
  }
  return 0;
}
