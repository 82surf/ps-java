package com.ps.programmers.algokit.stackqueue.p2;

import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> answer = new ArrayList<>();
        int idx = 0;
        while (idx < speeds.length) {
            for (int i = 0; i < speeds.length; i++) {
                progresses[i] += speeds[i];
            }
            int cnt = 0;
            while (idx < speeds.length && progresses[idx] >= 100) {
                cnt++;
                idx++;
            }
            if (cnt > 0) {
                answer.add(cnt);
            }
        }
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}