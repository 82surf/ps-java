package com.ps.boj.p2096;

import java.io.*;
import java.util.*;
import com.ps.util.InputManager;

public class Main {
    public static void main(String[] args) throws IOException {
        InputManager.setInput("p2096");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] minBoard = new int[N][3];
        int[][] maxBoard = new int[N][3];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            minBoard[i] = new int[]{a, b, c};
            maxBoard[i] = new int[]{a, b, c};
            if (i != 0) {
                int[] prevMinRow = minBoard[i - 1];
                int[] prevMaxRow = maxBoard[i - 1];

                minBoard[i][0] += Math.min(prevMinRow[0], prevMinRow[1]);
                minBoard[i][1] += Math.min(prevMinRow[2], Math.min(prevMinRow[0], prevMinRow[1]));
                minBoard[i][2] += Math.min(prevMinRow[1], prevMinRow[2]);

                maxBoard[i][0] += Math.max(prevMaxRow[0], prevMaxRow[1]);
                maxBoard[i][1] += Math.max(prevMaxRow[2], Math.max(prevMaxRow[0], prevMaxRow[1]));
                maxBoard[i][2] += Math.max(prevMaxRow[1], prevMaxRow[2]);
            }
        }

        int max = Math.max(Math.max(maxBoard[N - 1][0], maxBoard[N - 1][1]), maxBoard[N - 1][2]);
        int min = Math.min(Math.min(minBoard[N - 1][0], minBoard[N - 1][1]), minBoard[N - 1][2]);
        System.out.printf("%d %d", max, min);
    }
}
