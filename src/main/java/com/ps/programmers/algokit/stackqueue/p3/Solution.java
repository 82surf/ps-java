package com.ps.programmers.algokit.stackqueue.p3;

class Solution {
    boolean solution(String s) {
        int idx = 0;
        int left = 0;
        int right = 0;
        while (idx < s.length()) {
            char c = s.charAt(idx);
            if (c == '(') {
                left++;
            } else {
                left--;
            }
            if (left < right) {
                return false;
            }
            idx++;
        }
        return left != 0 || right != 0 ? false : true;
    }
}