package com.ps.softeer.cartest;

import java.io.*;
import java.util.*;

// [HSAT 7회 정기 코딩 인증평가 기출] 자동차 테스트
// https://softeer.ai/practice/6247/history?questionType=ALGORITHM
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        int[] prices = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Arrays.sort(prices);
        HashMap<Integer, Integer> priceMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            priceMap.put(prices[i], i);
        }

        while (q-- > 0) {
            int m = Integer.parseInt(br.readLine());
            if (priceMap.containsKey(m)) {
                int idx = priceMap.get(m);
                if (idx == 0 || idx == n - 1) {
                    System.out.println(0);
                } else {
                    System.out.println(idx * (n - idx - 1));
                }
            } else {
                System.out.println(0);
            }
        }
    }
}
