package com.ps.boj.p1987;

import java.io.*;
import java.util.*;
import com.ps.util.InputManager;

public class Main {
    static int R;
    static int C;
    static int answer = 0;
    static String[][] board;
    static boolean[][] visited;
    static Set<String> used = new HashSet<String>();
    static int[][] delta = { {0, 1}, {1, 0}, {0, -1}, {-1, 0} };

    public static void main(String[] args) throws IOException {
        InputManager.setInput("p1987");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new String[R][];
        visited = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            board[i] = br.readLine().split("");
        }
        dfs(0, 0, 1);
        System.out.println(answer);
    }

    static void dfs(int r, int c, int cnt) {
        visited[r][c] = true;
        used.add(board[r][c]);
        boolean isBlocked = true;
        for (int[] d: delta) {
            int nr = r + d[0];
            int nc = c + d[1];
            if (0 <= nr && nr < R && 0 <= nc && nc < C && !visited[nr][nc] && !used.contains(board[nr][nc])) {
                isBlocked = false;
                dfs(nr, nc, cnt + 1);
            }
        }
        if (isBlocked && answer < cnt) {
            answer = cnt;
        }
        visited[r][c] = false;
        used.remove(board[r][c]);
    }
}
