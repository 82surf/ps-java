package com.ps.programmers.practice.p5;

import java.util.*;

// 문제명: 지게차와 크레인
// 링크: https://school.programmers.co.kr/learn/courses/30/lessons/388353
class Solution {
    int n;
    int m;
    String[][] containers;

    public int solution(String[] storage, String[] requests) {
        init(storage);
        processRequests(requests);
        return countContainers();
    }

    void init(String[] storage) {
        n = storage.length + 2;
        m = storage[0].length() + 2;

        String[][] tmp = new String[n - 2][];
        for (int i = 0; i < n - 2; i++) {
            tmp[i] = storage[i].split("");
        }

        containers = new String[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 || j == 0 || i == n - 1 || j == m - 1) {
                    containers[i][j] = null;
                } else {
                    containers[i][j] = tmp[i - 1][j - 1];
                }
            }
        }
    }

    void processRequests(String[] requests) {
        for (String r : requests) {
            if (r.length() == 1) {
                deleteByCar(r);
            } else {
                String[] tmp = r.split("");
                deleteByCrane(tmp[0]);
            }
        }
    }

    int countContainers() {
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (containers[i][j] != null) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    void deleteByCar(String target) {
        Set<int[]> targets = new HashSet<>();
        int[][] delta = { {0, 1}, {1, 0}, {0, -1}, {-1, 0} };
        boolean[][] visited = new boolean[n][m];
        visited[0][0] = true;
        ArrayDeque<int[]> stack = new ArrayDeque<>();
        stack.addLast(new int[]{0, 0});
        while (!stack.isEmpty()) {
            int[] curr = stack.removeLast();
            int r = curr[0];
            int c = curr[1];
            for (int[] d : delta) {
                int nr = r + d[0];
                int nc = c + d[1];
                if (0 <= nr && nr < n &&
                        0 <= nc && nc < m &&
                        containers[nr][nc] == null && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    stack.addLast(new int[]{nr, nc});
                    for (int[] dd : delta) {
                        int nnr = nr + dd[0];
                        int nnc = nc + dd[1];
                        if (0 <= nnr && nnr < n &&
                                0 <= nnc && nnc < m &&
                                containers[nnr][nnc] != null && containers[nnr][nnc].equals(target) &&
                                !visited[nnr][nnc]) {
                            visited[nnr][nnc] = true;
                            targets.add(new int[]{nnr, nnc});
                        }
                    }
                }
            }
        }

        for (int[] t : targets) {
            containers[t[0]][t[1]] = null;
        }
    }

    void deleteByCrane(String target) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (containers[i][j] != null && containers[i][j].equals(target)) {
                    containers[i][j] = null;
                }
            }
        }
    }
}