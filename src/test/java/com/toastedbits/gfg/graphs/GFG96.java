package com.toastedbits.gfg.graphs;

//Bellman-Ford Algorithm
//https://www.geeksforgeeks.org/bellman-ford-algorithm-dp-23/

import com.google.common.collect.ImmutableList;
import com.toastedbits.gfg.graphs.common.Edges;
import com.toastedbits.gfg.graphs.common.Graph;
import com.toastedbits.gfg.graphs.common.Graphs;
import com.toastedbits.gfg.graphs.common.SDWEdge;
import com.toastedbits.gfg.graphs.common.algorithms.GraphBellmanFordsShortestPaths;
import com.toastedbits.gfg.graphs.common.Edges;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

@Slf4j
public class GFG96 {
    @ParameterizedTest
    @MethodSource("createGraphs")
    public void testBellmanFord(@NonNull final Graph graph) {
        log.debug("Graph {}:\n{}", graph.getClass(), graph);
        List<Integer> shortestPaths = GraphBellmanFordsShortestPaths.pathCost(graph, 0);
        log.debug("Shortest Paths: {}", shortestPaths);
        assertThat(shortestPaths, equalTo(ImmutableList.of(0, -1, 2, -2, 1)));
    }

    //Ensure all the edges we add to the graph are properly returned by getAllEdges
    @ParameterizedTest
    @MethodSource("createGraphs")
    public void testEdges(@NonNull final Graph graph) {
        List<SDWEdge> testEdges = allTestEdges();
        List<SDWEdge> graphEdges = graph.getAllEdges();

        Set<SDWEdge> testEdgeSet = new HashSet<>(testEdges);
        Set<SDWEdge> graphEdgeSet = new HashSet<>(graphEdges);
        assertThat(testEdgeSet, equalTo(graphEdgeSet));

        assertThat(testEdges.size(), equalTo(graphEdges.size()));
    }

    private static Stream<Graph> createGraphs() {
        return Stream.of(
            addData(Graphs.adjacencyListGraph()),
            addData(Graphs.adjacencyMatrixGraph()),
            addData(Graphs.adjacencyHashGraph())
        );
    }

    private static List<SDWEdge> allTestEdges() {
        //A = 0, B = 1, C = 2, D = 3, E = 4
        List<SDWEdge> edges = new ArrayList<>();
        edges.add(Edges.weighted(0, 1, -1));
        edges.add(Edges.weighted(0, 2, 4));
        edges.add(Edges.weighted(1, 2, 3));
        edges.add(Edges.weighted(1, 3, 2));
        edges.add(Edges.weighted(1, 4, 2));
        edges.add(Edges.weighted(3, 1, 1));
        edges.add(Edges.weighted(3, 2, 5));
        edges.add(Edges.weighted(4, 3, -3));
        return edges;
    }

    private static Graph addData(@NonNull final Graph graph) {
        for(SDWEdge edge : allTestEdges()) {
            graph.setEdge(edge.getSrc(), edge.getDest(), edge.getWeight());
        }

        return graph;
    }
}
