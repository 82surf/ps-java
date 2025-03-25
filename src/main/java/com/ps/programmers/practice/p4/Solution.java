package com.ps.programmers.practice.p4;

// 문제명: 혼자서 하는 틱택토
// 링크: https://school.programmers.co.kr/learn/courses/30/lessons/160585#
class Solution {
    public int solution(String[] board) {
        String[][] tictacto = new String[3][];
        for (int i = 0; i < 3; i++) {
            tictacto[i] = board[i].split("");
        }

        int oCnt = 0;
        int xCnt = 0;
        int oWinCnt = 0;
        int xWinCnt = 0;
        String lToR = "";
        String rToL = "";
        for (int i = 0; i < 3; i++) {
            String row = "";
            String col = "";
            for (int j = 0; j < 3; j++) {
                row += tictacto[i][j];
                col += tictacto[j][i];

                if (i == j) {
                    lToR += tictacto[i][j];
                }
                if (i + j == 2) {
                    rToL += tictacto[i][j];
                }

                if (tictacto[i][j].equals("O")) {
                    oCnt++;
                } else if (tictacto[i][j].equals("X")) {
                    xCnt++;
                }
            }

            if (row.equals("OOO")) {
                oWinCnt++;
            }
            if (col.equals("OOO")) {
                oWinCnt++;
            }

            if (row.equals("XXX")) {
                xWinCnt++;
            }
            if (col.equals("XXX")) {
                xWinCnt++;
            }
        }

        if (lToR.equals("OOO")) {
            oWinCnt++;
        }
        if (rToL.equals("OOO")) {
            oWinCnt++;
        }
        if (lToR.equals("XXX")) {
            xWinCnt++;
        }
        if (rToL.equals("XXX")) {
            xWinCnt++;
        }

        if (
                ((oWinCnt == 2 || oWinCnt == 1) && xWinCnt == 0 && oCnt - 1 == xCnt) ||
                        (oWinCnt == 0 && xWinCnt == 1 && oCnt == xCnt) ||
                        (oWinCnt == 0 && xWinCnt == 0 && (oCnt - 1 == xCnt || oCnt == xCnt))
        ) {
            return 1;
        }
        return 0;
    }
}