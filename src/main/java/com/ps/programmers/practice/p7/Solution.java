package com.ps.programmers.practice.p7;

import java.util.*;

// 문제명: 석유 시추
// 링크: https://school.programmers.co.kr/learn/courses/30/lessons/250136
class Solution {
    int[][] delta = { {0, 1}, {1, 0}, {0, -1}, {-1, 0} };
    int[] oilCnt;

    public int solution(int[][] land) {
        oilCnt = new int[land[0].length];
        startRecordOilCnt(land);
        return Arrays.stream(oilCnt).max().getAsInt();
    }

    void startRecordOilCnt(int[][] land) {
        int rLen = land.length;
        int cLen = land[0].length;
        boolean[][] visited = new boolean[rLen][cLen];

        for (int i = 0; i < rLen; i++) {
            for (int j = 0; j < cLen; j++) {
                if (land[i][j] == 1 && !visited[i][j]) {
                    recordOilCnt(i, j, land, visited);
                }
            }
        }
    }

    void recordOilCnt(int sr, int sc, int[][] land, boolean[][] visited) {
        int rLen = land.length;
        int cLen = land[0].length;
        visited[sr][sc] = true;

        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.addLast(new int[]{sr, sc});

        int cnt = 0;
        int minC = sc;
        int maxC = sc;
        while (!q.isEmpty()) {
            cnt++;
            int[] rc = q.removeFirst();
            int r = rc[0];
            int c = rc[1];
            if (minC > c) {
                minC = c;
            }
            if (maxC < c) {
                maxC = c;
            }

            for (int[] d : delta) {
                int nr = r + d[0];
                int nc = c + d[1];
                if (0 <= nr && nr < rLen && 0 <= nc && nc < cLen && !visited[nr][nc] && land[nr][nc] == 1) {
                    visited[nr][nc] = true;;
                    q.addLast(new int[]{nr, nc});
                }
            }
        }

        for (int i = minC; i <= maxC; i++) {
            oilCnt[i] += cnt;
        }
    }
}
