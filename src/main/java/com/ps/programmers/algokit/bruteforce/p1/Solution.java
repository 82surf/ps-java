package com.ps.programmers.algokit.bruteforce.p1;

import java.util.*;

class Solution {
    public int solution(int[][] sizes) {
        for (int[] size : sizes) {
            System.out.println(Arrays.toString(size));
            if (size[0] < size[1]) {
                int left = size[0];
                size[0] = size[1];
                size[1] = left;
            }
        }

        int leftMax = 0;
        int rightMax = 0;
        for (int[] size : sizes) {
            if (size[0] > leftMax) {
                leftMax = size[0];
            }
            if (size[1] > rightMax) {
                rightMax = size[1];
            }
        }

        return leftMax * rightMax;
    }
}