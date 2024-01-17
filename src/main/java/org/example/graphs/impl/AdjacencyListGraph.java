package org.example.graphs.impl;

public class AdjacencyListGraph implements  Graph{

    private class Edge {
        int vertex, weight;
        Edge prev, next;

        Edge(int v, int w, Edge p, Edge n) {
            vertex = v;
            weight = w;
            prev = p;
            next = n;
        }
    }

    private Edge[] nodeArray;
    private Object[] nodeValues;
    private int numEdge;

    @Override
    public void init(int n) {
        try {
            if (n < 0) {
                throw new ArrayIndexOutOfBoundsException("n should more or equal to 0");
            }

            nodeArray = new Edge[n];
            for (int i = 0; i < n; i++) {
                nodeArray[i] = new Edge(-1, -1, null, null);
            }

            nodeValues = new Object[n];
            numEdge = 0;
        }catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public int nodeCount() {
        return nodeArray.length;
    }

    @Override
    public int edgeCount() {
        return numEdge;
    }

    @Override
    public Object getValue(int v) {
        if (v < 0 || v > nodeCount()) {
            throw new ArrayIndexOutOfBoundsException("v must be more or equal to 0 and not to be more than size of nodeArray");
        }
        return nodeValues[v];
    }

    @Override
    public void setValue(int v, Object val) {
        if (v < 0 || v > nodeCount()) {
            throw new ArrayIndexOutOfBoundsException("v must be more or equal to 0 and not to be more than size of nodeArray");
        }

        nodeValues[v] = val;
    }

    @Override
    public void addEdge(int v, int w, int wgt) {
        if (wgt == 0) {return;}
        Edge curr = find(v, w);
        if ((curr.next != null) && (curr.next.vertex == w)) {
            curr.next.weight = wgt;
        }else {
            curr.next = new Edge(w, wgt, curr, curr.next);
            numEdge++;
            if (curr.next.next != null) {
                curr.next.prev.prev = curr.next;
            }
        }
    }

    @Override
    public int weight(int v, int w) {
        Edge curr = find(v, w);
        if ((curr.next == null) || (curr.next.vertex != w)) {
            return 0;
        }else {
            return curr.next.weight;
        }
    }

    @Override
    public void removeEdge(int v, int w) {
        Edge curr = find(v, w);
        if ((curr.next == null) || (curr.next.vertex != w)) {
            return;
        }else {
            curr.next = curr.next.next;
            if (curr.next != null) {
                curr.next.prev = curr;
            }
        }
        numEdge--;
    }

    @Override
    public boolean hasEdge(int v, int w) {
        return weight(v, w) != 0;
    }

    @Override
    public int[] adj(int v) {

        int cnt = 0;
        Edge curr;

        for (curr = nodeArray[v].next; curr != null; curr = curr.next) {
            cnt++;
        }

        int[] temp = new int[cnt];
        cnt = 0;

        for (curr = nodeArray[v].next; curr != null; curr = curr.next ) {
            temp[cnt++] = curr.vertex;
        }

        return temp;
    }

    private Edge find(int v, int w) {
        if (v < 0 || v > nodeCount()) {
            throw new ArrayIndexOutOfBoundsException("v must be more or equal to 0 and not to be more than size of nodeArray");
        }

        Edge curr = nodeArray[v];
        while ((curr.next != null) && (curr.next.vertex < w)) {
            curr = curr.next;
        }

        return curr;
    }
}
