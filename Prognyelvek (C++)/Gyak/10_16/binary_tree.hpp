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
    // 
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
  
public: //c++ban szekciok vannak, nem kell kulon definialni a lathatosagat minden egyes adattagnak metodusnak egy szekcion belul (alapertelmezett szekcio private ugye)
  Btree(){
    r = NULL;
  }
  ~Btree(){

    std::cout << "~Btree(), " << std::endl; // h tudjuk h melyik elemet szabaditottuk fel eppen
    deleteBranch(r);
  } //destruktornak nincs parametere, legtobbszor fordito kezeli, o nem tudja mi lehetne - mindenkepp kell lennie igy parameter nelkulinek (egyebkent lehet definialni ezen kivul parameterezettet is)
    //

void push(int e){
    if (NULL == r){
      r = new Node(e);
      return;
    }
  push(e, r);
  }

};
