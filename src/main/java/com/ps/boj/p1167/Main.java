package com.ps.boj.p1167;

import java.io.*;
import java.nio.file.*;
import java.util.*;

class Node {
    int index;
    int cost;

    Node(int index, int cost) {
        this.index = index;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "index = " + this.index + " cost = " + this.cost;
    }
}

class SItem {
    int currIdx;
    int totalCost;
    
    SItem(int currIdx, int totalCost) {
        this.currIdx = currIdx;
        this.totalCost = totalCost;
    }
}

public class Main {
    private static int V;

    public static void main(String[] args) throws IOException {
        String path = System.getProperty("user.dir") + "/src/main/java/com/ps/boj/p1167/input.txt";
        System.setIn(Files.newInputStream(Paths.get(path)));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        V = Integer.parseInt(br.readLine());
        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < V; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            while (true) {
                int index = Integer.parseInt(st.nextToken());
                if (index == -1) {
                    break;
                }
                int cost = Integer.parseInt(st.nextToken());
                graph.get(n).add(new Node(index, cost));
            }
        }

        SItem sItem = dfs(1, graph);
        SItem answer = dfs(sItem.currIdx, graph);
        System.out.println(answer.totalCost);
    }

    public static SItem dfs(int start, List<List<Node>> graph) {
        boolean[] visited = new boolean[V + 1];
        visited[start] = true;
        ArrayDeque<SItem> stack = new ArrayDeque<>();
        stack.addLast(new SItem(start, 0));
        int maxDist = 0;
        SItem mostFar = null;
        while (!stack.isEmpty()) {
            SItem sItem = stack.removeLast();
            if (maxDist < sItem.totalCost) {
                maxDist = sItem.totalCost;
                mostFar = sItem;
            }
            for (Node next : graph.get(sItem.currIdx)) {
                if (!visited[next.index]) {
                    stack.addLast(new SItem(next.index, sItem.totalCost + next.cost));
                    visited[next.index] = true;
                }
            }
        }
        return mostFar;
    }
}
