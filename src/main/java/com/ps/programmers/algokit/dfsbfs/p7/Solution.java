package com.ps.programmers.algokit.dfsbfs.p7;

import java.util.*;

class Solution {
    public int solution(int[][] game_board, int[][] table) {
        int answer = 0;

        ArrayList<int[][]> gameBoardBlocks = searchBlocks(game_board, 0);
        ArrayList<int[][]> tableBlocks = searchBlocks(table, 1);

        for (int[][] gameBoardBlock : gameBoardBlocks) {
            for (int i = 0; i < tableBlocks.size(); i++) {
                int[][] tableBlock = tableBlocks.get(i);
                if (isSameBlock(gameBoardBlock, tableBlock)) {
                    tableBlocks.remove(i);
                    answer += getBlockCnt(gameBoardBlock);
                    break;
                }
            }
        }

        return answer;
    }

    private int[][] dfs(int sr, int sc, int[][] matrix, int searchVal) {
        int rLen = matrix.length;
        int cLen = matrix[0].length;
        int[][] delta = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        boolean[][] visited = new boolean[rLen][cLen];
        ArrayDeque<int[]> stack = new ArrayDeque<int[]>();
        List<int[]> dots = new ArrayList<>();

        visited[sr][sc] = true;
        stack.addLast(new int[]{sr, sc});
        dots.add(new int[]{sr, sc});

        int minR = sr;
        int minC = sc;
        int maxR = sr;
        int maxC = sc;

        while (!stack.isEmpty()) {
            int[] curr = stack.removeLast();
            int r = curr[0];
            int c = curr[1];
            for (int[] d : delta) {
                int nr = r + d[0];
                int nc = c + d[1];
                if (0 <= nr && nr < rLen && 0 <= nc && nc < cLen) {
                    if (!visited[nr][nc] && matrix[nr][nc] == searchVal) {
                        visited[nr][nc] = true;
                        stack.addLast(new int[]{nr, nc});
                        dots.add(new int[]{nr, nc});

                        if (minR > nr) {
                            minR = nr;
                        }
                        if (minC > nc) {
                            minC = nc;
                        }
                        if (maxR < nr) {
                            maxR = nr;
                        }
                        if (maxC < nc) {
                            maxC = nc;
                        }
                    }
                }
            }
        }

        int height = maxR - minR + 1;
        int width = maxC - minC + 1;

        int[][] block = new int[height][width];
        for (int[] d : dots) {
            block[d[0] - minR][d[1] - minC] = 1;
            matrix[d[0]][d[1]] = searchVal == 0 ? 1 : 0;
        }

        return block;
    }

    private int[][] turn(int[][] block) {
        int rLen = block.length;
        int cLen = block[0].length;

        int[][] turnedBlock = new int[cLen][rLen];

        for (int i = 0; i < rLen; i++) {
            for (int j = 0; j < cLen; j++) {
                if (block[i][j] == 1) {
                    turnedBlock[j][rLen - 1 - i] = 1;
                }
            }
        }

        return turnedBlock;
    }

    private ArrayList<int[][]> searchBlocks(int[][] matrix, int searchVal) {
        ArrayList<int[][]> blocks = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == searchVal) {
                    blocks.add(dfs(i, j, matrix, searchVal));
                }
            }
        }
        return blocks;
    }

    private void printBlocks(List<int[][]> blocks) {
        for (int[][] block : blocks) {
            printBlock(block);
        }
    }

    private void printBlock(int[][] block) {
        for (int i = 0; i < block.length; i++) {
            for (int j = 0; j < block[0].length; j++) {
                System.out.print(block[i][j]);
            }
            System.out.println("");
        }
        System.out.println("");
    }

    private void print(String str) {
        System.out.println(str);
    }

    private int getBlockCnt(int[][] block) {
        int cnt = 0;
        for (int i = 0; i < block.length; i++) {
            for (int j = 0; j < block[0].length; j++) {
                if (block[i][j] == 1) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    private boolean isSameBlock(int[][] blockA, int[][] blockB) {
        int aRLen = blockA.length;
        int aCLen = blockA[0].length;
        int bRLen = blockB.length;
        int bCLen = blockB[0].length;

        if (aRLen == bRLen && aCLen == bCLen && aRLen == aCLen) {
            boolean resultA = checkBlocks(blockA, blockB);
            boolean resultB = checkBlocks(blockA, turn(blockB));
            boolean resultC = checkBlocks(blockA, turn(turn(blockB)));
            boolean resultD = checkBlocks(blockA, turn(turn(turn(blockB))));
            return resultA || resultB || resultC || resultD;
        } else if (aRLen == bRLen && aCLen == bCLen) {
            boolean resultA = checkBlocks(blockA, blockB);
            boolean resultB = checkBlocks(blockA, turn(turn(blockB)));
            return resultA || resultB;
        } else if (aRLen == bCLen && aCLen == bRLen) {
            blockB = turn(blockB);
            boolean resultA = checkBlocks(blockA, blockB);
            boolean resultB = checkBlocks(blockA, turn(turn(blockB)));
            return resultA || resultB;
        } else {
            return false;
        }
    }

    private boolean checkBlocks(int[][] blockA, int[][] blockB) {
        for (int i = 0; i < blockA.length; i++) {
            for (int j = 0; j < blockA[0].length; j++) {
                if (blockA[i][j] != blockB[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}