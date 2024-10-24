package com.ps.programmers.algokit.dp.p1;

import java.util.*;

class Solution {
    private int answer = -1;

    public int solution(int N, int number) {
        List<HashSet<Integer>> dp = new ArrayList<>();
        process(N, number, dp);
        return answer;
    }

    private void process(int N, int target, List<HashSet<Integer>> dp) {
        if (dp.size() == 0) {
            HashSet<Integer> set = new HashSet<>();
            set.add(N);
            if (set.contains(target)) {
                answer = 1;
                return;
            }
            dp.add(set);
            process(N, target, dp);
        } else if (dp.size() > 8) {
            return;
        } else {
            int currSize = dp.size() + 1;
            HashSet<Integer> curr = new HashSet<>();
            curr.add(getFirstNum(N, currSize));
            for (int i = 0; i < dp.size(); i++) {
                HashSet<Integer> setA = dp.get(i);
                HashSet<Integer> setB = dp.get(dp.size() - 1 - i);
                for (int a : setA) {
                    for (int b : setB) {
                        addToSet(a, b, curr);
                        addToSet(b, a, curr);
                    }
                }
            }
            if (curr.contains(target)) {
                answer = currSize;
                return;
            }
            dp.add(curr);
            process(N, target, dp);
        }
    }

    private void addToSet(int a, int b, HashSet<Integer> set) {
        set.add(a + b);
        set.add(a - b);
        set.add(b - a);
        set.add(a * b);
        if (a != 0) {
            set.add(b / a);
        }
        if (b != 0) {
            set.add(a / b);
        }
    }

    private int getFirstNum(int N, int size) {
        String numStr = "";
        for (int i = 0; i < size; i++) {
            numStr += String.valueOf(N);
        }
        return Integer.parseInt(numStr);
    }
}