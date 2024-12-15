package com.ps.boj.p1504;

import java.io.*;
import java.util.*;
import java.nio.file.*;

class Node {
    int index;
    int cost;

    Node(int index, int cost) {
        this.index = index;
        this.cost = cost;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        String path = System.getProperty("user.dir") + "/src/main/java/com/ps/boj/p1504/input.txt";
        System.setIn(new FileInputStream(path));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        Map<Integer, List<Node>> graph = new HashMap<>();
        for (int i = 1; i <= N; i++) graph.put(i, new ArrayList<>());

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a, c));
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        int answer1 = getResult(N, v1, v2, graph);
        int answer2 = getResult(N, v2, v1, graph);
        if (answer1 > 0 && answer2 > 0) {
            System.out.println(Math.min(answer1, answer2));
        } else if (answer1 == -1 && answer2 == -1) {
            System.out.println(-1);
        } else {
            System.out.println(Math.max(answer1, answer2));
        }
    }

    public static int getResult(int N, int v1, int v2, Map<Integer, List<Node>> graph) {
        int result1 = dijkstra(N, 1, v1, graph);
        int result2 = dijkstra(N, v1, v2, graph);
        int result3 = dijkstra(N, v2, N, graph);
        int answer = -1;
        if (result1 != -1 && result2 != -1 && result3 != -1) {
            answer = result1 + result2 + result3;
        }
        return answer;
    }

    public static int dijkstra(int N, int start, int end, Map<Integer, List<Node>> graph) {
        int INF = Integer.MAX_VALUE;
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> Integer.compare(n1.cost, n2.cost));
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            if (curr.cost > dist[curr.index]) continue;
            for (Node next : graph.get(curr.index)) {
                if (dist[next.index] > dist[curr.index] + next.cost) {
                    dist[next.index] = dist[curr.index] + next.cost;
                    pq.offer(new Node(next.index, dist[next.index]));
                }
            }
        }

        return dist[end] == INF ? -1 : dist[end];
    }
}
