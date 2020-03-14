package com.toastedbits.gfg.graphs;

import com.toastedbits.gfg.graphs.common.Graph;
import com.toastedbits.gfg.graphs.common.Graphs;
import com.toastedbits.gfg.graphs.common.algorithms.GraphKCore;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GFG9 {
    private static void addData1(Graph graph) {
        graph.addUndirectedEdge(0, 1);
        graph.addUndirectedEdge(0, 2);
        graph.addUndirectedEdge(1, 2);
        graph.addUndirectedEdge(1, 5);
        graph.addUndirectedEdge(2, 3);
        graph.addUndirectedEdge(2, 4);
        graph.addUndirectedEdge(2, 5);
        graph.addUndirectedEdge(2, 6);
        graph.addUndirectedEdge(3, 4);
        graph.addUndirectedEdge(3, 6);
        graph.addUndirectedEdge(3, 7);
        graph.addUndirectedEdge(4, 7);
        graph.addUndirectedEdge(5, 6);
        graph.addUndirectedEdge(5, 8);
        graph.addUndirectedEdge(6, 7);
        graph.addUndirectedEdge(6, 8);
    }

    private static void test(Graph graph) {
        addData1(graph);
        log.info("Graph {}\n{}", graph.getClass(), graph);
        GraphKCore.kCore(graph, 3); //2, 3, 4, 6, 7
        log.info("3-Core\n{}", graph);
    }

    public static void main(@NonNull final String[] args) {
        test(Graphs.adjacencyMatrixGraph());
        test(Graphs.adjacencyListGraph());
        test(Graphs.adjacencyHashGraph());
    }
}
