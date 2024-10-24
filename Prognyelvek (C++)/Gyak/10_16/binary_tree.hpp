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
  }

  Node *r;
  void deleteBranch(Node *b){
    if (b == NULL){
      return;
    }

    deleteBranch(b->l);
    deleteBranch(b->r);
    delete b;
  }
public: //c++ban szekciok vannak, nem kell kulon definialni a lathatosagat minden egyes adattagnak metodusnak egy szekcion belul (alapertelmezett szekcio private ugye)
  Btree(){
    r = NULL;
  }
  ~Btree(){
    deleteBranch(r);
    std::cout << "~Btree(), " << v << std::endl; // h tudjuk h melyik elemet szabaditottuk fel eppen
  } //destruktornak nincs parametere, legtobbszor fordito kezeli, o nem tudja mi lehetne - mindenkepp kell lennie igy parameter nelkulinek (egyebkent lehet definialni ezen kivul parameterezettet is)
    //
 void pushLeft(int e){
   if (NULL == r){
     r = new Node(e);
   }
   r-> left = new Node(e);
 }
 void pushRight(int e){
   if (NULL == r){
     r = new Node(e);
   }
  r->right = new Node(e);
 }

};
