package com.ps.programmers.algokit.dfsbfs.p1;

class Solution {
    private int answer = 0;

    public int solution(int[] numbers, int target) {
        calc(0, target, -1, numbers);
        return answer;
    }

    private void calc(int curr, int target, int depth, int[] numbers) {
        if (depth == numbers.length - 1) {
            if (curr == target) {
                answer++;
            }
            return;
        }
        depth++;
        calc(curr + numbers[depth], target, depth, numbers);
        calc(curr - numbers[depth], target, depth, numbers);
    }
}