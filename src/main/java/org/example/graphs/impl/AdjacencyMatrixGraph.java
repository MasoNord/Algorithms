package org.example.graphs.impl;

public class AdjacencyMatrixGraph implements Graph{
    private int[][] matrix;
    private Object[] nodeValues;
    private int numEdge;


    @Override
    public void init(int n) {
        matrix = new int[n][n];
        numEdge = 0;
    }

    @Override
    public int nodeCount() {
        return nodeValues.length;
    }

    @Override
    public int edgeCount() {
        return numEdge;
    }

    @Override
    public Object getValue(int v) {
        return nodeValues[v];
    }

    @Override
    public void setValue(int v, Object val) {
        nodeValues[v] = val;
    }

    @Override
    public void addEdge(int v, int w, int wgt) {
        try {
            if (wgt == 0)
                throw new ArithmeticException("can't store weight of 0");

            if (v < 0 || w < 0 || v >= matrix.length || w >= matrix.length)
                throw new ArrayIndexOutOfBoundsException();

            if (matrix[v][w] == 0) {
                numEdge++;
            }
            matrix[v][w] = wgt;
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public int weight(int v, int w) {
        if (v > 0 && w > 0 && v < matrix.length && w < matrix.length) {
            return matrix[v][w];
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public void removeEdge(int v, int w) {
        if (v > 0 && w > 0 && v < matrix.length && w < matrix.length) {
            if (matrix[v][w] != 0) {
                matrix[v][w] = 0;
                numEdge--;
            }
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    @Override
    public boolean hasEdge(int v, int w) {
        if (v > 0 && w > 0 && v < matrix.length && w < matrix.length) {
            return weight(v, w) != 0;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public int[] adj(int v) {
        if (v > 0 && v < matrix.length) {
            int count = 0;
            int [] temp;

            for (int i = 0; i < nodeValues.length; i++) {
                if (matrix[v][i] != 0) count++;
            }

            temp = new int[count];
            count = 0;

            for (int i = 0; i < nodeValues.length; i++) {
                if (matrix[v][i] != 0) temp[count++] = i;
            }
        }
        throw new ArrayIndexOutOfBoundsException();
    }
}
