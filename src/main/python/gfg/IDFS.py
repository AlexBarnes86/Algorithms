from Graph import Graph

def idfs(v, fun, graph, max_depth, visited=None, cur_depth=0):
    if not visited:
        visited = set()

    if (cur_depth > max_depth) or (v in visited):
        return
    visited.add(v)
    fun(v)

    for adj in graph.adjacent(v):
        idfs(adj, fun, graph, max_depth, visited, cur_depth+1)

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
    idfs(7, lambda v: print(v, end=" "), g, 5)

test1()