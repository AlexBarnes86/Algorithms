package com.toastedbits.gfg.graphs.common;

import java.util.Objects;

public class SDWEdge extends DWEdge {
    private final Integer src;

    SDWEdge(final Integer src, final Integer dest, final Integer weight) {
        super(dest, weight);
        this.src = src;
    }

    public int getSrc() {
        return this.src;
    }

    @Override
    public String toString() {
        return "SDWEdge(src=" + src + ", dest=" + getDest() + ", weight=" + getWeight() + ")";
    }

    @Override
    public int hashCode() {
        return Objects.hash(src, getDest(), getWeight());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SDWEdge sdwEdge = (SDWEdge) o;
        return Objects.equals(getSrc(), sdwEdge.getSrc());
    }
}