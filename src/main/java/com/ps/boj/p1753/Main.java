package com.ps.boj.p1753;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int V;
    static int E;
    static int K;
    static ArrayList<Node>[] edges;
    static int[] dijkstraTable;

    static class Node {
        int n;
        int w;

        Node(int n, int w) {
            this.n = n;
            this.w = w;
        }
    }

    public static void main(String[] args) throws IOException {
//        String path = System.getProperty("user.dir") + "/src/main/java/com/ps/boj/p1753/input.txt";
//        System.setIn(new FileInputStream(path));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());

        edges = new ArrayList[V + 1];
        for (int i = 1; i < V + 1; i++) edges[i] = new ArrayList<>();
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edges[u].add(new Node(v, w));
        }

        dijkstra();

        for (int i = 1; i < V + 1; i++) {
            if (i == K) {
                System.out.println(0);
            } else if (dijkstraTable[i] == Integer.MAX_VALUE) {
                System.out.println("INF");
            } else {
                System.out.println(dijkstraTable[i]);
            }
        }
    }

    private static void dijkstra() {
        dijkstraTable = new int[V + 1];
        Arrays.fill(dijkstraTable, Integer.MAX_VALUE);
        dijkstraTable[K] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(node -> node.w));
        pq.add(new Node(K, 0));

        while (!pq.isEmpty()) {
            Node currNode = pq.poll();
            for (Node nextNode : edges[currNode.n]) {
                int originW = dijkstraTable[nextNode.n];
                int newW = currNode.w + nextNode.w;
                if (originW > newW) {
                    dijkstraTable[nextNode.n] = newW;
                    pq.add(new Node(nextNode.n, newW));
                }
            }
        }
    }
}
