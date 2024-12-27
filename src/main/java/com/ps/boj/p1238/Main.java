package com.ps.boj.p1238;

import java.io.*;
import java.util.*;
import java.nio.file.*;

class Node {
    int to;
    int cost;

    Node(int to, int cost) {
        this.to = to;
        this.cost = cost;
    }
}

class PQNode {
    int index;
    int cost;

    PQNode(int index, int cost) {
        this.index = index;
        this.cost = cost;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        String path = System.getProperty("user.dir") + "/src/main/java/com/ps/boj/p1238/input.txt";
        System.setIn(Files.newInputStream(Paths.get(path)));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.get(from).add(new Node(to, cost));
        }

        // X -> N
        int[] xToN = dijkstra(N, X, graph);

        // N -> X
        int answer = 0;
        for (int i = 1; i <= N; i++) {
            int[] nToX = dijkstra(N, i, graph);
            int result = nToX[X] + xToN[i];
            if (answer < result) {
                answer = result;
            }
        }
        System.out.println(answer);
    }

    public static int[] dijkstra(int N, int start, List<List<Node>> graph) {
        PriorityQueue<PQNode> pq = new PriorityQueue<>((n1, n2) -> Integer.compare(n1.cost, n2.cost));
        pq.offer(new PQNode(start, 0));

        int[] dist = new int[N + 1];
        int INF = 1000001;
        Arrays.fill(dist, INF);
        dist[start] = 0;

        while (!pq.isEmpty()) {
            PQNode curr = pq.poll();
            if (curr.cost > dist[curr.index]) continue;
            for (Node next : graph.get(curr.index)) {
                if (dist[next.to] > curr.cost + next.cost) {
                    dist[next.to] = curr.cost + next.cost;
                    pq.offer(new PQNode(next.to, dist[next.to]));
                }
            }
        }

        return dist;
    }
}
