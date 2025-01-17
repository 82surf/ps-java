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
        // 출발 노드에서 다른 노드까지의 최단거리를 표현하는 배열
        long[] dist = new long[N + 1];
        long INF = Long.MAX_VALUE;
        Arrays.fill(dist, INF);
        dist[start] = 0;

        // 정점의 개수 - 1번 완화 알고리즘을 수행한다.
        // 한 정점에서 다른 정점으로 이동할 때 지날 수 있는 간선의 최대 개수가 N - 1이기 때문.
        for (int i = 0; i < N - 1; i++) {
            // 완화(Relax) 알고리즘
            // 간선을 순회하며 최단거리를 갱신할 수 있다면 갱신한다.
            for (Edge edge : graph) {
                if (dist[edge.from] != INF && dist[edge.to] > dist[edge.from] + edge.cost) {
                    dist[edge.to] = dist[edge.from] + edge.cost;
                }
            }
        }

        // 위 과정을 거친 이후에도 최단거리가 감소하는 경우, 음의 사이클이 존재한다고 볼 수 있다.
        // 가능한 짧은 거리로 갱신했음에도 최단거리가 줄어든다는 것은 최단거리가 음의 무한대인 음의 사이클이 존재한다는 뜻.
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
