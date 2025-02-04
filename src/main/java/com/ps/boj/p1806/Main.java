package com.ps.boj.p1806;

import java.io.*;
import java.util.*;
import com.ps.util.InputManager;

public class Main {

    static long S;
    
    public static void main(String[] args) throws IOException {
        InputManager.setInput("p1806");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        // 누적합 배열 생성 O(N)
        long[] arr = new long[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            long n = Long.parseLong(st.nextToken());
            arr[i] = arr[i - 1] + n;
        }

        // 탐색을 시작할 인덱스 찾기 O(N)
        int startIdx = -1;
        for (int i = 0; i < N + 1; i++) {
            if (arr[i] >= S) {
                startIdx = i;
                break;
            }
        }
        if (startIdx == -1) {
            System.out.println(0);
            return;
        }

        // 합이 S 이상이면서 가장 짧은 구간의 길이 찾기 O(NlogN)
        int answer = Integer.MAX_VALUE;
        for (int i = startIdx; i < N + 1; i++) {
            int result = check(i, arr);
            if (answer > result) {
                answer = result;
            }
        }
        System.out.println(answer == Integer.MAX_VALUE ? 0 : answer);
    }

    public static int check(int idx, long[] arr) {
        int left = 0;
        int right = idx;
        long condition = arr[idx] - S;

        while (left + 1 < right) {
            int mid = (left + right) / 2;
            if (arr[mid] <= condition) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return idx - left;
    }
}
