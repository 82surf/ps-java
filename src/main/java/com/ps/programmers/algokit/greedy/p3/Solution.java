package com.ps.programmers.algokit.greedy.p3;

import java.util.*;

class Solution {
    public String solution(String number, int k) {
        int answerLen = number.length() - k;
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < number.length(); i++) {
            char n = number.charAt(i);
            while (!stack.isEmpty() && k > 0 && stack.getLast() < n) {
                stack.removeLast();
                k--;
            }
            stack.addLast(n);
        }
        String answer = "";
        for (char n : stack) {
            answer += String.valueOf(n);
        }
        return answer.substring(0, answerLen);
    }
}