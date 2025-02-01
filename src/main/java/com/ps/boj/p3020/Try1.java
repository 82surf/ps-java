package com.ps.boj.p3020;

import java.io.*;
import java.util.*;
import com.ps.util.InputManager;

// 시간초과
// 입력받는 부분에서 N * H는 10^11로 시간초과 발생
public class Try1 {
    public static void main(String[] args) throws IOException {
        InputManager.setInput("p3020");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        int[] cnt = new int[H + 1];

        // 
        for (int i = 0; i < N; i++) {
            int wallHeight = Integer.parseInt(br.readLine());

            for (int j = 1; j <= wallHeight; j++) {
                if (i % 2 != 0) {
                    cnt[H - j + 1]++;
                } else {
                    cnt[j]++;
                }
            }
        }

        int[] answerArr = new int[N * 2];
        for (int i = 1; i < cnt.length; i++) {
            int n = cnt[i];
            answerArr[n]++;
        }

        int minVal = 0;
        int minValCnt = 0;
        for (int i = 1; i < answerArr.length; i++) {
            if (answerArr[i] != 0) {
                minVal = i;
                minValCnt = answerArr[i];
                break;
            }
        }

        System.out.println(minVal + " " + minValCnt);
    }
}
