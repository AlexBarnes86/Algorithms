from Graph import Graph


class BfsNode:
    def __init__(self, index, depth):
        self.index = index
        self.depth = depth


def bfs_count(graph, v=0):
    queue = [BfsNode(v, 0)]
    depth = {}
    while queue:
        node = queue.pop(0)
        if node.depth not in depth:
            depth[node.depth] = []
        depth[node.depth].append(node.index)

        expanse = map(lambda n: BfsNode(n, node.depth+1), graph.get_edges(node.index) or [])
        queue.extend(expanse)

    return depth


g = Graph()
g.add_vertex(0, {1, 2})
g.add_vertex(1, {3, 4, 5})
g.add_vertex(2, {6})


for layer, verts in bfs_count(g).items():
    print("{}: {} -> {}".format(layer, len(verts), verts))