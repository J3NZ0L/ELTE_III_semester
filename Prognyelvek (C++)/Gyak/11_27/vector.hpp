#include 
        arr[i] = rhs.arr[i];
      }
    }
    vector<value_type>& operator=(vector<value_type> &rhs){
      if (NULL != arr){
        delete[] arr;
      }
      current = rhs.current;
      arr = new T[capacity];
      for (size_t i = 0; i< current; ++i){
        arr[i] = rhs.arr[i];
      }
      return *this;
    }
    ~vector() {
      delete[] arr;
      arr = tmp;
      capacity = capacity * 2;
    }
    arr[current] = e;
    ++current;
  }
  void pop.back() {
    --current;
  }
  T& operator[](int i) const {
    return arr[i];
  }
  size_t size() const {  return current; }
  bool empty() const {return (0 == current;) }
  class iterator;
//
//  ne masoljunk at mindent hanem csak az uj pozicioig, ha meg van telve a vektor
//  amiert nem konstans a kapott iterator: mivel noveltuk a meretet, es masik arrayt allokaltuk, tehat invalid lesz a kapott parameter - ugye ez csak abban az esetben ervenyes, ha novelni kell a meretet egyebkent
  iterator insert(iterator &pos, const T &v){
    if (current == capacity){
      T* tmp = new T[capacity*2];
      capacity = capacity * 2;
      for ( size_t i = 0; i< pos.c; ++i){
        tmp[i] = arr[i];
      }
      tmp[pos.c] = v;
      for (size_t i = pos.c+1; i < current+1; ++i){
        tmp[i] = arr[i-1]; 
      }
      delete[] arr;
      arr = tmp;
      current +=1;
      pos.c +=1;
      pos.a = arr;
      return iterator(arr, pos.c-1); //az uj elemre mutat igy
    }
    for (size_t i = current; i> pos.c; --i){
      arr[i] = arr[i-1];
    } 
    arr[pos.c] = v;
    pos.c += 1;
    return (iterator(arr, pos.c-1));
  }
  iterator begin() const { return iterator(arr, 0);}
  iterator end() const { return iterator(arr, current); }
  class iterator {
    T *a;
    size_t c;
    friend class vector<T>;
    iterator(T *a, size_t c): a(a), c(c) {}
  public:
    T& operator*() const { return a[c];}
    T* operator->() const {return $a[c]; }
    iterator& operator++(){
      ++c;
      return *this;
    }
    iterator operator++(int) {
      iterator i = *this;
      ++c;
      return i;
    }
    bool operator==(const iterator &rhs) const {
    // vizsgaljuk az osszehasonlitast a mainben.
      return (this->a == rhs.a && this->c == rhs.c);
    }
    
    bool operator!=(const iterator &rhs) const {
    // vizsgaljuk az osszehasonlitast a mainben.
      return !(*this == rhs);
  }
 
  
}; 
