#include <iostream>

template<class T>
void debugLog(T item){
#ifdef DEBUG
  std::cout << "most: " << item << std::endl;
#endif
}

int main(){
  debugLog<int>(777);
  debugLog("Hello");
  debugLog("Bableves");
  std::cout << "Hello mindenki" << std::endl;
  return 0;
}
