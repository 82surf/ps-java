package com.ps.boj.p11054;

import java.io.*;
import java.util.*;
import com.ps.util.InputManager;

public class Main {
    public static void main(String[] args) throws IOException {
        InputManager.setInput("p11054");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] LIS = new int[N];
        int[] LDS = new int[N];

        for (int i = 0; i < N; i++) {
            LIS[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    LIS[i] = Math.max(LIS[j] + 1, LIS[i]);
                }
            }
        }

        for (int i = N - 1; i >= 0; i--) {
            LDS[i] = 1;
            for (int j = N - 1; j >= 0; j--) {
                if (nums[i] > nums[j]) {
                    LDS[i] = Math.max(LDS[i], LDS[j] + 1);
                }
            }
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            int a = LIS[i];
            for (int j = i; j < N; j++) {
                int b = LDS[j];
                if (nums[i] == nums[j] && answer < a + b - 1) {
                    answer = a + b - 1;
                } else if (nums[i] > nums[j] && answer < a + b) {
                    answer = a + b;
                }
            }
        }
        // System.out.println(Arrays.toString(LIS));
        // System.out.println(Arrays.toString(LDS));
        System.out.println(answer);
    }
}
