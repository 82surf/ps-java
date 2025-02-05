package com.ps.boj.p12015;

import java.io.*;
import java.util.*;
import com.ps.util.InputManager;

public class Main {

    static int N;
    static int[] arr;
    
    public static void main(String[] args) throws IOException {
        InputManager.setInput("p12015");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int answer = findLIS();
        System.out.println(answer);
    }

    static int findLIS() {
        int[] memo = new int[N];
        memo[0] = arr[0];

        int len = 1;
        for (int i = 1; i < N; i++) {
            int target = arr[i];
            int idx = binarySearch(target, len, memo);
            memo[idx] = target;
            if (len < idx + 1) {
                len = idx + 1;
            }
        }
        return len;
    }

    static int binarySearch(int target, int len, int[] memo) {
        int left = -1;
        int right = len;
        while (left + 1 < right) {
            int mid = (left + right) / 2;
            if (memo[mid] < target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        if (left == -1) {
            return 0;
        } else if (memo[left] == target) {
            return left;
        } else {
            return right;
        }
    }
}
