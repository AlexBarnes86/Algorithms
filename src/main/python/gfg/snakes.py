from Graphs import *

size = 7
graph = Graph(AdjListGraphStore, size)
graph.set_weight(0, 1, 1)
graph.set_weight(1, 2, 1)
graph.set_weight(1, 3, 1)
graph.set_weight(2, 4, 1)
graph.set_weight(2, 5, 1)
graph.set_weight(3, 5, 1)
graph.set_weight(4, 5, 1)
graph.set_weight(4, 6, 1)
graph.set_weight(5, 6, 1)
graph.reflect_edges()

print("graph")
print(graph)
print("BFS from 1")
print("\n".join(["{}: {} -> {}".format(res[0], res[1], res[2]) for res in graph.bfs(1)]))
print("DFS from 1")
print("\n".join(["{}: {} -> {}".format(res[0], res[1], res[2]) for res in graph.dfs(1)]))
print("Mother Vertex:", graph.find_mother_vertex())

size = 7
graph = Graph(AdjListGraphStore, size)
graph.set_weight(0, 1, 1)
graph.set_weight(0, 2, 1)
graph.set_weight(1, 3, 1)
graph.set_weight(4, 1, 1)
graph.set_weight(6, 4, 1)
graph.set_weight(5, 6, 1)
graph.set_weight(5, 2, 1)
graph.set_weight(6, 0, 1)
print(graph)
for i in range(size):
    print("Is ", i, " a Mother Vertex: ", graph.is_mother_vertex(i))

print("Transitive Closure graph")
size = 4
g = Graph(AdjListGraphStore, size)
g.set_weight(0, 0, 1)
g.set_weight(0, 1, 1)
g.set_weight(0, 3, 1)
g.set_weight(1, 1, 1)
g.set_weight(1, 2, 1)
g.set_weight(2, 2, 1)
g.set_weight(2, 3, 1)
g.set_weight(3, 3, 1)


def print_graph(m):
    for row in m:
        print(", ".join([str(s) for s in row]))

print(g)
print("Closure")
print_graph(g.transitive_closure_fw())

print("Closure v2")
print_graph(g.transitive_closure())