package com.ps.programmers.algokit.greedy.p1;

import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int[] students = new int[n];
        for (int i = 0; i < n; i++) {
            students[i] = 1;
        }
        for (int r : reserve) {
            students[r - 1]++;
        }
        for (int l : lost) {
            students[l - 1]--;
        }
        for (int i = 0; i < n; i++) {
            if (i > 0 && students[i] == 0 && students[i - 1] == 2) {
                students[i]++;
                students[i - 1]--;
            }
            if (i < n - 1 && students[i] == 0 && students[i + 1] == 2) {
                students[i]++;
                students[i + 1]--;
            }
        }
        return (int) Arrays.stream(students).filter(v -> v > 0).count();
    }
}