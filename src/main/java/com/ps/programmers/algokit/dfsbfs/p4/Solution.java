package com.ps.programmers.algokit.dfsbfs.p4;

import java.util.*;

class Solution {
    private int answer = 0;

    public int solution(String begin, String target, String[] words) {
        if (!isPossible(target, words)) {
            return 0;
        } else {
            bfs(begin, target, words);
            return answer;
        }
    }

    private boolean isPossible(String target, String[] words) {
        for (String word : words) {
            if (target.equals(word)) {
                return true;
            }
        }
        return false;
    }

    private boolean isValid(String a, String b) {
        int cnt = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                cnt++;
            }
            if (cnt > 1) {
                return false;
            }
        }
        return cnt == 1;
    }

    private void bfs(String begin, String target, String[] words) {
        boolean[] visited = new boolean[words.length];
        ArrayDeque<String[]> queue = new ArrayDeque<>();
        queue.addLast(new String[]{begin, "0"});
        while (!queue.isEmpty()) {
            String[] item = queue.removeFirst();
            String curr = item[0];
            int cnt = Integer.parseInt(item[1]);
            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                if (!visited[i] && isValid(curr, word)) {
                    if (word.equals(target)) {
                        answer = cnt + 1;
                        return;
                    }
                    queue.addLast(new String[]{word, String.valueOf(cnt + 1)});
                    visited[i] = true;
                }
            }
        }
    }
}