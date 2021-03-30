from operator import add
from abc import abstractmethod

class GraphStore:
    @abstractmethod
    def set_weight(self, from_vert_idx, to_vert_idx, weight):
        pass

    @abstractmethod
    def get_weight(self, from_vert_idx, to_vert_idx):
        pass

    @abstractmethod
    def adjacent(self, index):
        pass

class AdjMatrixGraphStore(GraphStore):
    def __init__(self, size):
        self.size = size
        self.adj_matrix = [[0 for x in range(size)] for y in range(size)]

    def set_weight(self, from_vert_idx, to_vert_idx, weight):
        self.adj_matrix[from_vert_idx][to_vert_idx] = weight

    def get_weight(self, from_vert_idx, to_vert_idx):
        return self.adj_matrix[from_vert_idx][to_vert_idx]

    def adjacent(self, idx):
        vert_weights = [vert_weight for vert_weight in enumerate(self.adj_matrix[idx])]
        for edge in filter(lambda e: e[1] != 0, vert_weights):
            yield edge

class AdjListGraphStore(GraphStore):
    def __init__(self, size):
        self.size = size
        self.adj_list = dict()

    def set_weight(self, from_vert_idx, to_vert_idx, weight):
        if weight == 0:
            return

        if from_vert_idx not in self.adj_list:
            self.adj_list[from_vert_idx] = set()
        self.adj_list[from_vert_idx].add((to_vert_idx, weight))

    def get_weight(self, from_vert_idx, to_vert_idx):
        if from_vert_idx not in self.adj_list:
            return 0
        for adj, weight in self.adj_list[from_vert_idx]:
            if adj == to_vert_idx:
                return weight
        return 0

    def adjacent(self, idx):
        if idx in self.adj_list:
            for idx, vert_weight in enumerate(self.adj_list[idx]):
                yield vert_weight

class Graph(GraphStore):
    def __init__(self, store, size):
        self.store = store(size)
        self.size = size

    def set_weight(self, from_vert_idx, to_vert_idx, weight):
        self.store.set_weight(from_vert_idx, to_vert_idx, weight)

    def get_weight(self, from_vert_idx, to_vert_idx):
        return self.store.get_weight(from_vert_idx, to_vert_idx)

    def adjacent(self, idx):
        for edge in self.store.adjacent(idx):
            yield edge

    def __str__(self):
        return '\n'.join([''.join(['{:4}'.format(item) for item in row]) for row in self.to_matrix()])

    def reflect_edges(self, op=add):
        """
        Make the left lower and right upper segments equal each other (relative to the diagonal)
        Useful for turning a unidirectional graph into a bidirectional graph, may provide your own operator function
        to determine the resulting value between reflected (transposed) edges
        :param op:
        :return:
        """
        for i in range(self.size):
            for j in range(i, self.size):
                val = op(self.get_weight(i, j), self.get_weight(j, i))
                self.set_weight(i, j, val)
                self.set_weight(j, i, val)

    def to_matrix(self):
        matrix = [[0 for i in range(self.size)] for j in range(self.size)]
        for i in range(self.size):
            for j, weight in self.adjacent(i):
                matrix[i][j] = weight
        return matrix

    def bfs(self, start=0):
        """
        Iterates through the graph in breadth first manner without using recursion
        Mark node visited as soon as we pop it off and add each adjacent node to the queue
        only if it is not already present in the queue to ensure proper traversal (shallower nodes fully evaluated first)
        :param start:
        :return:
        """
        visited = [False] * self.size
        successor = ["x"] * self.size
        successor[start] = "s"
        queue = [start]
        ct = 0
        while queue:
            node = queue.pop(0)
            if visited[node]:
                continue
            visited[node] = True
            yield (ct, successor[node], node)
            ct += 1
            for idx, weight in self.adjacent(node):
                if not visited[idx]:
                    if idx not in queue:
                        queue.append(idx)
                        successor[idx] = node

    def dfs(self, start=0):
        """
        Iterates through the graph in a depth first manner without using recursion
        On each step add all adjacent to the stack, but only mark node as visited afterwards
        DFS does not care if the node already exists in the queue or not,
        iterate deeper first without regard to who else may be adjacent to the node
        :param start:
        :return:
        """
        visited = [False] * self.size
        successor = ["x"] * self.size
        successor[start] = "s"
        stack = [start]
        ct = 0
        while stack:
            node = stack.pop()
            if visited[node]:
                continue
            yield (ct, successor[node], node)
            ct += 1
            for idx, weight in self.adjacent(node):
                if not visited[idx]:
                    successor[idx] = node
                    stack.append(idx)
            visited[node] = True

    def dfs_visit(self, idx, visited):
        visited[idx] = True
        for i, adj in self.adjacent(idx):
            if not visited[i]:
                self.dfs_visit(i, visited)

    def is_mother_vertex(self, idx):
        visited = [False] * self.size
        self.dfs_visit(idx, visited)
        return all(visited)

    def find_mother_vertex(self):
        visited = [False] * self.size
        mother_vertex = 0
        for i in range(self.size):
            if not visited[i]:
                self.dfs_visit(i, visited)
                mother_vertex = i

        return mother_vertex if self.is_mother_vertex(mother_vertex) else None

    def transitive_closure_fw(self):
        reach = self.to_matrix()
        for k in range(self.size):
            for i in range(self.size):
                for j in range(self.size):
                    reach[i][j] = reach[i][j] or (reach[i][k] and reach[k][j])
        return reach

    def _reachable_dfs(self, reachable, s, v):
        reachable[s][v] = 1
        for idx, weight in self.adjacent(v):
            if reachable[s][idx] == 0:
                self._reachable_dfs(reachable, s, idx)

    def transitive_closure(self):
        reachable = [[0 for j in range(self.size)] for i in range(self.size)]
        for i in range(self.size):
            self._reachable_dfs(reachable, i, i)
        return reachable