package com.ps.programmers.algokit.dfsbfs.p3;

import java.util.*;

class Solution {
    int answer = -1;

    public int solution(int[][] maps) {
        bfs(maps);
        return answer;
    }

    private void bfs(int[][] maps) {
        int rLen = maps.length;
        int cLen = maps[0].length;
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[rLen][cLen];
        visited[0][0] = true;
        queue.addLast(new int[]{0, 0, 1});
        int[][] delta = { {0, 1}, {1, 0}, {0, -1}, {-1, 0} };
        while (!queue.isEmpty()) {
            int[] curr = queue.removeFirst();
            int r = curr[0];
            int c = curr[1];
            int cnt = curr[2];
            for (int[] d : delta) {
                int nr = r + d[0];
                int nc = c + d[1];
                if (0 <= nr && nr < rLen && 0 <= nc && nc < cLen) {
                    if (!visited[nr][nc] && maps[nr][nc] == 1) {
                        if (nr == rLen - 1 && nc == cLen - 1) {
                            answer = cnt + 1;
                            return;
                        }
                        queue.addLast(new int[]{nr, nc, cnt + 1});
                        visited[nr][nc] = true;
                    }
                }
            }
        }
    }
}