package com.ps.programmers.practice.p2;

import java.util.*;

// 문제명: 퍼즐 게임 챌린지
// 링크: https://school.programmers.co.kr/learn/courses/30/lessons/340212
class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int maxLevel = Arrays.stream(diffs).max().getAsInt();
        return lowerBound(maxLevel, diffs, times, limit);
    }

    private int lowerBound(int maxLevel, int[] diffs, int[] times, long limit) {
        int left = 1;
        int right = maxLevel;
        while (left < right) {
            int mid = (left + right) / 2;
            long time = calcTime(mid, diffs, times);
            if (time > limit) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    // O(N)
    private long calcTime(int level, int[] diffs, int[] times) {
        long time = 0;
        for (int i = 0; i < diffs.length; i++) {
            int diff = diffs[i];
            int timeCur = times[i];
            if (diff <= level) {
                time += timeCur;
            } else {
                int timePrev = times[i - 1];
                time += (diff - level) * (timeCur + timePrev) + timeCur;
            }
        }
        return time;
    }
}
