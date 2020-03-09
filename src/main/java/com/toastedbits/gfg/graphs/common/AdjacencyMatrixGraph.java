package com.toastedbits.gfg.graphs.common;

import com.google.common.collect.ImmutableSet;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.Optional;

@Slf4j
public class AdjacencyMatrixGraph implements Graph {
    private int size;
    private int maxObserved;
    private int[][] matrix;

    AdjacencyMatrixGraph() {
        maxObserved = 0;
        size = 10;
        matrix = new int[size][size];
    }

    private void resize(final int size) {
        int copySize = this.size;
        while(this.size <= size) {
            this.size *= 2;
        }

        int[][] next = new int[this.size][this.size];
        for(int i = 0; i < copySize; ++i) {
            for(int j = 0; j < copySize; ++j) {
                next[i][j] = matrix[i][j];
            }
        }

        matrix = next;
    }

    @Override
    public void addEdge(final int src, final int dest, final int value) {
        int fringe = Math.max(src, dest);
        maxObserved = Math.max(maxObserved, fringe);
        if(fringe >= this.size) {
            resize(fringe);
        }

        matrix[src][dest] = value;
    }

    @Override
    public void addUndirectedEdge(final int a, final int b, final int value) {
        addEdge(a, b, value);
        addEdge(b, a, value);
    }

    @Override
    public Optional<Integer> getWeight(final int src, final int dest) {
        if(src >= size || dest >= size) {
            return Optional.empty();
        }

        if(matrix[src][dest] != 0) {
            return Optional.of(matrix[src][dest]);
        }

        return Optional.empty();
    }

    @Override
    public boolean contains(final int src, final int dest) {
        return getWeight(src, dest).isPresent();
    }

    @Override
    public Collection<Edge> adjacent(final int vertex) {
        final ImmutableSet.Builder<Edge> builder = ImmutableSet.builder();

        if(vertex >= this.size) {
            return builder.build();
        }

        for(int i = 0; i < this.size; ++i) {
            if(matrix[vertex][i] != 0) {
                //log.debug("visit edge {} -> {}", vertex, i);
                builder.add(Edge.to(i, matrix[vertex][i]));
            }
        }

        return builder.build();
    }

    @Override
    public int getSize() {
        return maxObserved + 1;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for(int i = 0; i <= maxObserved; ++i) {
            for(int j = 0; j <= maxObserved; ++j) {
                sb.append(matrix[i][j]);
                if(j != this.size - 1) {
                    sb.append(" ");
                }
            }
            if(i != this.size - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
