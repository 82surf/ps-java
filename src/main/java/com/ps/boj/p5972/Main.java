package com.ps.boj.p5972;

import java.io.*;
import java.util.*;
import com.ps.util.InputManager;

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

    public int getCost() {
        return this.cost;
    }
}

public class Main {

    static int N;
    static int M;
    static List<List<Node>> graph = new ArrayList<>();
    
    public static void main(String[] args) throws IOException {
        InputManager.setInput("p5972");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            graph.get(A).add(new Node(B, C));
            graph.get(B).add(new Node(A, C));
        }
        System.out.println(dijkstra());
    }

    public static int dijkstra() {
        int INF = Integer.MAX_VALUE;
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[1] = 0;

        PriorityQueue<PQItem> pq = new PriorityQueue<>(Comparator.comparingInt(PQItem::getCost));
        pq.offer(new PQItem(1, 0));

        while (!pq.isEmpty()) {
            PQItem curr = pq.poll();
            if (curr.cost > dist[curr.idx]) continue;
            for (Node next : graph.get(curr.idx)) {
                if (dist[next.idx] > curr.cost + next.cost) {
                    dist[next.idx] = curr.cost + next.cost;
                    pq.offer(new PQItem(next.idx, dist[next.idx]));
                }
            }
        }

        return dist[N];
    }
}
