package com.ps.boj.p3020;

import java.io.*;
import java.util.*;
import com.ps.util.InputManager;

public class Main {
    public static void main(String[] args) throws IOException {
        InputManager.setInput("p3020");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        int[] ceil = new int[H + 1];
        int[] floor = new int[H + 1];
        // O(N)
        for (int i = 0; i < N; i++) {
            int h = Integer.parseInt(br.readLine());
            if (i % 2 == 0) {
                floor[h]++;
            } else {
                ceil[H - h + 1]++;
            }
        }

        // O(H)
        for (int i = 1; i < H; i++) {
            ceil[i + 1] = ceil[i] + ceil[i + 1];
            floor[H - i] = floor[H - i] + floor[H - i + 1];
        }
        
        // O(H)
        int[] cnt = new int[H + 1];
        for (int i = 1; i < H + 1; i++) {
            cnt[i] = floor[i] + ceil[i];
        }
        
        int[] sorted = Arrays.stream(cnt).sorted().toArray();
        int minVal = sorted[1];
        int minValCnt = 0;
        for (int i = 1; i < sorted.length; i++) {
            int val = sorted[i];
            if (minVal == val) {
                minValCnt++;
            } else {
                break;
            }
        }

        System.out.println(minVal + " " + minValCnt);
    }
}
