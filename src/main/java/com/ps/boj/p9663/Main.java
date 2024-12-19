package com.ps.boj.p9663;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Main {
    public static int N;
    public static boolean[][] board;
    public static boolean[] usedCol;
    public static int answer = 0;
    public static int[][] delta = { {1, 1}, {1, -1}, {-1, 1}, {-1, -1} };

    public static void main(String[] args) throws IOException {
        String path = System.getProperty("user.dir") + "/src/main/java/com/ps/boj/p9663/input.txt";
        System.setIn(Files.newInputStream(Paths.get(path)));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new boolean[N][N];
        usedCol = new boolean[N];

        nQueen(0);
        System.out.println(answer);
    }

    public static void nQueen(int currRow) {
        if (currRow == N) {
            answer++;
            return;
        }
        
        for (int c = 0; c < N; c++) {
            if (isAvailable(currRow, c)) {
                board[currRow][c] = true;
                usedCol[c] = true;
                nQueen(currRow + 1);
                board[currRow][c] = false;
                usedCol[c] = false;
            }
        }
    }

    public static boolean isAvailable(int r, int c) {
        if (usedCol[c]) {
            return false;
        }
        for (int[] d : delta) {
            if (!checkDiagonal(r, c, d)) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkDiagonal(int r, int c, int[] d) {
        while (0 <= r && r < N && 0 <= c && c < N) {
            r = r + d[0];
            c = c + d[1];
            if (0 <= r && r < N && 0 <= c && c < N && board[r][c]) {
                return false;
            }
        }
        return true;
    }
}
