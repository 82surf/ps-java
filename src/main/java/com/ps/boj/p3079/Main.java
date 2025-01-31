package com.ps.boj.p3079;

import java.io.*;
import java.util.*;
import com.ps.util.InputManager;

public class Main {
    
    public static void main(String[] args) throws IOException {
        InputManager.setInput("p3079");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] times = new int[N];
        for (int i = 0; i < N; i++) {
            times[i] = Integer.parseInt(br.readLine());
        }
        System.out.println(binarySearch(N, M, times));
    }

    public static long binarySearch(int N, int M, int[] times) {
        long left = 0;
        long right = 1_000_000_000_000_000_001L;

        while (left < right) {
            long mid = (left + right) / 2;
            long total = 0;
            for (int t : times) {
                total += mid / t;
                if (total > M) break;
            }

            if (total >= M) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}
