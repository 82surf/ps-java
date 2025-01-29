package com.ps.boj.p10830;

import java.io.*;
import java.util.*;
import com.ps.util.InputManager;

public class Main {
    static int N;

    public static void main(String[] args) throws IOException {
        InputManager.setInput("p10830");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        long[][] matrix = new long[N][];
        for (int i = 0; i < N; i++) {
            matrix[i] = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        }
        long mod = 1_000;
        long[][] result = processPow(B, matrix);
        for (int i = 0; i < N; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < N; j++) {
                sb.append(result[i][j] % mod);
                sb.append(" ");
            }
            System.out.println(sb);
        }
    }

    public static long[][] processPow(long n, long[][] matrix) {
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
        long mod = 1_000;
        long[][] result = new long[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                long n = 0;
                for (int k = 0; k < N; k++) {
                    n += (matrix1[i][k] * matrix2[k][j]) % mod;
                }
                result[i][j] = n % mod;
            }
        }
        return result;
    }
}
