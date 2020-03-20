package com.toastedbits.algs.gfg.graphs;

import com.toastedbits.algs.gfg.graphs.common.Edges;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class EdgeTests {
    @Test
    public void test() {
        log.debug("SDWEdge {}", Edges.weighted(0, 0, 1));
    }
}
