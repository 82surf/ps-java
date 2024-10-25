package com.ps.programmers.algokit.dp.p2;

import java.util.*;

class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int[][] matrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = -1;
            }
        }
        for (int[] puddle : puddles) {
            matrix[puddle[1] - 1][puddle[0] - 1] = 0;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 0) {
                    continue;
                } else if (i == 0 && j == 0) {
                    matrix[i][j] = 1;
                } else if (i == 0) {
                    matrix[i][j] = matrix[i][j - 1];
                } else if (j == 0) {
                    matrix[i][j] = matrix[i - 1][j];
                } else {
                    matrix[i][j] = (matrix[i - 1][j] + matrix[i][j - 1]) % 1000000007;
                }
            }
        }
        return matrix[n - 1][m - 1];
    }
}