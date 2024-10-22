package com.ps.programmers.algokit.greedy.p3;

import java.util.*;
import java.util.stream.*;

// 시간초과
class Solution2 {
    public String solution(String number, int k) {
        LinkedList<Integer> nums = stringToLinkedList(number);
        return getAnswer(k, number.length(), nums);
    }

    private LinkedList<Integer> stringToLinkedList(String str) {
        return Arrays
                .stream(str.split(""))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toCollection(LinkedList::new));
    }

    private String getAnswer(int k, int n, LinkedList<Integer> nums) {
        for (int i = 0; i < n; i++) {
            if (k > 0 && process(nums)) {
                k--;
            }
        }
        while (k > 0) {
            k--;
            nums.remove(nums.size() - 1);
        }
        String answer = "";
        for (int num : nums) {
            answer += num;
        }
        return answer;
    }

    private boolean process(LinkedList<Integer> nums) {
        int idx = 0;
        for (int n : nums) {
            if (idx < nums.size() - 1) {
                if (n < nums.get(idx + 1)) {
                    nums.remove(idx);
                    return true;
                } else {
                    idx++;
                }
            }
        }
        return false;
    }
}