package com.ps.boj.p13549;

import java.io.*;
import java.nio.file.*;
import java.util.*;

class Node {
    int n;
    int cost;

    Node(int n, int cost) {
        this.n = n;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "n = " + this.n + " cost = " + this.cost;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        String path = System.getProperty("user.dir") + "/src/main/java/com/ps/boj/p13549/input.txt";
        System.setIn(Files.newInputStream(Paths.get(path)));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        System.out.println(bfs(N, K));
    }
    
    public static int bfs(int N, int K) {
        if (N == K) {
            return 0;
        } else if (N > K) {
            return N - K;
        }
        
        int[] minCost = new int[100002];
        Arrays.fill(minCost, 100001);
        minCost[N] = 0;
        ArrayDeque<Node> q = new ArrayDeque<>();
        q.addLast(new Node(N, 0));
        while (!q.isEmpty()) {
            Node curr = q.removeFirst();
            if (minCost[K] < curr.cost) {
                continue;
            }
            if (curr.n == K && minCost[K] > curr.cost) {
                minCost[K] = curr.cost;
                continue;
            }
            int[] nextArr = new int[]{curr.n * 2, curr.n - 1, curr.n + 1};
            for (int i = 0; i < 3; i++) {
                int next = nextArr[i];
                if (next > K) {
                    if (minCost[K] > curr.cost + next - K) {
                        minCost[K] = curr.cost + next - K;
                    }
                } else if (0 <= next && next <= 100001) {
                    if (i == 0 && minCost[next] > curr.cost) {
                        minCost[next] = curr.cost;
                        q.addLast(new Node(next, curr.cost));
                    } else if (minCost[next] > curr.cost + 1) {
                        minCost[next] = curr.cost + 1;
                        q.addLast(new Node(next, curr.cost + 1));
                    }
                }
            }
        }
        return minCost[K];
    }
}
