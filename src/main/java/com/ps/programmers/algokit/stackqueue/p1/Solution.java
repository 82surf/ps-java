package com.ps.programmers.algokit.stackqueue.p1;

import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        for (int n : arr) {
            if (q.isEmpty()) {
                q.add(n);
            } else if (q.peekLast() != n) {
                q.add(n);
            }
        }
        return q.stream().mapToInt(Integer::intValue).toArray();
    }
}