#include <iostream>

#ifndef MYNUM_H
#define MYNUM_H

template<class T>
class MyNUm{
  T n;
public:
  MyNum(T n): n(n) {
  }
  MyNum& operator++();

}
#endif
