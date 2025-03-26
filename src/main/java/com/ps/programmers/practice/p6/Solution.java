package com.ps.programmers.practice.p6;

import java.util.*;

// 문제명: 요격 시스템
// 링크: https://school.programmers.co.kr/learn/courses/30/lessons/181188
class Solution {
    public int solution(int[][] targets) {
        Arrays.sort(targets, (t1, t2) -> {
            if (t1[1] == t2[1]) return t1[0] - t2[0];
            return t1[1] - t2[1];
        });

        int answer = 0;
        boolean[] boomed = new boolean[targets.length];
        for (int i = 0; i < targets.length; i++) {
            if (!boomed[i]) {
                boomed[i] = true;
                answer++;
                int end = targets[i][1];
                for (int j = i + 1; j < targets.length; j++) {
                    int start = targets[j][0];
                    if (start < end) {
                        boomed[j] = true;
                    }
                }
            }
        }

        return answer;
    }
}