package org.example.graphs.impl;

public interface Graph {

    void init(int n);

    int nodeCount();

    int edgeCount();

    Object getValue(int v);

    void setValue(int v, Object val);

    void addEdge(int v, int w, int wgt);

    int weight(int v, int w);

    void removeEdge(int v, int w);

    boolean hasEdge(int v, int w);

    int[] adj(int v); // vertices adjacent to v
}
