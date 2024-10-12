package com.ps.programmers.algokit.sort.p3;

import java.util.Arrays;
import java.util.Collections;

// 성공
public class Solution {
    public int solution(int[] citations) {
        int[] cArr = Arrays.stream(citations)
                .boxed()
                .sorted(Collections.reverseOrder())
                .mapToInt(Integer::intValue)
                .toArray();
        int answer = 0;
        for (int i = 0; i < citations.length; i++) {
            if (cArr[i] >= i + 1) {
                answer = i + 1;
            }
        }
        return answer;
    }
}
