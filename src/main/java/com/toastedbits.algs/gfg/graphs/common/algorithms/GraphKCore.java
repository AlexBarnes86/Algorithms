package com.toastedbits.algs.gfg.graphs.common.algorithms;

import com.toastedbits.algs.gfg.graphs.common.Graph;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public class GraphKCore {
    public static void kCore(@NonNull final Graph graph, final int k) {
        boolean updatesMade = true;
        Set<Integer> nodes = IntStream.range(0, graph.getSize()).boxed().collect(Collectors.toSet());
        while(updatesMade && !nodes.isEmpty()) {
            updatesMade = false;
            for(Iterator<Integer> itr = nodes.iterator(); itr.hasNext();) {
                int node = itr.next();
                if(graph.getAdjacentEdges(node).size() < k) {
                    log.debug("Deleting node: {}", node);
                    itr.remove();
                    graph.deleteVertex(node);
                    updatesMade = true;
                }
            }
        }
    }
}
