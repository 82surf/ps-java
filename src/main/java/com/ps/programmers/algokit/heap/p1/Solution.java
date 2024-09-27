package com.ps.programmers.algokit.heap.p1;

import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int s : scoville) {
            pq.add(s);
        }
        int answer = 0;
        while (pq.peek() < K) {
            int a = pq.poll();
            int b = pq.poll();
            pq.add(a + (b * 2));
            answer++;
            if (pq.size() == 1) {
                return pq.peek() >= K ? answer : -1;
            }
        }
        return answer;
    }
}