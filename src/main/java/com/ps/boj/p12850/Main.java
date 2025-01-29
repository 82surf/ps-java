package com.ps.boj.p12850;

import java.io.*;
import java.util.*;
import com.ps.util.InputManager;

public class Main {
    public static void main(String[] args) throws IOException {
        // 1 - 0
        // | \ |
        // 3 - 2
        // | \ |
        // 5 - 4
        // |   |
        // 7 - 6
        long[][] matrix = {
//            0  1  2  3  4  5  6  7
            { 0, 1, 1, 0, 0, 0, 0, 0 }, // 0
            { 1, 0, 1, 1, 0, 0, 0, 0 }, // 1
            { 1, 1, 0, 1, 1, 0, 0, 0 }, // 2
            { 0, 1, 1, 0, 1, 1, 0, 0 }, // 3
            { 0, 0, 1, 1, 0, 1, 1, 0 }, // 4
            { 0, 0, 0, 1, 1, 0, 0, 1 }, // 5
            { 0, 0, 0, 0, 1, 0, 0, 1 }, // 6
            { 0, 0, 0, 0, 0, 1, 1, 0 }  // 7
        };

        InputManager.setInput("p12850");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[][] result = processPow(N, matrix);
        System.out.println(result[0][0]);
    }

    public static long[][] processPow(int n, long[][] matrix) {
        if (n == 1) {
            return matrix;
        } else if (n == 2) {
            return multiply(matrix, matrix);
        } else if (n % 2 == 0) {
            long[][] tmp = processPow(n / 2, matrix);
            return multiply(tmp, tmp);
        } else {
            long[][] tmp = processPow((n - 1) / 2, matrix);
            return multiply(multiply(tmp, tmp), matrix);
        }
    }

    public static long[][] multiply(long[][] matrix1, long[][] matrix2) {
        long mod = 1_000_000_007;
        long[][] result = new long[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                long n = 0;
                for (int k = 0; k < 8; k++) {
                    n += (matrix1[i][k] * matrix2[k][j]) % mod;
                }
                result[i][j] = n % mod;
            }
        }
        return result;
    }
}
