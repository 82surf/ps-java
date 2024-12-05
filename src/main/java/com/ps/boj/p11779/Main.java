package com.ps.boj.p11779;

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
        String path = System.getProperty("user.dir") + "/src/main/java/com/ps/boj/p11779/input.txt";
        System.setIn(Files.newInputStream(Paths.get(path)));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i < n + 1; i++) graph.add(new ArrayList<>());
        while (m-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Node(b, c));
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());

        dijkstra(n, start, target, graph);
    }

    public static void dijkstra(int n, int start, int target, List<List<Node>> graph) {
        boolean[] visited = new boolean[n + 1];
        int[] dist = new int[n + 1];
        int INF = Integer.MAX_VALUE;
        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
        pq.offer(new Node(start, 0));

        int[] beforeNodes = new int[n + 1];

        while (!pq.isEmpty()) {
            Node item = pq.poll();
            int curr = item.index;
            int currWeight = item.cost;

            if (dist[curr] < currWeight) continue;
            visited[curr] = true;

            for (Node next : graph.get(curr)) {
                if (dist[next.index] > dist[curr] + next.cost) {
                    dist[next.index] = dist[curr] + next.cost;
                    beforeNodes[next.index] = curr;
                    pq.offer(new Node(next.index, dist[next.index]));
                }
            }
        }

        System.out.println(dist[target]);
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.addLast(target);
        int cnt = 0;
        while (beforeNodes[target] != 0) {
            cnt += 1;
            stack.addLast(beforeNodes[target]);
            target = beforeNodes[target];
        }
        System.out.println(cnt + 1);
        while (!stack.isEmpty()) {
            System.out.print(stack.removeLast() + " ");
        }
        System.out.println();
    }
}
