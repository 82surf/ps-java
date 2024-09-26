package com.ps.programmers.algokit.stackqueue.p6;

import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        ArrayDeque<Integer> priceStack = new ArrayDeque<>();
        ArrayDeque<Integer> idxStack = new ArrayDeque<>();
        int[] answer = new int[prices.length];

        for (int idx = 0; idx < prices.length; idx++) {
            int price = prices[idx];
            if (priceStack.isEmpty() || priceStack.peekLast() <= price) {
                priceStack.addLast(price);
                idxStack.addLast(idx);
            } else {
                while (!priceStack.isEmpty() && priceStack.peekLast() > price) {
                    priceStack.removeLast();
                    int popIdx = idxStack.removeLast();
                    answer[popIdx] = idx - popIdx;
                }
                priceStack.addLast(price);
                idxStack.addLast(idx);
            }
        }
        while (!idxStack.isEmpty()) {
            int popIdx = idxStack.removeLast();
            answer[popIdx] = prices.length - popIdx - 1;
        }
        return answer;
    }
}
