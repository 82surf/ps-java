package com.ps.boj.p15565;

import java.io.*;
import java.util.*;
import com.ps.util.InputManager;

public class Main {
    public static void main(String[] args) throws IOException {
        InputManager.setInput("p15565");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        List<Integer> lionIdxList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if (nums[i] == 1) {
                lionIdxList.add(i);
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int i = 0; i <= lionIdxList.size() - K; i++) {
            int a = lionIdxList.get(i);
            int b = lionIdxList.get(i + K - 1);
            if (answer > b - a + 1) {
                answer = b - a + 1;
            }
        }
        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }
}
