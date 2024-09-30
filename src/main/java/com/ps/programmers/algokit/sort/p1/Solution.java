package com.ps.programmers.algokit.sort.p1;

import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        for (int idx = 0; idx < commands.length; idx++) {
            int[] command = commands[idx];
            int i = command[0];
            int j = command[1];
            int k = command[2];
            int[] splitArr = Arrays.copyOfRange(array, i - 1, j);
            Arrays.sort(splitArr);
            answer[idx] = splitArr[k - 1];
        }
        return answer;
    }
}