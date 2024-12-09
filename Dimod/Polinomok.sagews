︠45d1f3ee-d251-4a0f-9d97-4d19c4624edc︠
#Polinomok létrehozása

#Kell hozzá egy gyűrű

QQ
ZZ
RR
NN
CC
IntegerModRing(72)

R

︡ee85fb4f-da4f-42ec-8fc7-72663cae7200︡{"stdout":"Rational Field\n"}︡{"stdout":"Integer Ring\n"}︡{"stdout":"Real Field with 53 bits of precision\n"}︡{"stdout":"Non negative integer semiring\n"}︡{"stdout":"Complex Field with 53 bits of precision\n"}︡{"stdout":"Ring of integers modulo 72\n"}︡{"stdout":"Univariate Polynomial Ring in y over Integer Ring\n"}︡{"done":true}
︠4b360f3e-9888-4d47-b0a8-03cf5a28d55f︠

# kell hozzá a változónév is
R.<y> = ZZ[]

︡9197dfdf-574a-4274-8366-46cce0cce73f︡{"done":true}
︠4a6cf7a9-306c-4551-9905-12f438a08da7︠

#Polinomok létrehozása

f=y**2-23*y+7
g=y^5+12*y**3-1

f,g

h=[1,3,4,0,0,5,0,2]

h=R(h)
h
︡3686af68-7f73-4751-a527-b10225ee413e︡{"stdout":"(y^2 - 23*y + 7, y^5 + 12*y^3 - 1)\n"}︡{"stdout":"2*y^7 + 5*y^5 + 4*y^2 + 3*y + 1\n"}︡{"done":true}
︠2a67ce24-db75-4aaf-8681-4693ae49d313︠


# Műveletek

f+g
g-h

f*h


︡bd800991-b6db-489f-b85c-61a9aaad6a3d︡{"stdout":"y^5 + 12*y^3 + y^2 - 23*y + 6\n"}︡{"stdout":"-2*y^7 - 4*y^5 + 12*y^3 - 4*y^2 - 3*y - 2\n"}︡{"stdout":"2*y^9 - 46*y^8 + 19*y^7 - 115*y^6 + 35*y^5 + 4*y^4 - 89*y^3 - 40*y^2 - 2*y + 7\n"}︡{"done":true}
︠c58ff928-8ce6-480c-ae7d-05e42ae6cec5︠

# Osztás

g/f

g.quo_rem(f)
︡af57cb91-25a5-4294-8f35-15a8479aa84a︡{"stdout":"(y^5 + 12*y^3 - 1)/(y^2 - 23*y + 7)\n"}︡{"stdout":"(y^3 + 23*y^2 + 534*y + 12121, 275045*y - 84848)\n"}︡{"done":true}
︠61c1da7a-889a-4e5f-bef6-5dea7dbddf11︠

#gcd 

g.gcd(g)
︡79b97129-d5a1-4f55-9f62-1cd3a65c5e70︡{"stdout":"y^5 + 12*y^3 - 1\n"}︡{"done":true}
︠d8220f06-9728-4ed7-8ac7-bf162a0fc2c3︠

# Behelyettesítés

f(3)
g(-49)

(f*g)(2)
︡3f16ce7b-b325-4628-a5d7-a07456b91204︡{"stdout":"-53\n"}︡{"stdout":"-283887038\n"}︡{"stdout":"-4445\n"}︡{"done":true}
︠6a0cd133-225d-4a7d-81a1-650bad1f0eae︠

#gyökök megkeresése

#R.<y>=CC[]

f = y^3 - 1
f.roots()

f.factor()

f.gcd((y-1)**5)
︡f24b4f91-dabb-4149-89aa-1c6a64e3c4b4︡{"stdout":"[(1, 1)]\n"}︡{"stdout":"(y - 1) * (y^2 + y + 1)\n"}︡{"stdout":"y - 1\n"}︡{"done":true}
︠163ae991-d26a-4072-ab6a-7689cf2e8d22︠
p = ZZ['x']([1, 2, 0, 4])

p.leading_coefficient()
p.constant_coefficient()
p.degree()
p.list()
p.dict()
p.roots(ring=RR)

##
︡fddd94f6-c970-41ff-bef9-a77763b5800b︡{"stdout":"4\n"}︡{"stdout":"1\n"}︡{"stdout":"3\n"}︡{"stdout":"[1, 2, 0, 4]\n"}︡{"stdout":"{0: 1, 1: 2, 3: 4}\n"}︡{"stdout":"[(-0.385458498529624, 1)]\n"}︡{"done":true}
︠935cf47e-10fa-487a-bcd1-ee3df57abc44︠

p = (x - 3)*(x - 3)*(x - 12.5)*(x + 45)*(x - 12)*(x + 2)
p
expand(p)
︡8728aa3f-3411-47ee-8c25-3e3240616027︡{"stdout":"(x + 45)*(x + 2)*(x - 3)^2*(x - 12)*(x - 12.5000000000000)\n"}︡{"stdout":"x^6 + 16.5000000000000*x^5 - 1037.50000000000*x^4 + 10516.5000000000*x^3 - 23773.5000000000*x^2 - 37395.0000000000*x + 121500.000000000\n"}︡{"done":true}
︠657bba3b-15b7-4335-a34d-6aa166110ed6︠

p = RR['x']([1, 4, -6, 56, -43])
plot(p(x), x, -5, 5)
︡5f95b411-7db3-47ed-b781-89b12d5c5279︡{"file":{"filename":"/tmp/tmplu16zp5k/tmp_x39s4_qh.svg","show":true,"text":null,"uuid":"3e3b3767-50da-4600-b319-2801a114926a"},"once":false}︡{"done":true}
︠cbed7219-acfd-4b90-8097-012d3aba7307︠
plot(sin(x) + cos(x), x, -2*pi, 2*pi, color='green')
︡e921d86e-664a-4ee9-bbeb-7ea75ac6fa82︡{"file":{"filename":"/tmp/tmpmx_670dt/tmp_sew127pm.svg","show":true,"text":null,"uuid":"b222a193-2807-4679-a263-4a3937f4dbe4"},"once":false}︡{"done":true}
︠d5be8182-601d-450f-be75-bd2a057a1141︠
points([(1, 2), (5, 6), (-4, 7)])
︡0b040416-9257-4f7b-865f-b9b92827a2fb︡{"file":{"filename":"/tmp/tmpmx_670dt/tmp_tcntt37x.svg","show":true,"text":null,"uuid":"4664c5f9-9dfc-4157-a272-359d26f9cba3"},"once":false}︡{"done":true}
︠312d8334-afd7-47cf-b6a1-ff1b72144cc3︠
points([(1, 2), (5, 6), (-4, 7)]) + plot(sin(x) + cos(x), (x, -2*pi, 2*pi), color='green')
︡41e6062d-9685-4786-89dd-a47fdab7a843︡{"file":{"filename":"/tmp/tmpmx_670dt/tmp_ityrkjjj.svg","show":true,"text":null,"uuid":"a04107cd-c586-4959-a629-381a94ee0a8e"},"once":false}︡{"done":true}
︠73a63ff0-b702-432a-a822-6737192bb3bf︠
def PlotGcd(p, q):
    a=plot([])
    pq_gcd = p.gcd(q)
    a+=plot(p, x, -3, 3)
    a+=plot(q, x, -3, 3)
    a+=plot(pq_gcd, x, -3, 3, color='red')

    return a

PlotGcd(x^4 - 5*x^2 + 4, x^4 - 2*x^3 - 2*x^2 + 8*x - 8)
︡2f41e6e0-bec6-4f95-a43e-bb6eaf19068d︡{"file":{"filename":"/tmp/tmpmx_670dt/tmp_rw0a01lx.svg","show":true,"text":null,"uuid":"78dafff6-a2ca-4742-b9c1-13224fdd057b"},"once":false}︡{"done":true}
︠1379b75d-7c91-4d3c-91db-6fec06be80a9︠
lagr=RR['x'].lagrange_polynomial([(0, 3), (1, 3), (4, 7), (-1, 0)])
points([(0, 3), (1, 3), (4, 7), (-1, 0)])+plot(lagr(x), x, -1, 3, color="red")
︡42f22acb-eaad-44cd-845f-1aefaf4a45f8︡{"file":{"filename":"/tmp/tmpmx_670dt/tmp_4nmg2sy3.svg","show":true,"text":null,"uuid":"e9701f5e-ef96-4d5c-84e7-4ed1631ff9b9"},"once":false}︡{"done":true}
︠85cd9346-273c-4612-baa3-c93433956ebd︠
GF(7)
GF(7).list()

GF(7)['x'].lagrange_polynomial([(1, 3), (2, 4), (5, 0), (6, 4)])
︡5d93f413-0c4c-4625-9ff2-ff20f1c3dafb︡{"stdout":"Finite Field of size 7\n"}︡{"stdout":"[0, 1, 2, 3, 4, 5, 6]\n"}︡{"stdout":"4*x^3 + 3*x^2 + 6*x + 4"}︡{"stdout":"\n"}︡{"done":true}
︠01a84c6c-4369-4128-9e3a-074490491a53︠
GF(7)['x'].lagrange_polynomial([(1, 3), (2, 4), (5, 0), (4, 13)])
︡9c9f6d99-d419-4429-b403-64b06fc7a254︡{"stdout":"x + 2\n"}︡{"done":true}
︠c859771e-2f6f-478d-8170-741dc3ee1d77︠

### Feladat
# Lagrange polynom SSS
# generate
# calculate_secret




















