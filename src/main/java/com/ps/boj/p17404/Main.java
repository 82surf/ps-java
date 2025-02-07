package com.ps.boj.p17404;

import java.io.*;
import java.util.*;
import com.ps.util.InputManager;

public class Main {

    static int N;
    static int INF = Integer.MAX_VALUE;
    static int[][] costMatrix;
    
    public static void main(String[] args) throws IOException {
        InputManager.setInput("p17404");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        costMatrix = new int[N][];
        for (int i = 0; i < N; i++) {
            costMatrix[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        int answer = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i != j) {
                    int result = makeDP(i, j);
                    if (answer > result) {
                        answer = result;
                    }
                }
            }
        }
        System.out.println(answer);
    }

    static int makeDP(int chosenFirstHouse, int chosenLastHouse) {
        // init dp table
        int[][] dp = new int[N][];
        for (int i = 0; i < N; i++) {
            dp[i] = Arrays.copyOf(costMatrix[i], 3);
        }

        // run dp
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 3; j++) {
                List<Integer> target = new ArrayList<>();
                if (i == 1 && j != chosenFirstHouse) {
                    target.add(dp[0][chosenFirstHouse]);
                } else {
                    for (int k = 0; k < 3; k++) {
                        if (j != k) {
                            target.add(dp[i - 1][k]);
                        }
                    }
                }
                if (i == 1 && j == chosenFirstHouse) {
                    dp[i][j] = INF;
                } else {
                    dp[i][j] += target.stream().mapToInt(Integer::intValue).min().getAsInt();
                    if (dp[i][j] < 0) {
                        dp[i][j] = INF;
                    }
                }
            }
        }

        return dp[N - 1][chosenLastHouse];
    }
}
