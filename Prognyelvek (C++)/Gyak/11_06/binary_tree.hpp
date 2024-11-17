#include <cstddef>
#include <iostream>
//hpp kiterjesztes: ha nem elkulonitheto a definicio es a deklaracio - alapvetoen az elso az cpp, masodik header fajlba kene h keruljon
class Btree {
 //struct es class koozott kulonbseg: classnal adattag default lathatosaga private, structnal public, egyebkent MINDEN mas tok ugyanaz
  struct Node{ //Node nem lesz public, csak a nodeon belul annak az adattagjai (szamara?)
    int v;
    Node* l; //left child
    Node* r; //right child
    // : es {} kozott: inicializalo lista!! nem baj ha ugyanugy hivjuk a parametereket: de nyilvan az elso az adattag, a zarojelben pedig a parameter, alapertelmezetten, kulon specifikalas nelkul: ezt a fordito is ki tudja egyszeruen kovetkeztetni
    // miert jo ez a param lista: egybol bemasolodnak igy az adatok a megfelelo adattag ertekebe
    // kotelezo ezt hasznalni konstans adattagnal!
    Node(int v, Node *l = NULL, Node *r = NULL): v(v), l(l), r(r){}
    // copy konstruktort felul kell irni ha az eletciklusat mi szeretnenk kezelni
    // alapertelmezetten keszit nekunk a fordito egy shallow copy konstuktort
    // explitict akkor szokas copy konstruktort felulirni ha szeretnenk masolgatni az objektumokat egyeni logika szerint - a dinamikusan letrehozott adattagokert felelosek szeretnenk lenni 
    // implicit copy konstruktor hivasok gyakran tortennek, peldaul returnolesnel, stb.

    void printTo(std::ostream &os){
      os << "(" ;
      if (NULL != l){
        l->printTo(os);
      }
      os << ",";
      if (NULL != r){
        r->printTo(os);
      }
      os << v << ")";
    }
  };

  Node *r;
  void deleteBranch(Node *b){
    if (b == NULL){
      return;
    }

    deleteBranch(b->l);
    deleteBranch(b->r);
    std::cout << "deleting branch with root value: " << b->v << std::endl;
    delete b;
  }

  void push(int e, Node *b){
    if (b->v == e){ return;}
    
    if (e < b->v){
      if (NULL == b-> l){
        b->l == new Node(e);
        return;
      }
      push(e, b->l);
    } 
    else {
      if (NULL == b->r){
        b->r = new Node(e);
        return;
      }
      
      push(e, b->r);
    }
  }
 
  Node* copyBranch(Node* b){
    if (NULL == b){
      return NULL;
    } 

//  Node * res = new Node(b->v, copyBranch(b->l), copyBranch(b->r));
    return new Node(b->v, copyBranch(b->l), copyBranch(b->r));
  }

public: //c++ban szekciok vannak, nem kell kulon definialni a lathatosagat minden egyes adattagnak metodusnak egy szekcion belul (alapertelmezett szekcio private ugye)
  Btree(){
    r = NULL;
  }
  ~Btree(){

    std::cout << "~Btree(), " << std::endl; // h tudjuk h melyik elemet szabaditottuk fel eppen
    deleteBranch(r);
  } //destruktornak nincs parametere, legtobbszor fordito kezeli, o nem tudja mi lehetne - mindenkepp kell lennie igy parameter nelkulinek (egyebkent lehet definialni ezen kivul parameterezettet is)
    //

  Btree(const Btree &b){
    std::cout << "Btree copy constr has been called!" << std::endl;
    r = copyBranch(b.r); 
  }

void push(int e){
    if (NULL == r){
      r = new Node(e);
      return;
    }
  push(e, r);
  }
  
  Btree& operator=(const Btree &rhs){
    // classon belul, peldanyszintu metodusnal eleg ugyebar a jobboldali operandust argumentumkent kezelni
    // osztalyszintunel kell mindket operandus parameterkent
    //deleteBranch(this->r) // megegyezik kovi sorral
    std::cout << "Btree& operator=(const Btree &rhs)";
    deleteBranch(r);
    r = copyBranch(rhs.r);
    return (*this);
  }
  // nem a class a friend, hanem az operator a classon belul
  friend std::ostream& operator<<(std::ostream &os, const Btree &t) {
    if (NULL == t.r){ return os;}
    t.r->printTo(std::cout);
    return os;
  }

};
