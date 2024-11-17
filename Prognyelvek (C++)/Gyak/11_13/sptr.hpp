// Automatic Reference Counting - alternativa a garbage collectionre, c++, rust, swift is ezt hasznalja
// szamoljuk hogy hany referencia van egy adott elemre
// hatranya: a referencia szamlalo novelese csokkentese, foleg tobbszalu programoknal
#ifndef SPTR_HPP
#define SPTR_HPP

#include <cstddef>
#include <iostream>

//c++ ban nincs generikus, csak generikus megoldas - template
// typename es class kozott kulonbseg
// typename lehet alaptipus is
// class csak class vagy struct altal letrehozott adattipus lehet
template<typename T>
// smart pointer
class Sptr {
  T *p;
  int* c;

public:
  Sptr(T* p): p(p) {
    c=new int(1);
  } 
  
  Sptr(const Sptr &p): p(p.p), c(p.c){
    ++(*c);

  }

  Sptr& operator=(const Sptr &rhs){
    //egyebkent nem lehetne null mert abban az esetben copy constructor lenne hivva
    if (NULL != c){
      if (0!=*c){
        delete c;
        delete p;
      }
    }
    p = rhs.p;
    c = rhs.c;
    ++(*c);
    return *this;
  }

  ~Sptr(){
    --(*c);
    if (0 == *c){
      delete c;
      delete p;
    }
  }

  T* operator->(){
    return p;
  }

  T& operator*(){
    return *p;
  }
};


#endif
