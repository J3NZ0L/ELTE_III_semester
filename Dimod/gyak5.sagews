︠4bcc6dd9-3a1f-4695-ad3c-84804541f081︠
###Lineáris algebra

# A Sagemath támogatja a Lináris algebrát is, szinte minden implementálva van, de be lehet hívni pythonhoz készített könyvtárakat is (numpy, pandas stb.)

##Mátrixok, vektorok

#Konstruktorok

#Létrehozhatjuk konkrét elemeinek megadásával
A = matrix([[1,2,3],[3,2,1],[1,1,1]])

#Extra opcióként megadhatjuk a halmazt amibe tartoznak majd a mátrix elemei. Ez egy megkötés a későbbi műveletekre is.

B = matrix(QQ,[[1,2,3],[3,2,1],[1,1,1]])

C = matrix(ZZ,[[1,2,3],[3,2,1],[1,1,1]])

print(A,'\n')
print(B,'\n')
print(C,'\n')

︡14d180bd-c203-4b0d-9cff-25152b2126e0︡{"stdout":"[1 2 3]\n[3 2 1]\n[1 1 1] \n\n"}︡{"stdout":"[1 2 3]\n[3 2 1]\n[1 1 1] \n\n"}︡{"stdout":"[1 2 3]\n[3 2 1]\n[1 1 1] \n\n"}︡{"done":true}
︠d4a7cca6-1f93-445e-8ea8-0c688491a06as︠

matrix.identity(2)
︡3841de94-7401-4161-bd94-e50c0183435d︡{"stdout":"[1 0]\n[0 1]\n"}︡{"done":true}
︠f47a136b-63c1-4706-818d-dfdfc7274e36︠
# Egy paraméteres konstruktor: nxn-es nullmátrix
m=matrix(5)
print(m , '\n')
# Két paraméter: nxm-es nullmátrix
n=matrix(2,3)
print(n,'\n')


︡89a79467-40d3-45e9-9c07-f53b0603526f︡{"stdout":"[0 0 0 0 0]\n[0 0 0 0 0]\n[0 0 0 0 0]\n[0 0 0 0 0]\n[0 0 0 0 0] \n\n"}︡{"stdout":"[0 0 0]\n[0 0 0] \n\n"}︡{"done":true}
︠dd1889eb-a413-4300-bfa0-26783c99e3a9s︠

#Elemek

print(A,'\n')

A[0,1]=1
print(A, '\n')

#C[0,1]=0.5

#Sorok, Oszlopok

print(A[0],'\n')

print(A.column(1),'\n')
print(A.row(2),'\n')

A.set_column(2,[0,0,1])
print(A,'\n')

︡03c4ede4-6a9a-4669-9e58-485f296b8041︡{"stdout":"[1 2 3]\n[3 2 1]\n[1 1 1] \n\n"}︡{"stdout":"[1 1 3]\n[3 2 1]\n[1 1 1] \n\n"}︡{"stdout":"(1, 1, 3) \n\n"}︡{"stdout":"(1, 2, 1) \n\n"}︡{"stdout":"(1, 1, 1) \n\n"}︡{"stdout":"[1 1 0]\n[3 2 0]\n[1 1 1] \n\n"}︡{"done":true}
︠ca078861-4349-44c9-b019-16f41265ca31︠

#Műveletek

print(A.inverse(),'\n')
print(A*B, '\n')

v=vector([1,2,3])

print(A*v,'\n')

##Egyéb

#Sajátértékek
print(A.eigenvalues(),'\n')

#Sajátvektorok
print(A.eigenvectors_left(),'\n')

print(A.eigenvectors_right(),'\n')

#Ax=0 megoldása
print(A.solve_left(v))

︡1519e4c4-d52a-4cb9-88ad-7044aae3cbda︡{"stdout":"[-2  1  0]\n[ 3 -1  0]\n[-1  0  1] \n\n"}︡{"stdout":"[ 4  4  4]\n[ 9 10 11]\n[ 5  5  5] \n\n"}︡{"stdout":"(3, 7, 6) \n\n"}︡{"stdout":"[1, -0.3027756377319947?, 3.302775637731995?] \n\n"}︡{"stdout":"[(1, [\n(1, 1/2, -3/2)\n], 1), (-0.3027756377319947?, [(1, -0.4342585459106649?, 0)], 1), (3.302775637731995?, [(1, 0.7675918792439982?, 0)], 1)] \n\n"}︡{"stdout":"[(1, [\n(0, 0, 1)\n], 1), (-0.3027756377319947?, [(1, -1.302775637731995?, 0.2324081207560018?)], 1), (3.302775637731995?, [(1, 2.302775637731995?, 1.434258545910665?)], 1)] \n\n"}︡{"stdout":"(1, -1, 3)\n"}︡{"done":true}
︠02a8ee6a-9baf-419a-b5b4-b2b489adbf23︠

#Kombinatorika

#Halmazok

s1=Set([1..5])
s2=Set(['a','b','c'])

print(s1, s2)

#s1xs2

print(list(s1.cartesian_product(s2)))
︡fc8d2bd7-7bf1-484e-ba80-395b8ddd3de1︡{"stdout":"{1, 2, 3, 4, 5} {'b', 'c', 'a'}\n"}︡{"stdout":"[(1, 'b'), (1, 'c'), (1, 'a'), (2, 'b'), (2, 'c'), (2, 'a'), (3, 'b'), (3, 'c'), (3, 'a'), (4, 'b'), (4, 'c'), (4, 'a'), (5, 'b'), (5, 'c'), (5, 'a')]\n"}︡{"done":true}
︠834085f6-90d2-459e-81b9-5fc2ed91f71c︠
#random húzás

print(s1.random_element())
︡d462dccd-e674-49e6-888c-18d75f814b25︡{"stdout":"2\n"}︡{"done":true}
︠ef653dee-861a-4ad6-b7e0-046ed1a1c856s︠
#Részhalmazok

s3=Set(Subsets(s1,2))

print(s3, '\n')

s4=Set(Subsets(s2))

print(s4)
︡3283a34e-8f5f-4804-b3e0-d7b6499c9ee9︡{"stderr":"Error in lines 1-1\nTraceback (most recent call last):\n  File \"/cocalc/lib/python3.11/site-packages/smc_sagews/sage_server.py\", line 1244, in execute\n    exec(\n  File \"\", line 1, in <module>\nNameError: name 's1' is not defined\n"}︡{"done":true}︡
︠7ab132b2-9820-4e40-a374-85552feb78f6s︠
#Elemszám
s4.cardinality()
︡a04922ab-9513-4490-9be8-b8f469494429︡{"stdout":"8\n"}︡{"done":true}
︠a7f66a79-8744-4a81-80df-bd845d2181c8︠

s3.cardinality()
# Kombináció (binomiális együtthatók)
binomial(5,2)
︡7045c9d3-8a56-47f6-8386-888c299c492d︡{"stdout":"10\n"}︡{"stdout":"10\n"}︡{"done":true}
︠6cdb1671-319e-4fb4-b2cf-880805027dce︠
#Permutáció
list(Permutations(3))

list(Permutations[2..5])
︡06efd6e2-b183-476f-ad9a-9a69287b3491︡{"stdout":"[[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]\n"}︡{"stdout":"[[2, 3, 4, 5], [2, 3, 5, 4], [2, 4, 3, 5], [2, 4, 5, 3], [2, 5, 3, 4], [2, 5, 4, 3], [3, 2, 4, 5], [3, 2, 5, 4], [3, 4, 2, 5], [3, 4, 5, 2], [3, 5, 2, 4], [3, 5, 4, 2], [4, 2, 3, 5], [4, 2, 5, 3], [4, 3, 2, 5], [4, 3, 5, 2], [4, 5, 2, 3], [4, 5, 3, 2], [5, 2, 3, 4], [5, 2, 4, 3], [5, 3, 2, 4], [5, 3, 4, 2], [5, 4, 2, 3], [5, 4, 3, 2]]\n"}︡{"done":true}
︠ab9ff529-5364-4b0f-972e-ac94bb9ebb4b︠
#Variáció
list(Permutations([1..3],2))

︡e5592202-1051-43e8-b383-e2fc4d5ba4c8︡{"stdout":"[[1, 2], [1, 3], [2, 1], [2, 3], [3, 1], [3, 2]]\n"}︡{"done":true}
︠2cf37e91-be6e-4042-a420-53b48d9877f7︠

#Számelmélet

#Legnagyobb közös osztó

#Rekurziós algoritmus

gcd(1040,882)

#Kibővített Euklidészi algoritmus

xgcd(1040,882)

d,a,b=xgcd(1040,882)

print(d==a*1040+b*882)

#Legkisebb közös többszörös

lcm(1040,882)

︡b1cfc209-7ecd-4ebb-a6cf-4e4348c0e4c4︡{"stdout":"2\n"}︡{"stdout":"(2, 67, -79)\n"}︡{"stdout":"True\n"}︡{"stdout":"458640\n"}︡{"done":true}
︠3738fe4f-7334-4e9f-bb3c-ec3391ad4caf︠

#Tau

divisors(12)
len(divisors(12))
number_of_divisors(12)

︡faa68214-61ba-4746-a6dd-00e999869b84︡{"stdout":"[1, 2, 3, 4, 6, 12]\n"}︡{"stdout":"6\n"}︡{"stdout":"6\n"}︡{"done":true}
︠25ea3329-4a17-4c2d-9d2b-7c5d4ae0ed1a︠
# b=aq+r
b=7
q=3
a=b//q
r=b%q
print(b,'=',a,'*',q,'+',r)
︡db95535b-fe66-4945-a6bd-aef8c65a72e3︡{"stdout":"7 = 2 * 3 + 1\n"}︡{"done":true}
︠1113c81b-d5de-4d94-abaa-350eac700cf1︠
#Számelmélet alaptétele, faktorizálás

factor(750)
list(factor(750))
︡b859ae50-bde1-4080-b92e-c6413cff175b︡{"stdout":"2 * 3 * 5^3\n"}︡{"stdout":"[(2, 1), (3, 1), (5, 3)]\n"}︡{"done":true}
︠015b44c6-999e-42b5-b888-64594b76a459︠
# Euler phi függvény

euler_phi(20)
︡848cc197-a918-448f-bde7-65bc347c890a︡{"stdout":"8\n"}︡{"done":true}
︠cac36065-ef91-484a-8c5a-3591986b4ade︠
#Mod gyűrű, kongruencia műveletek

R = IntegerModRing(10)

R(11)
R(15)

a=R(2)
b=R(7)

a-b
a*b
a**b
︡0292f051-17e2-4023-a700-80c43d722fcd︡{"stdout":"1\n"}︡{"stdout":"5\n"}︡{"stdout":"5\n"}︡{"stdout":"4\n"}︡{"stdout":"8\n"}︡{"done":true}
︠0cfb92b2-b7cc-4d41-b1d9-a834e6a18079s︠

#Lineáris kongruenciák

#6x === 9 mod 21

R=IntegerModRing(21)

[ x for x in R if R(9)*x == R(6)]

solve_mod(9*x== 6, 21)
︡65ba50f5-a799-4d9a-b8db-256edb807644︡{"stdout":"[3, 10, 17]\n"}︡{"stdout":"[(3,), (10,), (17,)]\n"}︡{"done":true}
︠9d2760f0-efa1-4f91-9b09-a4a670ee29a5s︠

#Kínai maradéktétel

#x ≡ 3 (mod 4)
#x ≡ 5 (mod 21)
#x ≡ 7 (mod 25)

crt([3,5,7],[4,21,25])

#crt([3,5,7],[4,21,42])

︡21515da3-561f-4f81-aff5-115443c97364︡{"stdout":"1307\n"}︡{"done":true}
︠f95c8ee4-d4e5-492b-acdf-b0eaf1c435b2s︠
# Diofantikus egyenlet
#36x+28y=2

var('x')
var('y')

assume(x, 'integer')
assume(y, 'integer')

solve(36*x+28*y==4,[x,y])

︡48e7cb61-bae7-422a-86f3-ed12c307e46e︡{"stdout":"x\n"}︡{"stdout":"y\n"}︡{"stdout":"(7*t_0 - 3, -9*t_0 + 4)"}︡{"stdout":"\n"}︡{"done":true}︡
︠ff85abaa-218f-4552-8525-b469dee7245f︠

#Prímszámok

#Prímek kiírása egy intervallumban

prime_range(1,100)


︡318932b0-c0f6-4f79-9596-80b7bb9f0b60︡{"stdout":"[2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97]\n"}︡{"done":true}
︠495fda98-b1b2-4b95-a239-0504d5313dc7s︠
# n-ig a prímek száma

prime_pi(6)
︡c67f0520-36d1-4eba-aeb6-2ad6bcef1f1b︡{"stdout":"3\n"}︡{"done":true}
︠4d187c64-7426-43b2-9870-cbb240f8995f︠

# Prímek halmaza

p=Primes()

p.cardinality()
#list(p)
7 in p

p.next(792)

#n-edik prímszám
p.unrank(765)  # 0-tól indexel, mintha p[765]-öt lenne
nth_prime(766) # - Ez 1-től indexel

︡d014fbd8-40cb-4544-adb8-0aca6df36418︡{"stdout":"+Infinity\n"}︡{"stdout":"True\n"}︡{"stdout":"797\n"}︡{"stdout":"5839\n"}︡{"stdout":"5839\n"}︡{"done":true}
︠0e017846-b9e1-48e7-957c-dc206cc1be54︠
#Kriptográfia

#Shift

S = ShiftCryptosystem(AlphabeticStrings())

AlphabeticStrings().gen(25)
AlphabeticStrings()[2..5]
#AlphabeticStrings().characteristic_frequency()

P = S.encoding("The shift cryptosystem generalizes the Caesar cipher.")
P
K = 7
C = S.enciphering(K, P)
C
S.deciphering(K, C)
S.deciphering(K, C) == P
︡af73d188-ac69-45bd-bee8-925f9e2308ce︡{"stdout":"Z\n"}︡{"stdout":"CDEF\n"}︡{"stdout":"THESHIFTCRYPTOSYSTEMGENERALIZESTHECAESARCIPHER\n"}︡{"stdout":"AOLZOPMAJYFWAVZFZALTNLULYHSPGLZAOLJHLZHYJPWOLY\n"}︡{"stdout":"THESHIFTCRYPTOSYSTEMGENERALIZESTHECAESARCIPHER\n"}︡{"stdout":"True\n"}︡{"done":true}









