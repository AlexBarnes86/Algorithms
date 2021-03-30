package com.toastedbits.gfg.graphs.common.algorithms;

import com.toastedbits.gfg.graphs.common.Graph;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GraphLevelCount {
    @Value
    @Builder
    public static class Results {
        Map<Integer, Integer> levels;
        List<Integer> maxVerts;
        int maxLevel;
    }

    public static Results solve(@NonNull final Graph graph) {
        final Map<Integer, Integer> levels = GraphBFS.findLevels(graph);

        int maxLevel = Integer.MIN_VALUE;
        final List<Integer> maxVerts = new ArrayList<>();

        for(Map.Entry<Integer, Integer> nodeLevel : levels.entrySet()) {
            int node = nodeLevel.getKey();
            int level = nodeLevel.getValue();
            if(maxLevel < level) {
                maxVerts.clear();
                maxLevel = level;
                maxVerts.add(node);
            }
            else if(level == maxLevel) {
                maxVerts.add(node);
            }
        }

        return Results.builder()
            .levels(levels)
            .maxVerts(maxVerts)
            .maxLevel(maxLevel)
            .build();
    }
}
