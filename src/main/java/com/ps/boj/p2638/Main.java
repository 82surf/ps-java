package com.ps.boj.p2638;

import java.io.*;
import java.util.*;
import com.ps.util.InputManager;

public class Main {
    static int R;
    static int C;
    static int[][] board;
    static int[][] cntBoard;

    public static void main(String[] args) throws IOException {
        InputManager.setInput("p2638");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new int[R][];
        for (int i = 0; i < R; i++) {
            int[] row = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            board[i] = row;
        }

        int answer = 0;
        while (true) {
            checkCheese();
            boolean isAllRemoved = removeCheese();
            if (isAllRemoved) {
                break;
            }
            answer++;
        }
        System.out.println(answer);
    }

    static boolean removeCheese() {
        boolean isAllRemoved = true;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (cntBoard[r][c] >= 2) {
                    board[r][c] = 0;
                    isAllRemoved = false;
                }
            }
        }
        return isAllRemoved;
    }

    static void checkCheese() {
        int[][] delta = { {0, 1}, {1, 0}, {0, -1}, {-1, 0} };
        cntBoard = new int[R][C];
        boolean[][] visited = new boolean[R][C];
        visited[0][0] = true;
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.addLast(new int[]{0, 0});
        while (!q.isEmpty()) {
            int[] curr = q.removeFirst();
            int r = curr[0];
            int c = curr[1];
            for (int[] d : delta) {
                int nr = r + d[0];
                int nc = c + d[1];
                if (0 <= nr && nr < R && 0 <= nc && nc < C && !visited[nr][nc]) {
                    if (board[nr][nc] == 1) {
                        cntBoard[nr][nc]++;
                    } else {
                        q.addLast(new int[]{nr, nc});
                        visited[nr][nc] = true;
                    }
                }
            }
        }
    }
}
