#ifndef VECTOR_HPP
#define VECTOR_HPP

#include <cstddef>
#include <iostream>

template<typename T>
class vector {
  T* arr;
  size_t capacity;
  size_t current;
public:
  typedef T value_type;
  typedef T& reference;
  typedef const value_type const_reference;
  typedef size_t size_type;
  vector(size_type c = 2): arr(new T[c]), capacity(c), current(0) {}
  vector(const vector<value_type> &v): arr(new T[v.capacity])
    , capacity(v.capacity)
    , current(v.current) {
    for (size_t i = 0; i < current; ++i) {
      arr[i] = v.arr[i];
    }
  }
  vector<value_type>& operator=(vector<value_type> &rhs) {
    if (NULL != arr) {
      delete[] arr;
    }
    capacity = rhs.capacity;
    current = rhs.current;
    arr = new T[capacity];
    for (size_t i = 0; i < current; ++i) {
      arr[i] = rhs.arr[i];
    }
    return *this;
  }
  ~vector() {
    delete[] arr;
  }
  void push_back(value_type e) {
    if (capacity == current) {
      T *tmp = new T[capacity * 2];
      for (size_t i = 0; i < current; ++i) {
        tmp[i] = arr[i];
      }
      delete[] arr;
      arr = tmp;
      capacity = capacity * 2;
    }
    arr[current] = e;
    ++current;
  }
  void pop_back() {
    --current;
  }
  reference operator[](int i) const {
    return arr[i];
  }
  size_t size() const { return current; }
  bool empty() const { return (0 == current); }
  class iterator;

  iterator insert(iterator &pos, const_reference v) {
    if (current == capacity) {
      T* tmp = new T[capacity*2];
      capacity = capacity * 2;
      for (size_t i = 0; i < pos.c; ++i) {
        tmp[i] = arr[i];
      }
      tmp[pos.c] = v;
      for (size_t i = pos.c+1; i < current+1; ++i) {
        tmp[i] = arr[i-1];
      }
      delete[] arr;
      arr = tmp;
      pos.c += 1;
      pos.a = arr;
      return iterator(arr, pos.c-1);
    }
    for (size_t i = current; i > pos.c; --i) {
      arr[i] = arr[i-1];
    }
    arr[pos.c] = v;
    current += 1;
    pos.c += 1;
    return (iterator(arr, pos.c-1));
  }

  
  iterator begin() const { return iterator(arr, 0); }
  iterator end() const { return iterator(arr, current); }

  iterator erase(iterator& pos){ // hazi: torles range( from, to) segitsegevel
                                // a ket iterator kozotti kulonbseg felhasznalasaval
    if (pos.c == capacity){
      return pos;
    }
    for (size_t i = pos.c; i != capacity - 1; ++i){
      arr[i] = arr[i+1];
    }
    current -= 1;
    return pos;
  }

  void clear() {
    delete[] arr;
    current = 0;
    arr = new value_type[capacity];

  }

  class iterator {
    T *a;
    size_t c;
    friend class vector<T>;
    iterator(T *a, size_t c): a(a), c(c) {}
  public:
    T& operator*() const { return a[c]; } // a this-re vonatkozik a const - tehat a this-en keresztul direktben nem fog tortenni modositas, ezt jelezzuk - folso szintu modositas
    T* operator->() const { return &a[c]; }
    iterator& operator++() { //prefix
      ++c;
      return *this;
    }
    iterator operator++(int) { //postfix
      iterator i = *this;
      ++c;
      return i;
    }
    iterator& operator--(){ //prefix
      --c;
      return *this;
    }

    iterator operator--(int){
      iterator i = *this;
      ++c;
      return i;
    }
    bool operator==(const iterator &rhs) const {
      // vizsgaljuk az osszehasonlitast a main-ben.
      return (this->a == rhs.a && this->c == rhs.c);
    }
    bool operator!=(const iterator &rhs) const {
      return !(*this == rhs);
    }
  };
};

#endif
