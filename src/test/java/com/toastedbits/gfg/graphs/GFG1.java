package com.toastedbits.gfg.graphs;

import com.toastedbits.gfg.graphs.common.DWEdge;
import com.toastedbits.gfg.graphs.common.Graph;
import com.toastedbits.gfg.graphs.common.Graphs;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;

//Graph and its representations
//https://www.geeksforgeeks.org/graph-and-its-representations/

//Graph representations using set and hash
//https://www.geeksforgeeks.org/graph-representations-using-set-hash/

@Slf4j
public class GFG1 {
    private static Stream<Graph> createGraphs() {
        return Stream.of(
            addData(Graphs.adjacencyListGraph()),
            addData(Graphs.adjacencyMatrixGraph()),
            addData(Graphs.adjacencyHashGraph())
        );
    }

    @ParameterizedTest
    @MethodSource("createGraphs")
    public void testSimpleGraph(@NonNull final Graph graph) {
        log.info("Graph: {}\n{}", graph.getClass(), graph.toString());

        assertThat(5, is(graph.getSize()));
        assertThat(graph.getAdjacentEdges(0).stream().map(DWEdge::getDest).collect(toList()), hasItems(1, 4));
        assertThat(graph.getAdjacentEdges(1).stream().map(DWEdge::getDest).collect(toList()), hasItems(0, 2, 3, 4));
        assertThat(graph.getAdjacentEdges(2).stream().map(DWEdge::getDest).collect(toList()), hasItems(1, 3));
        assertThat(graph.getAdjacentEdges(3).stream().map(DWEdge::getDest).collect(toList()), hasItems(1, 2, 4));
        assertThat(graph.getAdjacentEdges(4).stream().map(DWEdge::getDest).collect(toList()), hasItems(0, 1, 3));
    }

    private static Graph addData(@NonNull final Graph graph) {
        graph.setEdge(0, 1, 1);
        graph.setEdge(0, 4, 1);
        graph.setEdge(1, 0, 1);
        graph.setEdge(1, 4, 1);
        graph.setEdge(1, 2, 1);
        graph.setEdge(1, 3, 1);
        graph.setEdge(2, 1, 1);
        graph.setEdge(2, 3, 1);
        graph.setEdge(3, 1, 1);
        graph.setEdge(3, 4, 1);
        graph.setEdge(3, 2, 1);
        graph.setEdge(4, 3, 1);
        graph.setEdge(4, 0, 1);
        graph.setEdge(4, 1, 1);

        return graph;
    }
}
