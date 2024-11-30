package com.ps.boj.p17144;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        String path = System.getProperty("user.dir") + "/src/main/java/com/ps/boj/p17144/input.txt";
        System.setIn(Files.newInputStream(Paths.get(path)));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        int[][] matrix = new int[R][];
        for (int i = 0; i < R; i++) {
            int[] row = Arrays
            .stream(br.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();
            matrix[i] = row;
        }

        while (T-- > 0) {
            spreadOut(R, C, matrix);
            runMachine(R, C, matrix);
        }
        printAnswer(R, C, matrix);
    }

    public static void printAnswer(int r, int c, int[][] matrix) {
        int answer = 0;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                answer += matrix[i][j];
            }
        }
        System.out.println(answer + 2);
    }

    public static void printMatrix(int R, int[][] matrix) {
        for (int i = 0; i < R; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
        System.out.println();
    }

    public static void spreadOut(int r, int c, int[][] matrix) {
        int[][] delta = { {0, 1}, {1, 0}, {0, -1}, {-1, 0} };
        int[][] spreadMatrix = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                int cnt = 0;
                int spreadAmount = matrix[i][j] / 5;
                if (matrix[i][j] > 0) {
                    for (int[] d : delta) {
                        int nr = i + d[0];
                        int nc = j + d[1];
                        if (0 <= nr && nr < r && 0 <= nc && nc < c && matrix[nr][nc] != -1) {
                            spreadMatrix[nr][nc] += spreadAmount;
                            cnt++;
                        }
                    }
                }
                spreadMatrix[i][j] -= cnt * spreadAmount;
            }
        }
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                matrix[i][j] += spreadMatrix[i][j];
            }
        }
    }

    public static int searchMachineRowIdx(int r, int[][] matrix) {
        for (int i = 0; i < r; i++) {
            if (matrix[i][0] == -1) {
                return i;
            }
        }
        return -1;
    }

    public static void runMachine(int r, int c, int[][] matrix) {
        int[][] delta = { {-1, 0}, {0, 1}, {1, 0}, {0, -1} };
        int deltaIdx = 0;
        int machineRowIdx = searchMachineRowIdx(r, matrix);
        int i = machineRowIdx;
        int j = 0;
        while (deltaIdx < 4) {
            int[] d = delta[deltaIdx];
            int nr = i + d[0];
            int nc = j + d[1];
            if (0 <= nr && nr <= machineRowIdx && 0 <= nc && nc < c) {
                if (matrix[i][j] == -1) {
                    matrix[nr][nc] = 0;
                } else if (matrix[nr][nc] == -1) {
                    matrix[i][j] = 0;
                } else {
                    matrix[i][j] = matrix[nr][nc];
                }
                i = nr;
                j = nc;
            } else {
                deltaIdx++;
            }
        }

        int[][] delta2 = { {1, 0}, {0, 1}, {-1, 0}, {0, -1} };
        int delta2Idx = 0;
        int machineRowIdx2 = machineRowIdx + 1;
        int i2 = machineRowIdx2;
        int j2 = 0;
        while (delta2Idx < 4) {
            int[] d = delta2[delta2Idx];
            int nr = i2 + d[0];
            int nc = j2 + d[1];
            if (machineRowIdx2 <= nr && nr < r && 0 <= nc && nc < c) {
                if (matrix[i2][j2] == -1) {
                    matrix[nr][nc] = 0;
                } else if (matrix[nr][nc] == -1) {
                    matrix[i2][j2] = 0;
                } else {
                    matrix[i2][j2] = matrix[nr][nc];
                }
                i2 = nr;
                j2 = nc;
            } else {
                delta2Idx++;
            }
        }
    }
}
