︠4accf035-570b-4419-b008-5448683edf26s︠
def illustrate_graph(m):
    vertices = []
    # create values of the vertices
    for a in range(2, m+1):
        for b in range(a+1, m+1):
            if gcd(a, b) == 1:
                vertices.append((a, b))

    # instantiate the graph
    G = DiGraph()
    G.add_vertices(vertices)

    # create the edges
    for (a, b) in vertices:
        for (d, e) in vertices:
            if (a + b >= d * e - (abs(d - e) * 2)) and ((a, b) != (d, e)): # last check is for preventing loops (which are not allowed)
                G.add_edge((a, b), (d, e))

    return G

graph = illustrate_graph(6)
graph.show()
︡7c50611a-8d90-4766-81c0-295ab0a4bf60︡{"file":{"filename":"/tmp/tmpz7c9fg3y/tmp_700bpbvy.svg","show":true,"text":null,"uuid":"1bd3a57f-ed21-4684-9581-5522ec49c4b0"},"once":false}︡{"done":true}









