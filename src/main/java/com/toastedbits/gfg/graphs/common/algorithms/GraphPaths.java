package com.toastedbits.gfg.graphs.common.algorithms;

import com.toastedbits.gfg.graphs.common.DWEdge;
import com.toastedbits.gfg.graphs.common.Graph;
import lombok.NonNull;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class GraphPaths {
    public static List<List<Integer>> findAllPaths(@NonNull final Graph graph, final int src, final int dest) {
        final List<List<Integer>> solutions = new ArrayList<>();
        final Queue<Path> search = new ArrayDeque<>();
        search.add(new Path(src));

        while(!search.isEmpty()) {
            final Path path = search.remove();
            if(path.chain.idx == dest) {
                solutions.add(path.toList());
            }
            else {
                for (final DWEdge edge : graph.getAdjacentEdges(path.chain.idx)) {
                    if (!path.has(edge.getDest())) {
                        search.add(path.add(edge.getDest()));
                    }
                }
            }
        }

        return solutions;
    }

    private static class Node {
        Node parent;
        int idx;

        public Node(Node parent, int cur) {
            this.parent = parent;
            this.idx = cur;
        }
    }

    private static class Path {
        Set<Integer> visited;
        Node chain;
        public Path(int idx) {
            chain = new Node(null, idx);
            visited = new HashSet<>();
        }
        public Path add(int idx) {
            Path p = new Path(idx);
            p.chain = new Node(chain, idx);
            p.visited.add(idx);
            return p;
        }
        public boolean has(int dest) {
            return visited.contains(dest);
        }
        public List<Integer> toList() {
            Node current = chain;
            List<Integer> list = new ArrayList<>();
            while(current != null) {
                list.add(current.idx);
                current = current.parent;
            }
            Collections.reverse(list);
            return list;
        }
    }
}
