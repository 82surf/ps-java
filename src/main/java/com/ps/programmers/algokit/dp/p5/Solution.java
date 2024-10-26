package com.ps.programmers.algokit.dp.p5;

class Solution {
    public int solution(int[] money) {
        int len = money.length;
        int[] dp1 = new int[len];
        dp1[0] = money[0];
        dp1[1] = Math.max(money[0], money[1]);
        for (int i = 2; i < len - 1; i++) {
            dp1[i] = Math.max(dp1[i - 1], money[i] + dp1[i - 2]);
        }

        int[] dp2 = new int[len];
        dp2[0] = 0;
        dp2[1] = money[1];
        for (int i = 2; i < len; i++) {
            dp2[i] = Math.max(dp2[i - 1], money[i] + dp2[i - 2]);
        }

        return Math.max(dp1[len - 2], dp2[len - 1]);
    }
}