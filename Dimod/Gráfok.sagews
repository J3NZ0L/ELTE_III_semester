︠2530d499-45b8-4b2d-8432-6e1407988060︠
%md

#Konstruktorok

Sageben több módszer is létezik gráfok készítésére. Mindegyikhez a `Graph()` vagy `DiGraph()` konstruktor valamilyen verzióját kell használni. 
Részletes [`Graph()` dokumentáció](https://doc.sagemath.org/html/en/reference/graphs/sage/graphs/graph.html#sage.graphs.graph.Graph) és [`DiGraph()` dokumentáció](https://doc.sagemath.org/html/en/reference/graphs/sage/graphs/digraph.html#sage.graphs.digraph.DiGraph).

Létrehozhatunk egy élek nélküli gráfot, ha csak a csúcsok számát adjuk át. A paraméter nélküli `Graph()` egy üres gráfot hoz létre:

︡206fb35c-d63f-4e1d-8c5b-8d07de958055︡{"done":true,"md":"\n#Konstruktorok\n\nSageben több módszer is létezik gráfok készítésére. Mindegyikhez a `Graph()` vagy `DiGraph()` konstruktor valamilyen verzióját kell használni. \nRészletes [`Graph()` dokumentáció](https://doc.sagemath.org/html/en/reference/graphs/sage/graphs/graph.html#sage.graphs.graph.Graph) és [`DiGraph()` dokumentáció](https://doc.sagemath.org/html/en/reference/graphs/sage/graphs/digraph.html#sage.graphs.digraph.DiGraph).\n\nLétrehozhatunk egy élek nélküli gráfot, ha csak a csúcsok számát adjuk át. A paraméter nélküli `Graph()` egy üres gráfot hoz létre:"}
︠58f2ea56-5c92-462f-9fc2-42e97de74498s︠
Graph(5).show()
Graph()
︡11c9e32f-7e4a-4453-816c-2ed9d23eb028︡{"file":{"filename":"/tmp/tmpsqhra6hm/tmp_omwy92ke.svg","show":true,"text":null,"uuid":"84c14997-a974-4663-8924-65e1aac5dfdc"},"once":false}︡{"stdout":"Graph on 0 vertices\n"}︡{"done":true}
︠4324be96-fd99-4f6a-9a0e-fb340bcf1626s︠
# Egy gráfot létrehozhatunk listák listájával: az első lista a csúcsok címkéit, a második lista a csúcsok közötti éleket definiálja. 
# Ez utóbbi lehet rendezett párok (tuple-ök) listája, vagy listák listája. 
# Mivel az alábbi példában van hurokél és párhuzamos élek is, a `loops=True` és `multiedges=True` paramétereket át kell adni a konstruktornak:

Graph([[1, 2, 3, 4, 5], [(1, 2), (1, 1), (2, 3), (3, 4), (2, 4), (3, 4), (1, 4), (4, 5)]], loops=True, multiedges=True).plot()
︡a7e5a411-2331-459a-830d-51ca58bb1c87︡{"file":{"filename":"/tmp/tmpo5hqk5y0/tmp_l5dfchj_.svg","show":true,"text":null,"uuid":"c91a5083-7ce3-4f7c-9500-ee4074679b45"},"once":false}︡{"done":true}
︠03a40ce5-67b8-4bb5-9481-4adf86852fd7s︠

#Ugyanezt megadhatjuk egy `dict` objektumban is: a kulcsok a csúcsok címkéi, az értékeik pedig azoknak a csúcsoknak a listája, ahová az élek mennek. 
#Ebben a formában megadva nem kell az előző példában látott paramétereket átadni a konstruktornak.

Graph({1: [1, 2, 4], 2: [3, 4], 3: [4, 4], 4: [5]}).plot()
︡9061b197-6c53-4a2d-85ad-4f0af6a420d9︡{"file":{"filename":"/tmp/tmpsqhra6hm/tmp_f42_i9zh.svg","show":true,"text":null,"uuid":"59292897-395d-4a02-86f0-8632b01bcafa"},"once":false}︡{"done":true}
︠72a33332-5648-443a-8e62-72ddb97e113fs︠

#Címkézett gráfokat dictionary objektum segítségével lehet létrehozni:

g = Graph({1: {2: 'a', 3: 'b'}, 3: {2: 'c'}})
g.plot(edge_labels=True)
︡8168d068-5b19-47bf-9a7c-c9afaff8fbdf︡{"file":{"filename":"/tmp/tmppegk2sn8/tmp_8kwvk3ke.svg","show":true,"text":null,"uuid":"9535c237-fbb7-4249-9589-19051c7e8b29"},"once":false}︡{"done":true}
︠b68b1180-9648-488b-86b2-8f3f476afa47︠

%md

Csúcsok listájából és egy *szimmetrikus függvény* segítségével is létrehozható egy gráf. 
A gráf akkor fog egy $u,v$ élet tartalmazni, ha $f(u,v)$ `True`-ra értékelődik ki.
︡87e884cd-69e0-4727-be90-cc7c425911a3︡{"done":true,"md":"\nCsúcsok listájából és egy *szimmetrikus függvény* segítségével is létrehozható egy gráf. \nA gráf akkor fog egy $u,v$ élet tartalmazni, ha $f(u,v)$ `True`-ra értékelődik ki."}
︠b36801c8-1d1f-4e7c-8fda-8525f02616a2s︠


Graph([[2..9], lambda u, v: kulonbseg(u,v)]).plot()




︡8b553966-24b8-46cb-bc00-b644e4278fa8︡{"file":{"filename":"/tmp/tmpsqhra6hm/tmp_dymz2u5t.svg","show":true,"text":null,"uuid":"885254b3-a000-4ef8-b3e7-e8fe09bf6757"},"once":false}︡{"done":true}
︠63a575aa-8823-4969-bd0b-62492927d530s︠

#Vegyük észre, hogy a `Graph()` konstruktor irányítatlan gráfot hoz létre. 
#Irányított gráfot a `DiGraph()` konstruktor segítségével lehet készíteni, a fentiekhez hasonló módon.

%md

### Speciális gráfok

A leggyakoribb speciális gráfokat a Sageben lévő `graphs` objektumon keresztül lehet létrehozni. A teljes lista [irányítatlan](https://doc.sagemath.org/html/en/reference/graphs/sage/graphs/graph_generators.html) és [irányított](https://doc.sagemath.org/html/en/reference/graphs/sage/graphs/digraph_generators.html) gráfokra.


︡4b4990c2-99f9-4f4e-b8bd-86515a8eb042︡{"md":"\n### Speciális gráfok\n\nA leggyakoribb speciális gráfokat a Sageben lévő `graphs` objektumon keresztül lehet létrehozni. A teljes lista [irányítatlan](https://doc.sagemath.org/html/en/reference/graphs/sage/graphs/graph_generators.html) és [irányított](https://doc.sagemath.org/html/en/reference/graphs/sage/graphs/digraph_generators.html) gráfokra.\n\n"}︡{"done":true}
︠c4280911-d69b-4b02-8a4f-b2e57c703881s︠

# 1. Csillag gráf:
graphs.StarGraph(5).plot()
︡907da650-5909-49fc-b84f-3fb51cca30b1︡{"file":{"filename":"/tmp/tmpo5hqk5y0/tmp_0cdbwqya.svg","show":true,"text":null,"uuid":"80de801a-701e-4d1a-a3a9-26755f2805b6"},"once":false}︡{"done":true}
︠7ddb2221-abab-4461-aa28-60992ca8dbd6s︠


# 2. Petersen gráf:
graphs.PetersenGraph()

# 3. Nyalóka:
graphs.LollipopGraph(5, 3).plot()

# 4. Teljes gráf:
graphs.CompleteGraph(6)


︡33cf1cc9-9aa2-45b1-be20-cafc1474cda5︡{"stdout":"Petersen graph: Graph on 10 vertices\n"}︡{"file":{"filename":"/tmp/tmpsqhra6hm/tmp_ktytv15a.svg","show":true,"text":null,"uuid":"b308a5fb-170d-4d5b-8ad7-2a3823aa2fd0"},"once":false}︡{"stdout":"Complete graph: Graph on 6 vertices\n"}︡{"done":true}
︠399b2fbf-708d-44b2-96a8-f89de15c193f︠


%md 

# Gráfok ábrázolása

Minden gráf objektumhoz tartozik egy Sage grafikus objektum is, amit ki lehet rajzolni. A [teljes leírás](https://doc.sagemath.org/html/en/reference/plotting/sage/graphs/graph_plot.html) megtalálható a Sage dokumentációjában.

︡041d66ee-9cc8-410f-b0cd-9f489fe2e0b8︡{"done":true,"md":"\n# Gráfok ábrázolása\n\nMinden gráf objektumhoz tartozik egy Sage grafikus objektum is, amit ki lehet rajzolni. A [teljes leírás](https://doc.sagemath.org/html/en/reference/plotting/sage/graphs/graph_plot.html) megtalálható a Sage dokumentációjában."}
︠fda8913b-6a81-4656-8002-1633cbf33273s︠
g = Graph({1: {1: 'a', 2: 'b', 3: 'c'}, 2: {3: 'd', 4: 'e'}, 3: {4: 'f', 4: 'g'}, 4: {5: 'h'}})
g.show()

︡6818b2b5-7cf2-4d6c-bdc4-f49ed3812507︡{"file":{"filename":"/tmp/tmpo5hqk5y0/tmp_dym3clb4.svg","show":true,"text":null,"uuid":"4b3ae67f-f687-4123-9d31-f6e7ac1d6d3b"},"once":false}︡{"done":true}
︠415f0677-e799-489d-bc46-f6ba350d8117s︠
#Általában az alapértelmezett beállításokkal a `plot()` jól működik (lásd a fenti ábrát), de néha szükség lehet változtatásokra. Néhány paraméter, amivel a `plot()` viselkedését lehet változtatni:

#`vertex_labels`, ez lehet `True` (alapértelmezett) vagy `False`. A csúcsok címkéit írja ki.
g.plot(vertex_labels=False)


︡68e7c3b5-9790-4ccd-b6d8-e45ac806b6f5︡{"file":{"filename":"/tmp/tmppegk2sn8/tmp_7wgqzv51.svg","show":true,"text":null,"uuid":"ef676378-efbd-4f0b-b0ac-f14105dfdbfb"},"once":false}︡{"done":true}
︠8e5a2c92-6824-4fd2-8101-653b0207d126s︠
#`edge_labels`, ez lehet `True` vagy `False` (alapértelmezett). Az élek címkéit írja ki.
g.plot(edge_labels=True)


︡2bad80cd-fb3c-456e-a395-58805fcbe121︡{"file":{"filename":"/tmp/tmppegk2sn8/tmp_39tjx48t.svg","show":true,"text":null,"uuid":"e8cfff58-d43e-4cbc-b219-33e42aac2ba6"},"once":false}︡{"done":true}
︠ef4053a8-148e-4cb8-827c-7f28402f861ds︠
#A `partition` paraméterrel csoportokra bonthatunk csúcsokat, amik azonos színnel jelennek meg. Listák

p = [[2, 3, 5], [1], [4]]
g.plot(partition=p)


︡abf196b8-3bf8-4e33-ae34-e187b20587f3︡{"file":{"filename":"/tmp/tmppegk2sn8/tmp_v9d_szlw.svg","show":true,"text":null,"uuid":"c58e4ace-771a-4282-965e-327fee4c71f5"},"once":false}︡{"done":true}
︠aa192e14-bf96-4e5f-9148-fa57ff698772s︠
#A `vertex_colors` paraméterrel is színezni lehet a csúcsokat, de több szabadságunk van. 

P = graphs.PetersenGraph()
d = {'#FF0000': [0, 5], '#FF9900': [1, 6], '#FFFF00': [2, 7], '#00FF00': [3, 8], '#0000FF': [4, 9]}
P.plot(vertex_colors=d)


︡e48c09a3-9f47-4f5f-9e8c-63666cfecdb9︡{"file":{"filename":"/tmp/tmppegk2sn8/tmp_mtuimq37.svg","show":true,"text":null,"uuid":"2eb909bc-916a-433a-a208-a29ea4fe44a7"},"once":false}︡{"done":true}
︠0a6773ea-d15c-4f0a-b06d-73e1b196519ds︠
# A `talk` paraméterrel nagyobb méretű, fehér hátterű csúcsok lesznek ábrázolva. Lehet `True` vagy `False` (alapértelmezett).

g.plot(talk=True)

# Egy ábrát kimenteni a `plot()` függvényen keresztül lehet a `save()` meghívásával. Paraméterként egy fájlnevet kell megadni, például `g.plot(talk=True).save('mygraph.png')`
︡862f2b9a-23ba-4d32-9be1-d44f6b97a006︡{"file":{"filename":"/tmp/tmppegk2sn8/tmp_q8mw4s7x.svg","show":true,"text":null,"uuid":"68878098-55b1-40b9-9520-c0a78316194f"},"once":false}︡{"done":true}
︠676c9527-20df-446d-bc01-303114a17662︠

%md 

# Gráfokon értelmezett műveletek

A Sage-ben definiált gráfokon elég sok műveletet el lehet végezni. [Sok műveletet](https://doc.sagemath.org/html/en/reference/graphs/sage/graphs/generic_graph.html) irányítatlan és irányított gráfokon is el lehet végezni, azonban vannak típustól függő műveletek is, [irányítatlan](https://doc.sagemath.org/html/en/reference/graphs/sage/graphs/graph.html) és [irányított](https://doc.sagemath.org/html/en/reference/graphs/sage/graphs/digraph.html) gráfokra.

## Szomszédsági és illeszkedési mátrix

**Definíció (szomszédsági mátrix, *adjacency matrix*)**: Legyen $G$ egy gráf $V$ csúcshalmazzal és $E$ élhalmazzal és $n = |V|$. A $G$ gráf *szomszédsági mátrixa* az az $n \times n$-es mátrix $\mathbf{A}_G := (a_{uv})$, ahol $a_{uv}$ az $u$ csúcsból a $v$ csúcsba menő élek számát jelöli, és a hurokélek egy vagy két élnek számítanak (attól függően, melyik könyvet olvassuk).

**Definíció (illeszkedési mátrix, *incidence matrix*)**: Legyen $G$ egy gráf $V$ csúcshalmazzal és $E$ élhalmazzal és $n = |V|, m = |E|$. A $G$ gráf *illeszkedési mátrixa* az az $n \times m$-es mátrix $\mathbf{M}_G := (m_{ve})$, ahol $m_{ve}$ a $v$ csúcs és $e$ él illeszkedéseinek számát ($0$, $1$ vagy $2$) jelöli. A hurokélek két illeszkedésnek számítanak.

Legyen a $G$ gráf az alábbi cellában megadott gráf, aminek a szomszédsági és illeszkedési mátrixa a következő:

︡97e947bf-77cf-4c50-8749-f1d39c2be036︡{"done":true,"md":"\n# Gráfokon értelmezett műveletek\n\nA Sage-ben definiált gráfokon elég sok műveletet el lehet végezni. [Sok műveletet](https://doc.sagemath.org/html/en/reference/graphs/sage/graphs/generic_graph.html) irányítatlan és irányított gráfokon is el lehet végezni, azonban vannak típustól függő műveletek is, [irányítatlan](https://doc.sagemath.org/html/en/reference/graphs/sage/graphs/graph.html) és [irányított](https://doc.sagemath.org/html/en/reference/graphs/sage/graphs/digraph.html) gráfokra.\n\n## Szomszédsági és illeszkedési mátrix\n\n**Definíció (szomszédsági mátrix, *adjacency matrix*)**: Legyen $G$ egy gráf $V$ csúcshalmazzal és $E$ élhalmazzal és $n = |V|$. A $G$ gráf *szomszédsági mátrixa* az az $n \\times n$-es mátrix $\\mathbf{A}_G := (a_{uv})$, ahol $a_{uv}$ az $u$ csúcsból a $v$ csúcsba menő élek számát jelöli, és a hurokélek egy vagy két élnek számítanak (attól függően, melyik könyvet olvassuk).\n\n**Definíció (illeszkedési mátrix, *incidence matrix*)**: Legyen $G$ egy gráf $V$ csúcshalmazzal és $E$ élhalmazzal és $n = |V|, m = |E|$. A $G$ gráf *illeszkedési mátrixa* az az $n \\times m$-es mátrix $\\mathbf{M}_G := (m_{ve})$, ahol $m_{ve}$ a $v$ csúcs és $e$ él illeszkedéseinek számát ($0$, $1$ vagy $2$) jelöli. A hurokélek két illeszkedésnek számítanak.\n\nLegyen a $G$ gráf az alábbi cellában megadott gráf, aminek a szomszédsági és illeszkedési mátrixa a következő:"}
︠428c9ca3-a1b5-447e-b02a-e709b3cb8ed4s︠
g = Graph({1: {1: ['a'], 2: ['b'], 4: ['c']}, 2: {3: ['d'], 4: ['e']}, 3: {4: ['f', 'g']}, 4: {5: ['h']}})
g.show(edge_labels=True)
print(g.adjacency_matrix())
print(g.incidence_matrix())

︡39b5c4cd-b4a7-4c9f-b350-04b408cb60d6︡{"file":{"filename":"/tmp/tmppegk2sn8/tmp_6optqmpp.svg","show":true,"text":null,"uuid":"d6ac50c7-c2e3-4bca-9cc3-25cb13985c63"},"once":false}︡{"stdout":"[1 1 0 1 0]\n[1 0 1 1 0]\n[0 1 0 2 0]\n[1 1 2 0 1]\n[0 0 0 1 0]\n"}︡{"stdout":"[2 1 1 0 0 0 0 0]\n[0 1 0 1 1 0 0 0]\n[0 0 0 1 0 1 1 0]\n[0 0 1 0 1 1 1 1]\n[0 0 0 0 0 0 0 1]\n"}︡{"done":true}
︠794e62ef-190d-4f01-ad88-7e5a3b44c99fs︠
#Vegyük észre, hogy hurokélek csak főátlóban szerepelnek a szomszédsági mátrixban. A Sage konvenciója szerint a hurokélek egy élnek számítanak a szomszédsági mátrixban, de az illeszkedési mátrixban már kettőnek!

#Alapértelmezetten mind a két típusú mátrixban a csúcsok sorrendjét a `g.vertices()` metódus, az élek sorrendjét a `g.edge_iterator()` metódus adja meg, de meg lehet változtatni: a szomszédsági mátrix esetén a `vertices`, az illeszkedési mátrixnál pedig a `vertices` és `edges` paramétereknek lehet listát átadni, például:

g = Graph({1: {1: ['a'], 2: ['b'], 4: ['c']}, 2: {3: ['d'], 4: ['e']}, 3: {4: ['f', 'g']}, 4: {5: ['h']}})
g.show(edge_labels=True)
print(g.adjacency_matrix(vertices=[2, 3, 5, 1, 4]))
E = list(g.edge_iterator())
print(g.incidence_matrix(edges=E[::-1]))
print(g.incidence_matrix(vertices=[2, 3, 5, 1, 4], edges=E[::-1]))

︡106666cb-5043-40be-988f-84c073cece75︡{"file":{"filename":"/tmp/tmppegk2sn8/tmp_cmhbfz0b.svg","show":true,"text":null,"uuid":"87e456cd-87a6-4a32-81d6-32f23c9abd7b"},"once":false}︡{"stdout":"[0 1 0 1 1]\n[1 0 0 0 2]\n[0 0 0 0 1]\n[1 0 0 1 1]\n[1 2 1 1 0]\n"}︡{"stdout":"[0 0 0 0 0 1 1 2]\n[0 0 0 1 1 0 1 0]\n[0 1 1 0 1 0 0 0]\n[1 1 1 1 0 1 0 0]\n[1 0 0 0 0 0 0 0]\n"}︡{"stdout":"[0 0 0 1 1 0 1 0]\n[0 1 1 0 1 0 0 0]\n[1 0 0 0 0 0 0 0]\n[0 0 0 0 0 1 1 2]\n[1 1 1 1 0 1 0 0]\n"}︡{"done":true}
︠014032e0-b5f7-419d-ac39-256d5a6caadcs︠
# Élek és csúcsok

#Egy gráf csúcsait és éleit listázni a `vertices()` és `edges()` metódusokkal lehet.

g = Graph({0: [1,4,5], 1: [2,6], 2: [3,7], 3: [4,8], 4: [9], 5: [7,8], 6: [8,9], 7: [9]})
print(g.vertices())
print(g.edges())

︡8da90cba-6ed3-4325-981d-c0d8990e5bb0︡{"stdout":"[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]\n"}︡{"stdout":"[(0, 1, None), (0, 4, None), (0, 5, None), (1, 2, None), (1, 6, None), (2, 3, None), (2, 7, None), (3, 4, None), (3, 8, None), (4, 9, None), (5, 7, None), (5, 8, None), (6, 8, None), (6, 9, None), (7, 9, None)]\n"}︡{"done":true}
︠c3de971b-4cf6-4b10-9dce-f56eb8355d28s︠
#Egy gráfhoz természetesen lehet élet és csúcsot hozzáadni, illetve törölni. Az alábbi metódusok mindegyike működik irányítatlan és irányított gráfra is.

#Új csúcsot hozzáadni egy gráfhoz az `add_vertex()` vagy `add_vertices()` metódussal lehet. Minden esetben egy izolált csúcs jön létre (azaz nem megy innen él sehova), de ha már létezik az adott címkéjű csúcs, akkor nem történik semmi.

g = Graph({0: [1,4,5], 1: [2,6], 2: [3,7], 3: [4,8], 4: [9], 5: [7,8], 6: [8,9], 7: [9]})
print(g.vertices())
g.add_vertex()
g.add_vertex(11)
print(g.vertices())
g.add_vertices([8, 9, 10, 11, 12, 13])
g.add_vertices(graphs.CompleteGraph(16).vertex_iterator())
print(g.vertices())


︡99cf0719-3f74-45f3-bc0f-5f2df927470a︡{"stdout":"[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]\n"}︡{"stdout":"10\n"}︡{"stdout":"[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11]\n"}︡{"stdout":"[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15]\n"}︡{"done":true}
︠82b4454c-178a-44e7-b67a-a13b173eada4s︠
#Új élet hozzáadni egy gráfhoz az `add_edge()` vagy `add_edges()` metódussal lehet. Ha a paraméterben megadott csúcsok nem léteznek, akkor automatikusan létrejönnek. Ellentétben a csúcs hozzáadásával, ez felülírja a már meglévő éleket.

g = Graph()
g.add_edge(0, 1)
g.add_edge(0, 2, "a")
print(g.edges(sort=True))
g.add_edges([(0, 3, "b"), (1, 3, "c")])
g.show(edge_labels=True)

︡fa5d93c2-cba7-41b9-a6a3-a4dd6ac01cf8︡{"stdout":"[(0, 1, None), (0, 2, 'a')]\n"}︡{"file":{"filename":"/tmp/tmppegk2sn8/tmp_d3gdd491.svg","show":true,"text":null,"uuid":"387f0ff2-b85b-4d9d-bd4d-99618f949f10"},"once":false}︡{"done":true}
︠6c7abd7a-d681-4c8e-a1a0-c047fb7261e1s︠
#Csúcsokat törölni a `delete_vertex()` és `delete_vertices()` metódusokkal lehet. Ilyenkor azok az élek is kitörlődnek, amik a törlendő csúcsba/csúcsból mennek/jönnek ki. Éleket törölni a `delete_edge()` és `delete_edges()` metódusokkal lehet. Mind a négy metódus szintaxisa hasonló a csúcsok és élek hozzáadásához.

#Egy random csúcsot vagy random élet is kérhetünk a `random_vertex()` vagy `random_edge()` metódosuok segítségével.
g = graphs.PetersenGraph()
v = g.random_vertex()
print(v in g)

u, v, l = g.random_edge()
print(g.has_edge(u, v, l))

︡4659fabb-cf3c-4645-9ad8-3bfc18e1cc58︡{"stdout":"True\n"}︡{"stdout":"True\n"}︡{"done":true}
︠cf46e293-8ceb-41bc-9e74-7a297110a76ds︠

# Gráfok tulajdonságai

#Időnként hasznos lehet a gráfok tulajdonságairól információt szerezni.

#Van-e hurokél a gráfban? Ha igen, melyik élek ezek?
g = Graph(loops=True)
print(g.has_loops())
g.add_edge(0, 0)
print(g.has_loops())
print(g.loops())


︡8039f730-5fbd-41c7-b080-6cbeb2c4cede︡{"stdout":"False\n"}︡{"stdout":"True\n"}︡{"stdout":"[(0, 0, None)]\n"}︡{"done":true}
︠40d8d564-20f2-423a-9f97-ee2106eeca44︠
%md

Egy gráf csúcsainak fokszámát a `degree()` metódussal lehet lekérni. Irányított gráf esetén a kimenő és bemenő élek is számítanak. Két paramétert lehet átadni. A `vertices` paraméter lehet egy konkrét csúcs címkéje vagy lista a csúcsok címkéivel. Ha nem adunk át neki semmit (`None`, alapértelmezetten), akkor az összes csúcs fokszámát adja vissza listában. A `labels` egy logikai paraméter: ha `True`, akkor egy dictionary-vel tér vissza, amiben a `vertices` paraméterben megadott csúcsok címkéit képezi le azok fokszámára; ha `False` (alapértelmezetten), akkor a `vertices` paraméterben megadott csúcsok fokszámainak listájával tér vissza.


︡2c8e85fb-22be-49df-b3c0-9e76e83e16a2︡{"done":true,"md":"\nEgy gráf csúcsainak fokszámát a `degree()` metódussal lehet lekérni. Irányított gráf esetén a kimenő és bemenő élek is számítanak. Két paramétert lehet átadni. A `vertices` paraméter lehet egy konkrét csúcs címkéje vagy lista a csúcsok címkéivel. Ha nem adunk át neki semmit (`None`, alapértelmezetten), akkor az összes csúcs fokszámát adja vissza listában. A `labels` egy logikai paraméter: ha `True`, akkor egy dictionary-vel tér vissza, amiben a `vertices` paraméterben megadott csúcsok címkéit képezi le azok fokszámára; ha `False` (alapértelmezetten), akkor a `vertices` paraméterben megadott csúcsok fokszámainak listájával tér vissza."}
︠108b2af3-fd31-4c10-a6fa-4b5c893b3d42s︠

g = Graph({1: [1, 2, 4], 2: [3, 4], 3: [4, 4], 4: [5]})
print(g.degree())
print(g.degree(2))
print(g.degree([1, 4]))
print(g.degree([1, 4], labels=True))
︡6ca2c9d8-855d-416a-abe7-e720b9d4cb4d︡{"stdout":"[4, 3, 3, 5, 1]\n"}︡{"stdout":"3\n"}︡{"stdout":"[4, 5]\n"}︡{"stdout":"{1: 4, 4: 5}\n"}︡{"done":true}
︠ea5ffd53-79a6-4e29-817d-82759f8c71d9s︠
︡6b51ffcb-8d72-48c3-804e-4230e38008af︡{"done":true}
︠e09dd291-8e33-466c-b5b6-b461dd376f56s︠
# Algoritmusok gráfokon

#Bizonyos algoritmusok implementációja elérhető Sageben. Itt most csak néhányat mutatunk be, a többi elérhető a [dokumentációban]#(https://doc.sagemath.org/html/en/reference/graphs/index.html).

%md

# Euler-kör, Euler-út

Euler-kört és Euler-utat az `eulerian_circuit()` metódussal lehet keresni, ami egy Euler-körben lévő élek listájával tér vissza. Ha nem létezik Euler-kör, a metódus visszatérési értéke `False` lesz. A `return_vertices` paraméterrel a függvény a körben lévő csúcsokkal is visszatér. A `labels` paraméter, ha `True` (alapértelmezetten), kiírja az élek címkéit is.

︡2d9d5558-3266-473b-8ca7-5f1244adbba5︡{"md":"\n# Euler-kör, Euler-út\n\nEuler-kört és Euler-utat az `eulerian_circuit()` metódussal lehet keresni, ami egy Euler-körben lévő élek listájával tér vissza. Ha nem létezik Euler-kör, a metódus visszatérési értéke `False` lesz. A `return_vertices` paraméterrel a függvény a körben lévő csúcsokkal is visszatér. A `labels` paraméter, ha `True` (alapértelmezetten), kiírja az élek címkéit is.\n"}︡{"done":true}
︠1073f9db-1675-4311-a05e-576a64c69444s︠

g = graphs.CompleteGraph(5)
print("Egy Euler-kör:")
print(g.eulerian_circuit())
print(g.eulerian_circuit(labels=False))
c, v = g.eulerian_circuit(return_vertices=True)
print(v)

print("Ebben a gráfban nincs Euler-kör")
print(graphs.CompleteGraph(4).eulerian_circuit())

#Euler-utat a `path` paraméterrel lehet kérni (`False` alapértelmezetten).


︡90feab00-b7f3-41f8-a4ac-5621291cfadf︡{"stdout":"Egy Euler-kör:\n"}︡{"stdout":"[(0, 4, None), (4, 3, None), (3, 2, None), (2, 4, None), (4, 1, None), (1, 3, None), (3, 0, None), (0, 2, None), (2, 1, None), (1, 0, None)]\n"}︡{"stdout":"[(0, 4), (4, 3), (3, 2), (2, 4), (4, 1), (1, 3), (3, 0), (0, 2), (2, 1), (1, 0)]\n"}︡{"stdout":"[0, 4, 3, 2, 4, 1, 3, 0, 2, 1, 0]\n"}︡{"stdout":"Ebben a gráfban nincs Euler-kör\n"}︡{"stdout":"False\n"}︡{"done":true}
︠3019bfc8-05ae-451f-b4c3-a3367a37f036s︠

g = graphs.CompleteGraph(5)
print(g.eulerian_circuit(path=True))


︡893f144b-74c1-4863-80df-aa14eaf699a6︡{"stdout":"False\n"}︡{"done":true}
︠de92cdfc-f832-4dc4-b4a2-d77865c24481︠
%md

## Hamilton-út, Hamilton-kör

Egy gráf Hamilton-útja az az út, ami minden csúcson pontosan egyszer megy át. Mivel ez NP-nehéz probléma, a gráftól erősen függ a futási idő.

Hamilton-utat a `hamiltonian_path()` metódussal lehet keresni. Opcionálisan megadható egy kezdő és végpont is. Ha nem létezik Hamilton-út, a metódus visszatérési értéke `None` lesz.

︡31fafc69-4a8f-49a4-8690-e712f9708cc0︡{"done":true,"md":"\n## Hamilton-út, Hamilton-kör\n\nEgy gráf Hamilton-útja az az út, ami minden csúcson pontosan egyszer megy át. Mivel ez NP-nehéz probléma, a gráftól erősen függ a futási idő.\n\nHamilton-utat a `hamiltonian_path()` metódussal lehet keresni. Opcionálisan megadható egy kezdő és végpont is. Ha nem létezik Hamilton-út, a metódus visszatérési értéke `None` lesz."}
︠867b7710-1aca-45ec-8a49-a657be831e34s︠

g = Graph({0: [1, 2, 3], 1: [2, 4], 2: [3, 4]})
g.show()
g.hamiltonian_path().show()


︡54c1f954-b86f-4909-8394-cbbc5c79ff7d︡{"file":{"filename":"/tmp/tmppegk2sn8/tmp_42ew86eo.svg","show":true,"text":null,"uuid":"f20b1271-c86d-473c-9f11-ff9bee57c427"},"once":false}︡{"file":{"filename":"/tmp/tmppegk2sn8/tmp_4r7fyun1.svg","show":true,"text":null,"uuid":"c4798814-073a-40a5-8a97-7a958c093b90"},"once":false}︡{"done":true}
︠6bc31f84-2f2a-4639-a2e2-99f58da5dcdd︠
%md

Egy gráf Hamilton-köre az a kör, ami minden csúcsot érint. Akárcsak a Hamilton-út, ez is NP-nehéz probléma.

Hamilton-kört a `hamiltonian_cycle()` metódussal lehet keresni. Ha a gráfnak nem létezik Hamilton-köre, akkor a metódus `EmptySetError` kivételt dob.


︡8e8c3aca-c8e8-4464-a4bb-758458f90761︡{"done":true,"md":"\nEgy gráf Hamilton-köre az a kör, ami minden csúcsot érint. Akárcsak a Hamilton-út, ez is NP-nehéz probléma.\n\nHamilton-kört a `hamiltonian_cycle()` metódussal lehet keresni. Ha a gráfnak nem létezik Hamilton-köre, akkor a metódus `EmptySetError` kivételt dob."}
︠df128a12-d75e-49be-a315-22389e6e66f7s︠

g = graphs.RandomRegular(4, 7)
print(g.hamiltonian_cycle())
#g = graphs.PetersenGraph()
#print(g.hamiltonian_cycle())
︡d610e6aa-a885-48bd-862e-41862742d8c3︡{"stdout":"TSP from \n"}︡{"done":true}
︠0df82f50-c463-4b2e-b7bb-9ae41d6d2a25s︠

︡1127c84f-b92f-4943-acf6-1e0b02914305︡{"done":true}
︠a1378a6f-2f24-4906-8d4e-e4b46f30ee80︠

%md

### Feladat

 Legyen $V = \{2, 3, \dots, 9\}$ egy gráf csúcsainak halmaza. Készítsük el azt az irányítatlan gráfot, amiben két csúcsot pontosan akkor köt össze egy él, ha a csúcsok *relatív prímek*!
︡3ba5c987-2e8b-47a0-97c7-e0cfdaa02b78︡{"done":true,"md":"\n### Feladat\n\n Legyen $V = \\{2, 3, \\dots, 9\\}$ egy gráf csúcsainak halmaza. Készítsük el azt az irányítatlan gráfot, amiben két csúcsot pontosan akkor köt össze egy él, ha a csúcsok *relatív prímek*!"}
︠c0d82553-71ae-4ea0-9161-f4b8a6d37469︠
def lnko(x,y):
    return gcd(x,y)==1

Graph([[2..9], lambda u, v: gcd(u,v)==1]).plot()
︡ce2abe87-6d66-49b6-920b-8dfbbdd35513︡{"file":{"filename":"/tmp/tmpsqhra6hm/tmp_0_bn7u4q.svg","show":true,"text":null,"uuid":"b5ba5f5b-e33a-4927-a101-0bc239ba70ff"},"once":false}︡{"done":true}









