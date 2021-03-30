#The solution/implementation using DFS on geeksforgeeks has edge case failures
#This implementation is an unoptimized implementaiton based on http://vlado.fmf.uni-lj.si/pub/networks/doc/cores/cores.pdf

class Graph:
    def __init__(self):
        self.V = dict()

    def add_vertex(self, v1, edges=None):
        self.V[v1] = edges or set()

    def add_edges(self, v1, edges):
        if not self.V[v1]:
            self.V[v1] = set()
        self.V[v1].union(edges)

    def remove_vertex(self, v):
        self.V.pop(v, None)
        for vert, edges in self.V.items():
            edges.discard(v)

    def remove_edges(self, v, edges):
        self.V[v].discard(edges)

    def adjacent(self, v):
        for adj in self.V[v]:
            yield adj

    def get_edges(self, v):
        if v in self.V:
            return self.V[v]
        else:
            return None

    def get_degrees(self):
        return {vert: len(edges) for vert, edges in self.V.items()}

    def __len__(self):
        return max(self.V.keys())

    def __iter__(self):
        for v in self.V.keys():
            yield (v, self.V[v])

    def __str__(self):
        res = []
        for v in self.V.keys():
            res.append("{}: {}".format(v, self.V[v]))
        return ", ".join(res)


def kCores(graph):
    vdegree = graph.get_degrees()
    order = sorted([(i, d) for i, d in vdegree.items()], key=lambda e: e[1])

    k = 0
    kcores = dict()
    rem_list = []

    while order:
        index = order[0][0]
        degree = order[0][1]
        if degree <= k:
            rem_list.append(order.pop(0))
            graph.remove_vertex(index)
            vdegree = graph.get_degrees()
            order = sorted([(i, d) for i, d in vdegree.items()], key=lambda e: e[1])
        else:
            kcores[k] = sorted([i for i, d in rem_list])
            k += 1
            rem_list = []

    if len(rem_list) > 0:
        kcores[k] = sorted([i for i, d in rem_list])

    for i in kcores.keys():
        for j in range(i+1, k+1):
            kcores[i].extend(kcores[j])

    return kcores

k = 3
def test1():
    print("== Test 1 ==")
    g = Graph()
    g.add_vertex(0, {1, 4})
    g.add_vertex(1, {0, 2, 3, 4})
    g.add_vertex(2, {1, 3, 4})
    g.add_vertex(3, {1, 2, 4, 5})
    g.add_vertex(4, {0, 1, 2, 3, 7})
    g.add_vertex(5, {3, 6})
    g.add_vertex(6, {5})
    g.add_vertex(7, {4, 9})
    g.add_vertex(8, {})
    g.add_vertex(9, {7, 10, 14})
    g.add_vertex(10, {9, 11, 12, 13, 14})
    g.add_vertex(11, {10, 12, 13, 14})
    g.add_vertex(12, {10, 11, 13, 14})
    g.add_vertex(13, {10, 11, 12, 14, 15})
    g.add_vertex(14, {9, 10, 11, 12, 13})
    g.add_vertex(15, {13})

    print("Graph: {}".format(g))
    for k, core in kCores(g).items():
        print("{}-core: {}".format(k, core))

def test2():
    print("== Test 2 ==")
    g = Graph()
    g.add_vertex(0, {4})
    g.add_vertex(1, {2, 3, 4, 5})
    g.add_vertex(2, {1, 5, 7, 8})
    g.add_vertex(3, {1, 7 , 8})
    g.add_vertex(4, {0, 1})
    g.add_vertex(5, {1, 2})
    g.add_vertex(6, {})
    g.add_vertex(7, {2, 3, 8})
    g.add_vertex(8, {2, 3, 7})

    print("Graph: {}".format(g))
    for k, core in kCores(g).items():
        print("{}-core: {}".format(k, core))

test1()
test2()