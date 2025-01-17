package com.ps.boj.p14938;

import java.io.*;
import java.nio.file.*;
import java.util.*;

class Node {
    int idx, cost;

    Node(int idx, int cost) {
        this.idx = idx;
        this.cost = cost;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        String path = System.getProperty("user.dir") + "/src/main/java/com/ps/boj/p14938/input.txt";
        System.setIn(Files.newInputStream(Paths.get(path)));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int[] items = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < n + 1; i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }
        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) graph.add(new ArrayList<>());
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Node(b, l));
            graph.get(b).add(new Node(a, l));
        }
        
        int answer = 0;
        for (int i = 1; i < n + 1; i++) {
            int result = dijkstra(i, n, graph, m, items);
            if (answer < result) {
                answer = result;
            }
        }
        System.out.println(answer);
    }

    public static int dijkstra(int start, int n, List<List<Node>> graph, int m, int[] items) {
        PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> Integer.compare(n1.cost, n2.cost));
        pq.offer(new Node(start, 0));
        int[] dist = new int[n + 1];
        int INF = 1501;
        Arrays.fill(dist, INF);
        dist[start] = 0;
        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            for (Node next : graph.get(curr.idx)) {
                if (dist[next.idx] > dist[curr.idx] + next.cost) {
                    dist[next.idx] = dist[curr.idx] + next.cost;
                    pq.offer(new Node(next.idx, dist[next.idx]));
                }
            }
        }

        int answer = 0;
        for (int i = 1; i < n + 1; i++) {
            if (dist[i] <= m) {
                answer += items[i];
            }
        }
        return answer;
    }
}
