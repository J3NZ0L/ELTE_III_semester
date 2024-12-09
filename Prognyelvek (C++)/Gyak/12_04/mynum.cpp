
template<typename T>
MyNum<T>& MyNum::operator++(){
  ++n;
  return *this;
}
