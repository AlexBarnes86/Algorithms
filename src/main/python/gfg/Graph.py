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