package com.toastedbits.gfg.graphs.common.algorithms;

import com.toastedbits.gfg.graphs.common.DWEdge;
import com.toastedbits.gfg.graphs.common.Edges;
import com.toastedbits.gfg.graphs.common.Graph;
import com.toastedbits.gfg.graphs.common.SDWEdge;
import lombok.NonNull;

import java.util.ArrayDeque;
import java.util.Deque;

public class GraphTransitiveClosure {
    public static boolean[][] transitiveClosure(@NonNull final Graph graph) {
        final boolean [][] reachable = new boolean[graph.getSize()][graph.getSize()];
        final Deque<SDWEdge> stack = new ArrayDeque<>();

        for(int vertex = 0; vertex < graph.getSize(); vertex++) {
            stack.push(Edges.between(vertex, vertex));
            while(!stack.isEmpty()) {
                SDWEdge sd = stack.pop();
                reachable[sd.getSrc()][sd.getDest()] = true;
                for (final DWEdge adj : graph.getAdjacentEdges(sd.getDest())) {
                    if (!reachable[sd.getSrc()][adj.getDest()]) {
                        stack.push(Edges.between(sd.getSrc(), adj.getDest()));
                    }
                }
            }
        }

        return reachable;
    }
}
