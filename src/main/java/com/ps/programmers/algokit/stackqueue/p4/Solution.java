package com.ps.programmers.algokit.stackqueue.p4;

import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        ArrayDeque<Integer> pQ = new ArrayDeque<>();
        ArrayDeque<Integer> iQ = new ArrayDeque<>();
        ArrayDeque<Integer> sortedpQ = new ArrayDeque<>();
        for (int i = 0; i < priorities.length; i++) {
            pQ.addLast(priorities[i]);
            iQ.addLast(i);
        }
        int[] pArr = Arrays.stream(priorities)
                .boxed()
                .sorted(Collections.reverseOrder())
                .mapToInt(Integer::intValue)
                .toArray();
        for (int i = 0; i < priorities.length; i++) {
            sortedpQ.addLast(pArr[i]);
        }

        int answer = 0;
        while (true) {
            int p = pQ.removeFirst();
            int i = iQ.removeFirst();
            if (p != sortedpQ.peekFirst()) {
                pQ.addLast(p);
                iQ.addLast(i);
            } else if (i == location) {
                answer++;
                break;
            } else {
                answer++;
                sortedpQ.removeFirst();
            }
        }
        return answer;
    }
}
