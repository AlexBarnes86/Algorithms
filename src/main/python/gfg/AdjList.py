class AdjList:
    def __init__(self, size):
        self.adjList = [[] for x in range(size)]

    def set_weight(self, from_vert_idx, to_vert_idx, weight):
        for x in self.adjList[from_vert_idx]:
            if x[0] == to_vert_idx:
                x[1] = weight
                break
        else:
            self.adjList[from_vert_idx].append([to_vert_idx, weight])

    def get_weight(self, from_vert_idx, to_vert_idx):
        for vert in self.adjList[from_vert_idx]:
            if vert[0] == to_vert_idx:
                return vert[1]
        return 0

    def remove_edge(self, from_vert_idx, to_vert_idx):
        for x in self.adjList[from_vert_idx]:
            if x[0] == to_vert_idx:
                self.adjList[from_vert_idx].remove(x)

    def __str__(self):
        return '\n'.join([str(idx) + ":" + ','.join(['(to: {}, weight: {})'.format(entry[0], entry[1]) for entry in row]) for idx, row in enumerate(self.adjList)])