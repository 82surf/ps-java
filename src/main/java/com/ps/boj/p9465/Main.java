package com.ps.boj.p9465;

import java.io.*;
import java.nio.file.*;

public class Main {
    public static void main(String[] args) throws IOException {
        String path = System.getProperty("user.dir") + "/src/main/java/com/ps/boj/p9465/input.txt";
        System.setIn(Files.newInputStream(Paths.get(path)));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine());
            int[][] dp = new int[2][n + 1];
            int[][] stickers = new int[2][n + 1];
            String[] row1 = br.readLine().split(" ");
            String[] row2 = br.readLine().split(" ");
            for (int i = 0; i < n; i++) {
                stickers[0][i + 1] = Integer.parseInt(row1[i]);
                stickers[1][i + 1] = Integer.parseInt(row2[i]);
            }
            for (int i = 1; i < n + 1; i++) {
                if (i == 1) {
                    dp[0][i] = stickers[0][i];
                    dp[1][i] = stickers[1][i];
                } else {
                    dp[0][i] = Math.max(dp[1][i - 1] + stickers[0][i], Math.max(dp[0][i - 2] + stickers[0][i], dp[1][i - 2] + stickers[0][i]));
                    dp[1][i] = Math.max(dp[0][i - 1] + stickers[1][i], Math.max(dp[0][i - 2] + stickers[1][i], dp[1][i - 2] + stickers[1][i]));
                }
            }
            System.out.println(Math.max(dp[0][n], dp[1][n]));
        }
    }
}
