package com.ps.study.unionfind;

import java.util.*;

public class UnionFind {
    static int[] parent;

    public static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) {
            parent[Math.max(x, y)] = Math.min(x, y);
        }
        System.out.println("union result: " + Arrays.toString(parent));
    }

    public static int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public static void main(String[] args) {
        int n = 5;
        parent = new int[n + 1];
        for (int i = 0; i < parent.length; i++) parent[i] = i;
        union(1, 2);
        union(2, 3);

        for (int i = 1; i <= n; i++) System.out.println(find(i));
        System.out.println(Arrays.toString(parent));
    }
}
