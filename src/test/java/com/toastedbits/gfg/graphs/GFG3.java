package com.toastedbits.gfg.graphs;

import com.toastedbits.gfg.graphs.common.Graph;
import com.toastedbits.gfg.graphs.common.Graphs;
import com.toastedbits.gfg.graphs.common.algorithms.GraphDFS;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

//Depth First Traversal for a Graph
//https://www.geeksforgeeks.org/depth-first-traversal-for-a-graph/

//Applications of Depth First Search
//https://www.geeksforgeeks.org/applications-of-depth-first-search/

@Slf4j
public class GFG3 {
    @ParameterizedTest
    @MethodSource("createGraphs")
    public void testDfs(@NonNull final Graph graph) {
        log.info("Graph DFS {}\n{}", graph.getClass(), graph);
        final List<Integer> visited = new ArrayList<>();
        GraphDFS.dfs(graph, 0, i -> {
            visited.add(i);
            log.info("{}", i);
        });
        assertThat(visited.get(0), equalTo(0));
        assertThat(visited.indexOf(5), lessThan(visited.indexOf(visited.get(1) == 1 ? 2 : 1)));
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
