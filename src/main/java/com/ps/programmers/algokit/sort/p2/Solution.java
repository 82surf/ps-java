package com.ps.programmers.algokit.sort.p2;

import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        String[] strNumArr = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            strNumArr[i] = String.valueOf(numbers[i]);
        }
        Comparator<String> comparator = (s1, s2) -> {
            int n1 = Integer.parseInt(s1 + s2);
            int n2 = Integer.parseInt(s2 + s1);
            return n2 - n1;
        };
        Arrays.sort(strNumArr, comparator);
        return strNumArr[0].equals("0") ? "0" : String.join("", strNumArr);
    }
}