package com.ps.boj.p2206;

import java.io.*;
import java.nio.file.*;
import java.util.*;

// 시간초과

// class Node {
//     int r;
//     int c; 
//     int cnt;
//     boolean crushed;

//     Node(int r, int c, int cnt, boolean crushed) {
//         this.r = r;
//         this.c = c;
//         this.cnt = cnt;
//         this.crushed = crushed;
//     }
// }

public class Try1 {
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
        // solution(N, M, opened);
    }

    // public static void solution(int N, int M, Boolean[][] opened) {
    //     int INF = Integer.MAX_VALUE;
    //     int[][] delta = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    //     int[][] minCnt = new int[N][M];
    //     for (int i = 0; i < N; i++) Arrays.fill(minCnt[i], INF);
    //     minCnt[0][0] = 1;
    //     ArrayDeque<Node> q = new ArrayDeque<>();
    //     q.addLast(new Node(0, 0, 1, false));

    //     while (!q.isEmpty()) {
    //         Node curr = q.removeFirst();
    //         if (minCnt[curr.r][curr.c] > curr.cnt) {
    //             minCnt[curr.r][curr.c] = curr.cnt;
    //         }
    //         for (int[] d : delta) {
    //             int nr = curr.r + d[0];
    //             int nc = curr.c + d[1];
    //             if (0 <= nr && nr < N && 0 <= nc && nc < M && minCnt[nr][nc] > curr.cnt + 1) {
    //                 if (opened[nr][nc]) {
    //                     q.addLast(new Node(nr, nc, curr.cnt + 1, curr.crushed));
    //                 } else if (!curr.crushed) {
    //                     q.addLast(new Node(nr, nc, curr.cnt + 1, true));
    //                 }
    //             }
    //         }
    //     }

    //     System.out.println(minCnt[N - 1][M - 1] == INF ? -1 : minCnt[N - 1][M - 1]);
    // }
}
