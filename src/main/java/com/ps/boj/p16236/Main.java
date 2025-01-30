package com.ps.boj.p16236;

import java.io.*;
import java.util.*;
import com.ps.util.InputManager;

public class Main {
    static int N;
    static int[][] matrix;
    static int answer = 0;
    static int babySharkSize = 2;
    static int babySharkAteCnt = 0;

    public static void main(String[] args) throws IOException, Exception {
        InputManager.setInput("p16236");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        matrix = new int[N][];
        for (int i = 0; i < N; i++) {
            matrix[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        while (true) {
            // 아기 상어 위치 찾기 N * N
            int[] babyShark = findBabyShark();
            // 1. 먹을 수 있는 가장 가까운 물고기 찾기
            int[] target = searchNextTarget(babyShark);
            if (target[0] == -1) {
                break;
            }
            // 2. 물고기 먹기
            eat(babyShark, target);
        }
        System.out.println(answer);
    }

    public static void eat(int[] babyShark, int[] target) {
        answer += target[2];
        matrix[babyShark[0]][babyShark[1]] = 0;
        matrix[target[0]][target[1]] = 9;
        babySharkAteCnt++;
        if (babySharkAteCnt == babySharkSize) {
            babySharkSize++;
            babySharkAteCnt = 0;
        }
    }

    public static int[] findBabyShark() throws Exception {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (matrix[i][j] == 9) {
                    return new int[]{i, j};
                }
            }
        }
        throw new Exception("Baby shark not found");
    }

    public static int[] searchNextTarget(int[] babyShark) {
        int sr = babyShark[0];
        int sc = babyShark[1];
        int[][] delta = { {0, 1}, {1, 0}, {0, -1}, {-1, 0} };
        boolean[][] visited = new boolean[N][N];
        visited[sr][sc] = true;
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.addLast(new int[]{sr, sc, 0});
        PriorityQueue<int[]> targetQ = new PriorityQueue<>(
                Comparator.comparingInt((int[] target) -> target[2])
                .thenComparingInt(target -> target[0])
                .thenComparingInt(target -> target[1])
            );
        while (!q.isEmpty()) {
            int[] curr = q.removeFirst();
            int r = curr[0];
            int c = curr[1];
            int cnt = curr[2];
            for (int[] d : delta) {
                int nr = r + d[0];
                int nc = c + d[1];
                if (0 <= nr && nr < N && 0 <= nc && nc < N && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    if (matrix[nr][nc] <= babySharkSize) {
                        int[] next = new int[]{nr, nc, cnt + 1};
                        q.addLast(next);
                        if (0 < matrix[nr][nc] && matrix[nr][nc] < babySharkSize) {
                            targetQ.offer(next);
                        }
                    }
                }
            }
        }

        if (targetQ.isEmpty()) {
            return new int[]{-1, -1};
        } else {
            return targetQ.poll();
        }
    }
}
