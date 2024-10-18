package com.ps.programmers.algokit.bruteforce.p5;

import java.util.*;
import java.util.stream.*;

class Solution {
    public int solution(int k, int[][] dungeons) {
        boolean[] visited = new boolean[dungeons.length];
        List<Integer[]> current = new ArrayList<>();
        List<List<Integer[]>> result = new ArrayList<>();
        permute(dungeons, visited, current, result);
        return getMaxCnt(k, result);
    }

    private void permute(int[][] dungeons, boolean[] visited, List<Integer[]> current, List<List<Integer[]>> result) {
        if (current.size() == dungeons.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = 0; i < dungeons.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                current.add(Arrays.stream(dungeons[i]).boxed().toArray(Integer[]::new));
                permute(dungeons, visited, current, result);
                visited[i] = false;
                current.remove(current.size() - 1);
            }
        }
    }

    private int explore(int k, List<Integer[]> dungeons) {
        int cnt = 0;
        for (Integer[] dungeon : dungeons) {
            int require = dungeon[0];
            int consume = dungeon[1];
            if (require > k) {
                break;
            }
            cnt++;
            k -= consume;
        }
        return cnt;
    }

    private int getMaxCnt(int k, List<List<Integer[]>> permutation) {
        int maxCnt = 0;
        for (List<Integer[]> dungeons : permutation) {
            int cnt = explore(k, dungeons);
            if (maxCnt < cnt) {
                maxCnt = cnt;
            }
        }
        return maxCnt;
    }
}