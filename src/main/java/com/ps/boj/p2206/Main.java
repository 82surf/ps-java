package com.ps.boj.p2206;

import java.io.*;
import java.nio.file.*;
import java.util.*;

class Node {
    int r;
    int c; 
    int cnt;
    int crushed;

    Node(int r, int c, int cnt, int crushed) {
        this.r = r;
        this.c = c;
        this.cnt = cnt;
        this.crushed = crushed;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        String path = System.getProperty("user.dir") + "/src/main/java/com/ps/boj/p2206/input.txt";
        System.setIn(Files.newInputStream(Paths.get(path)));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Boolean[][] opened = new Boolean[N][];
        for (int i = 0; i < N; i++) {
            opened[i] = Arrays.stream(br.readLine().split("")).map(n -> n.equals("1") ? false : true).toArray(Boolean[]::new);
        }
        solution(N, M, opened);
    }

    public static void solution(int N, int M, Boolean[][] opened) {
        int[][] delta = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        boolean[][][] visited = new boolean[N][M][2];
        ArrayDeque<Node> q = new ArrayDeque<>();
        visited[0][0][0] = true;
        q.addLast(new Node(0, 0, 1, 0));
        while (!q.isEmpty()) {
            Node curr = q.removeFirst();
            if (curr.r == N - 1 && curr.c == M - 1) {
                System.out.println(curr.cnt);
                return;
            }
            for (int[] d : delta) {
                int nr = curr.r + d[0];
                int nc = curr.c + d[1];
                if (0 <= nr && nr < N && 0 <= nc && nc < M) {
                    if (opened[nr][nc]) {
                        if (!visited[nr][nc][curr.crushed]) {
                            visited[nr][nc][curr.crushed] = true;
                            q.addLast(new Node(nr, nc, curr.cnt + 1, curr.crushed));
                        }
                    } else if (curr.crushed == 0) {
                        if (!visited[nr][nc][1]) {
                            visited[nr][nc][1] = true;
                            q.addLast(new Node(nr, nc, curr.cnt + 1, 1));
                        }
                    }
                }
            }
        }
        System.out.println(-1);
    }
}
