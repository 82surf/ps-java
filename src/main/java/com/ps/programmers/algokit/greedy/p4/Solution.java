package com.ps.programmers.algokit.greedy.p4;

class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        int widthPlushHeight = (brown - 4) / 2;
        for (int h = 1; h <= widthPlushHeight; h++) {
            int w = widthPlushHeight - h;
            if (h > w) {
                break;
            }
            if (yellow % h == 0 && yellow / h == w) {
                answer[0] = w + 2;
                answer[1] = h + 2;
                return answer;
            }
        }
        return answer;
    }
}