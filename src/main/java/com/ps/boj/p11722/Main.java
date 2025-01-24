package com.ps.boj.p11722;

import java.io.*;
import java.util.*;
import com.ps.util.InputManager;

public class Main {
    public static void main(String[] args) throws IOException {
        InputManager.setInput("p11722");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] dp = new int[N];

        for (int i = 0; i < N; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] > nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }

        int answer = Arrays.stream(dp).max().getAsInt();
        System.out.println(answer);
    }
}
