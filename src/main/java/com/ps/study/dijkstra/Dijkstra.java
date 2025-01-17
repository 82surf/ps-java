package com.ps.study.dijkstra;

import java.io.*;
import java.nio.*;
import java.util.*;

class Node {
    int idx, cost;

    Node(int idx, int cost) {
        this.idx = idx;
        this.cost = cost;
    }
}

class PQItem {
    int idx, cost;

    PQItem(int idx, int cost) {
        this.idx = idx;
        this.cost = cost;
    }
}

public class Dijkstra {
    public static void main(String[] args) throws IOException {
        String path = System.getProperty("user.dir") + "/src/main/java/com/ps/study/dijkstra/input.txt";
        System.setIn(new FileInputStream(path));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine());
        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i < V + 1; i++) graph.add(new ArrayList<>());
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph.get(u).add(new Node(v, w));
        }
        dijkstra(K, V, graph);
    }

    public static void dijkstra(int start, int V, List<List<Node>> graph) {
        int[] dist = new int[V + 1];
        int INF = 3000001;
        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<PQItem> pq = new PriorityQueue<>(Comparator.comparingInt(i -> i.cost));
        pq.offer(new PQItem(start, 0));

        while (!pq.isEmpty()) {
            PQItem curr = pq.poll();
            if (curr.cost > dist[curr.idx]) continue;
            for (Node next : graph.get(curr.idx)) {
                int originCost = dist[next.idx];
                int newCost = curr.cost + next.cost;
                if (originCost > newCost) {
                    dist[next.idx] = newCost;
                    pq.offer(new PQItem(next.idx, newCost));
                }
            }
        }

        for (int i = 1; i < V + 1; i++) {
            System.out.println(dist[i] == INF ? "INF" : dist[i]);
        }
    }
}
