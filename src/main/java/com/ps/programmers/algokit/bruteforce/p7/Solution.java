package com.ps.programmers.algokit.bruteforce.p7;

import java.util.*;

class Solution {
    private String[] alphabets = {"A", "E", "I", "O", "U"};
    private List<String> current = new ArrayList<>();
    private int cnt = 0;
    private int answer = 0;

    public int solution(String word) {
        search(word);
        return answer;
    }

    private void search(String target) {
        if (answer != 0) {
            return;
        }

        if (!current.isEmpty()) {
            cnt++;
            String currStr = String.join("", current);
            if (currStr.equals(target)) {
                answer = cnt;
                return;
            }
        }

        if (current.size() >= 5) {
            return;
        }

        for (int i = 0; i < 5; i++) {
            current.add(alphabets[i]);
            search(target);
            current.remove(current.size() - 1);
        }
    }
}