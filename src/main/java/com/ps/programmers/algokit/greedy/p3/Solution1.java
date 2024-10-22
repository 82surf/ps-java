package com.ps.programmers.algokit.greedy.p3;

import java.util.*;

// 시간초과
class Solution1 {
    public String solution(String number, int k) {
        int[] numArr = Arrays.stream(number.split("")).mapToInt(Integer::parseInt).toArray();
        return getMaxNum(numArr, k);
    }

    private String getMaxNum(int[] nums, int k) {
        for (int i = 0; i < nums.length; i++) {
            if (k == 0) {
                break;
            }
            boolean processed = process(nums);
            if (processed) {
                k--;
            }
        }
        int idx = nums.length - 1;
        while (k > 0) {
            nums[idx] = -1;
            k--;
            idx--;
        }
        String maxNum = "";
        for (int n : nums) {
            if (n != -1) {
                maxNum += String.valueOf(n);
            }
        }
        return maxNum;
    }

    private boolean process(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == -1) {
                continue;
            }

            int nextIdx = i + 1;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] != -1) {
                    nextIdx = j;
                    break;
                }
            }

            if (nums[i] < nums[nextIdx]) {
                nums[i] = -1;
                return true;
            }
        }
        return false;
    }
}
