package com.ps.boj.p1967;

import java.io.*;
import java.nio.file.*;
import java.util.*;

class Edge {
    int to;
    int weight;

    Edge(int to, int weight) {
        this.to = to;
        this.weight = weight;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        String path = System.getProperty("user.dir") + "/src/main/java/com/ps/boj/p1967/input.txt";
        System.setIn(new FileInputStream(path));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<List<Edge>> edges = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) edges.add(new ArrayList<>());

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edges.get(a).add(new Edge(b, w));
            edges.get(b).add(new Edge(a, w));
        }
        int[] result1 = dfs(1, n, edges);
        int[] result2 = dfs(result1[0], n, edges);
        System.out.println(result2[1]);
    }

    public static int[] dfs(int s, int n, List<List<Edge>> edges) {
        boolean[] visited = new boolean[n + 1];
        visited[s] = true;
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.addLast(new int[]{s, 0});
        int mostFarNode = 1;
        int mostFarDist = 0;
        while (!q.isEmpty()) {
            int[] item = q.removeLast();
            int curr = item[0];
            int cnt = item[1];

            for (Edge edge : edges.get(curr)) {
                if (!visited[edge.to]) {
                    visited[edge.to] = true;
                    int distance = cnt + edge.weight;
                    if (mostFarDist < distance) {
                        mostFarNode = edge.to;
                        mostFarDist = distance;
                    }
                    q.addLast(new int[]{edge.to, distance});
                }
            }
        }
        return new int[]{mostFarNode, mostFarDist};
    }
}
