package com.toastedbits.algs.gfg.graphs.common.algorithms;

import com.toastedbits.algs.gfg.graphs.common.Graph;
import com.toastedbits.algs.gfg.graphs.common.SDWEdge;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

public class GraphBellmanFordsShortestPaths {
    public static List<Integer> pathCost(@NonNull final Graph graph, final int start) {
        checkArgument(graph.containsVertex(start), "Start vertex not in graph");

        final long[] costs = new long[graph.getSize()];
        for(int i = 0; i < graph.getSize(); ++i) {
            costs[i] = Integer.MAX_VALUE;
        }
        costs[start] = 0;

        List<SDWEdge> allEdges = graph.getAllEdges();
        for(int i = 0; i < graph.getSize(); ++i) {
            for(final SDWEdge edge : allEdges) {
                if(costs[edge.getDest()] > costs[edge.getSrc()] + edge.getWeight()) {
                    costs[edge.getDest()] = costs[edge.getSrc()] + edge.getWeight();
                }
            }
        }

        for(final SDWEdge edge : allEdges) {
            if(costs[edge.getDest()] > costs[edge.getSrc()] + edge.getWeight()) {
                throw new IllegalArgumentException("Graph contains a negative cycle on edge: " + edge);
            }
        }

        List<Integer> result = new ArrayList<>();
        for (final long cost : costs) {
            result.add((int) cost);
        }

        return result;
    }
}
