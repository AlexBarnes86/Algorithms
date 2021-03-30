from Graph import Graph
from random import randint

def count_paths(graph, v1, v2, visited, memo):
    if v1 in visited:
        #Protect against cycles
        return 0

    if v1 in memo:
        #Speed up computation by saving count of paths for subproblems
        return memo[v1]

    sum = 0
    for adj in graph.adjacent(v1):
        if adj == v2:
            sum += 1
        else:
            nvisit = []
            nvisit.extend(visited)
            nvisit.append(v1)
            sum += count_paths(graph, adj, v2, nvisit, memo)

    memo[v1] = sum
    return sum


def test1():
    print("== Test 1 ==")
    g = Graph()
    g.add_vertex(0, {1, 4})
    g.add_vertex(1, {2, 4})
    g.add_vertex(2, {3, 4})
    g.add_vertex(3, {5})
    g.add_vertex(4, {3, 7})
    g.add_vertex(5, {})
    g.add_vertex(6, {5})
    g.add_vertex(7, {9})
    g.add_vertex(8, {})
    g.add_vertex(9, {10, 14})
    g.add_vertex(10, {13, 14})
    g.add_vertex(11, {10, 13, 14})
    g.add_vertex(12, {11, 13, 14})
    g.add_vertex(13, {})
    g.add_vertex(14, {13})
    g.add_vertex(15, {13})
    print(count_paths(g, 0, 13, [], dict()))

test1()


def mygcd(a, b):
    x, y = (a, b) if a < b else (b, a)
    if x == 0:
        return y
    elif y == 0:
        return x
    else:
        return mygcd(x, y % x)

for i in range(100):
    a = randint(1, 10000)
    b = randint(1, 10000)
    print("gcd({}, {}) = {}".format(a, b, mygcd(a, b)))
