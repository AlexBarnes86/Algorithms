package com.toastedbits.algs.gfg.graphs.common.algorithms;

import com.toastedbits.algs.gfg.graphs.common.DWEdge;
import com.toastedbits.algs.gfg.graphs.common.Graph;
import com.toastedbits.algs.gfg.graphs.common.Graphs;
import com.toastedbits.algs.gfg.graphs.common.SDWEdge;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Slf4j
public class GraphFordFulkersonMaxFlow {
    @Value
    @Builder(builderClassName="Builder")
    public static class Result {
        int maxFlow;
        Graph residuals;
    }

    @Value
    @Builder(builderClassName="Builder")
    private static class AugmentPath {
        int criticalValue;
        List<Integer> path;
    }

    public static Result maxFlow(@NonNull final Graph graph, int start, int end) {
        Graph residuals = Graphs.adjacencyHashGraph();
        residuals.addGraph(graph);
        for(SDWEdge edge : residuals.getAllEdges()) {
            //Add initial opposing direction residuals
            if(!residuals.containsEdge(edge.getDest(), edge.getSrc())) {
                residuals.setEdge(edge.getDest(), edge.getSrc(), 0);
            }
        }

        int maxFlow = 0;
        AugmentPath augmentPath;
        while((augmentPath = findAugmentedPath(residuals, start, end)) != null) {
            List<Integer> path = augmentPath.getPath();
            int criticalValue = augmentPath.getCriticalValue();
            log.debug("Augment Path, critical value: {}, path: {}", criticalValue, path.stream().map(Object::toString).collect(Collectors.joining(" -> ")));

            for(int i = 0; i < path.size()-1; ++i) {
                residuals.addWeight(path.get(i), path.get(i+1), -criticalValue);
                residuals.addWeight(path.get(i+1), path.get(i), criticalValue);
            }
            maxFlow += criticalValue;
        }

        return Result.builder()
            .maxFlow(maxFlow)
            .residuals(residuals)
            .build();
    }

    private static AugmentPath findAugmentedPath(@NonNull final Graph residuals, int start, int end) {
        Queue<Integer> fringe = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();
        Map<Integer, Integer> parentMap = new HashMap<>();

        fringe.add(start);
        while(!fringe.isEmpty()) {
            int visit = fringe.remove();
            visited.add(visit);
            for(DWEdge edge : residuals.getAdjacentEdges(visit)) {
                if(edge.getWeight() != 0 && !visited.contains(edge.getDest())) {
                    parentMap.put(edge.getDest(), visit);
                    if(edge.getDest() == end) {
                        return buildAugmentedPath(residuals, parentMap, end);
                    }

                    visited.add(edge.getDest());
                    fringe.add(edge.getDest());
                }
            }
        }

        return null;
    }

    private static AugmentPath buildAugmentedPath(Graph graph, Map<Integer, Integer> parentMap, int end) {
        List<Integer> path = new ArrayList<>();
        Integer cur = end;
        int min = Integer.MAX_VALUE;
        while(true) {
            path.add(cur);
            Integer parent = parentMap.get(cur);
            if(parent == null) {
                break;
            }

            int weight = graph.getWeight(parent, cur).orElse(Integer.MAX_VALUE);
            if(min > weight) {
                min = weight;
            }
            cur = parent;
        }
        Collections.reverse(path);
        return AugmentPath.builder()
            .criticalValue(min)
            .path(path)
            .build();
    }
}
