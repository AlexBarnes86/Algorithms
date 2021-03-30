package com.toastedbits.gfg.graphs.common.algorithms;

import com.toastedbits.gfg.graphs.common.Graph;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

public class GraphDijstrasShortestPaths {
    private static int minDistanceIndex(final List<Integer> dist, List<Boolean> sptSet) {
        int min = Integer.MAX_VALUE, minIdx = -1;
        for(int v = 0; v < dist.size(); ++v) {
            if(!sptSet.get(v) && dist.get(v) <= min) {
                min = dist.get(v);
                minIdx = v;
            }
        }

        return minIdx;
    }

    public static List<Integer> pathCost(@NonNull final Graph graph, final int start) {
        checkArgument(graph.containsVertex(start), "Start vertex not in graph");

        final List<Boolean> sptSet = new ArrayList<>();
        final List<Integer> dist = new ArrayList<>();

        for(int i = 0; i < graph.getSize(); ++i) {
            dist.add(Integer.MAX_VALUE);
            sptSet.add(false);
        }

        dist.set(start, 0);

        for(int i = 0; i < graph.getSize() - 1; ++i) {
            int src = minDistanceIndex(dist, sptSet);

            sptSet.set(src, true);

            for(int dest : graph.getAdjacentVertecies(src)) {
                if (!sptSet.get(dest)) {
                    int weight = graph.getWeight(src, dest).orElse(0);
                    if(weight != 0 && dist.get(src) != Integer.MAX_VALUE && dist.get(src) + weight < dist.get(dest)) {
                        dist.set(dest, dist.get(src) + weight);
                    }
                }
            }
        }

        return dist;
    }
}
