package com.toastedbits.gfg.graphs;

import com.toastedbits.gfg.graphs.common.Graph;
import com.toastedbits.gfg.graphs.common.Graphs;
import com.toastedbits.gfg.graphs.common.algorithms.GraphBFS;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

//Breadth First Traversal for a Graph
//https://www.geeksforgeeks.org/breadth-first-traversal-for-a-graph/

//Applications of Breadth First Traversal
//https://www.geeksforgeeks.org/applications-of-breadth-first-traversal/

@Slf4j
public class GFG2 {
    @ParameterizedTest
    @MethodSource("createGraphs")
    public void testBfs(@NonNull final Graph graph) {
        log.info("Graph BFS {}\n{}", graph.getClass(), graph);
        final List<Integer> visited = new ArrayList<>();
        GraphBFS.bfs(graph, 0, e -> {
            visited.add(e.getDest());
            log.info("{}", e.getDest());
        });
        assertThat(visited.get(0), equalTo(0));
        assertThat(visited.get(5), equalTo(5));
        assertThat(visited.get(1), equalTo(visited.get(2) == 1 ? 2 : 1));
        assertThat(visited.get(2), equalTo(visited.get(1) == 1 ? 2 : 1));
        assertThat(visited, not((contains(6))));
    }

    /*
          0
        /   \
     <--     -->
    1           2
    |\          +
    | \___      |           +--+
    |     \__   |           |  |
    v        \  v           v  |
    3+------>-->4           6+-+
    +           +
    |           |
    |           |
    +---->5<----+
*/
    private static Graph addData(@NonNull final Graph graph) {
        graph.setEdge(0, 1, 1);
        graph.setEdge(0, 2, 1);
        graph.setEdge(1, 3, 1);
        graph.setEdge(1, 4, 1);
        graph.setEdge(2, 4, 1);
        graph.setEdge(3, 4, 1);
        graph.setEdge(3, 5, 1);
        graph.setEdge(4, 5, 1);
        graph.setEdge(6, 6, 1);

        return graph;
    }

    private static Stream<Graph> createGraphs() {
        return Stream.of(
            addData(Graphs.adjacencyListGraph()),
            addData(Graphs.adjacencyMatrixGraph()),
            addData(Graphs.adjacencyHashGraph())
        );
    }
}
