package com.toastedbits.gfg.graphs.common.algorithms;

import com.toastedbits.gfg.graphs.common.Graph;
import lombok.NonNull;

public class GraphFloydWarshall {
    public static int[][] allPairsShortestPaths(@NonNull final Graph graph) {
        int[][] solution = new int[graph.getSize()][graph.getSize()];

        for(int i = 0; i < graph.getSize(); ++i) {
            for(int j = 0; j < graph.getSize(); ++j) {
                if(i == j) {
                    solution[i][j] = 0;
                }
                else {
                    solution[i][j] = graph.getWeight(i, j).orElse(Integer.MAX_VALUE);
                }
            }
        }

        for(int k = 0; k < graph.getSize(); ++k) {
            for(int i = 0; i < graph.getSize(); ++i) {
                for(int j = 0; j < graph.getSize(); ++j) {
                    if((long)solution[i][k] + (long)solution[k][j] < (long)solution[i][j]) {
                        solution[i][j] = solution[i][k] + solution[k][j];
                    }
                    if(solution[j][j] < 0) {
                        throw new IllegalArgumentException("Negative Cycle Detected!");
                    }
                }
            }
        }

        return solution;
    }
}
