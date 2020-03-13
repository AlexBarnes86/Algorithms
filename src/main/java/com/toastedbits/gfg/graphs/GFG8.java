package com.toastedbits.gfg.graphs;

import com.toastedbits.gfg.graphs.common.Graph;
import com.toastedbits.gfg.graphs.common.Graphs;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GFG8 {
    private static void addData1(Graph graph) {
        graph.addEdge(0, 1, 1);
        graph.addEdge(0, 2, 1);
        graph.addEdge(1, 2, 1);
        graph.addEdge(2, 0, 1);
        graph.addEdge(2, 3, 1);
    }

    private static void test(Graph graph) {
        addData1(graph);
        log.info("Graph {}\n{}", graph.getClass(), graph);
        boolean[][] reachability = Graphs.transitiveClosure(graph);
        StringBuilder sb = new StringBuilder();
        for (boolean[] reachable : reachability) {
            for (boolean dest : reachable) {
                sb.append(dest ? "1 " : "0 ");
            }
            sb.append("\n");
        }
        log.info("Reachability\n{}", sb);
    }

    public static void main(String[] args) {
        test(Graphs.adjacencyMatrixGraph());
        test(Graphs.adjacencyListGraph());
        test(Graphs.adjacencyHashGraph());
    }
}
