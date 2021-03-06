package com.toastedbits.gfg.graphs;

import com.toastedbits.gfg.graphs.common.Graph;
import com.toastedbits.gfg.graphs.common.Graphs;
import com.toastedbits.gfg.graphs.common.algorithms.GraphTransitiveClosure;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

//Transitive Closure of a Graph Using DFS
//https://www.geeksforgeeks.org/transitive-closure-of-a-graph-using-dfs/

@Slf4j
public class GFG8 {
    @ParameterizedTest
    @MethodSource("createGraphs")
    public void testTransitiveClosure(@NonNull final Graph graph) {
        log.info("Graph {}\n{}", graph.getClass(), graph);
        boolean[][] reachability = GraphTransitiveClosure.transitiveClosure(graph);
        boolean[][] expected = new boolean[][] {
            {true, true, true, true},
            {true, true, true, true},
            {true, true, true, true},
            {false, false, false, true}
        };
        log.info("Reachability\n{}", booleanMatrixToString(reachability));

        assertThat(expected, equalTo(reachability));
    }

    private String booleanMatrixToString(boolean[][] matrix) {
        StringBuilder sb = new StringBuilder();
        for (boolean[] row : matrix) {
            for (boolean cell : row) {
                sb.append(cell ? "1 " : "0 ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private static Stream<Graph> createGraphs() {
        return Stream.of(
            addData(Graphs.adjacencyListGraph()),
            addData(Graphs.adjacencyMatrixGraph()),
            addData(Graphs.adjacencyHashGraph())
        );
    }

    private static Graph addData(Graph graph) {
        graph.setEdge(0, 1, 1);
        graph.setEdge(0, 2, 1);
        graph.setEdge(1, 2, 1);
        graph.setEdge(2, 0, 1);
        graph.setEdge(2, 3, 1);
        return graph;
    }
}
