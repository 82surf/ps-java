package com.ps.boj.p1865;

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
        String path = System.getProperty("user.dir") + "/src/main/java/com/ps/boj/p1865/input.txt";
        System.setIn(Files.newInputStream(Paths.get(path)));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        while (TC-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());
            List<Edge> graph = new ArrayList<>();
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());
                graph.add(new Edge(S, E, T));
                graph.add(new Edge(E, S, T));
            }
            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());
                graph.add(new Edge(S, E, -T));
            }
            bellmanFord(N, graph);
        }
    }

    public static void bellmanFord(int N, List<Edge> graph) {
        int INF = 25000001;
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[1] = 0;
        
        boolean updated = false;
        for (int i = 0; i < N - 1; i++) {
            updated = false;
            for (Edge edge : graph) {
                if (dist[edge.to] > dist[edge.from] + edge.cost) {
                    dist[edge.to] = dist[edge.from] + edge.cost;
                    updated = true;
                }
            }

            if (!updated) {
                break;
            }
        }

        if (updated) {
            for (Edge edge : graph) {
                if (dist[edge.to] > dist[edge.from] + edge.cost) {
                    System.out.println("YES");
                    return;
                }
            }
        }
        System.out.println("NO");
        return;
    }
}
