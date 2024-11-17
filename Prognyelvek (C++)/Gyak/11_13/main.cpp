# include "sptr.hpp"
#include <string>

struct Person{
  std::string name;
  int age;
  Person(std::string n, int a): name(n), age(a) {
    
  };
  ~Person(){
    std::cout << name << " logging out." << std::endl;
  }
};

// dinamikus objektumok elettartamat kotottuk a stackhez, explicit delete hivasok nelkul

int main(){
  Sptr<Person> p = Sptr(new Person("Aladar", 23));
  
  Sptr<Person> p2(p);

  p = Sptr<Person>(new Person("Bela", 24));

  std::cout << (*p).name << std::endl;
  std::cout << p2->name << std::endl;

  return 0;
}
