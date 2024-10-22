package com.ps.programmers.algokit.greedy.p6;

import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        Arrays.sort(routes, (a, b) -> Integer.compare(a[1], b[1]));
        int answer = 1;
        int max = routes[0][1];
        for (int[] route: routes) {
            System.out.println(Arrays.toString(route));
            if (max < route[0]) {
                answer++;
                max = route[1];
            }
        }
        return answer;
    }
}