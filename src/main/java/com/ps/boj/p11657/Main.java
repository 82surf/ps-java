package com.ps.boj.p11657;

import java.io.*;
import java.nio.file.*;
import java.util.*;

class Edge {
    int from, to, cost;

    Edge(int from, int to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        String path = System.getProperty("user.dir") + "/src/main/java/com/ps/boj/p11657/input.txt";
        System.setIn(Files.newInputStream(Paths.get(path)));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        List<Edge> graph = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            graph.add(new Edge(A, B, C));
        }
        bellmanFord(1, N, M, graph);
    }

    public static void bellmanFord(int start, int N, int M, List<Edge> graph) {
        long[] dist = new long[N + 1];
        long INF = Long.MAX_VALUE;
        Arrays.fill(dist, INF);
        dist[start] = 0;

        for (int i = 0; i < N; i++) {
            for (Edge edge : graph) {
                if (dist[edge.from] != INF && dist[edge.to] > dist[edge.from] + edge.cost) {
                    dist[edge.to] = dist[edge.from] + edge.cost;
                }
            }
        }

        for (Edge edge : graph) {
            if (dist[edge.from] != INF && dist[edge.to] > dist[edge.from] + edge.cost) {
                System.out.println(-1);
                return;
            }
        }

        for (int i = 2; i <= N; i++) {
            if (dist[i] == INF) {
                System.out.println(-1);
            } else {
                System.out.println(dist[i]);
            }
        }
    }
}
