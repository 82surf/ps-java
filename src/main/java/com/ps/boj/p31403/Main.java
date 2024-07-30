package com.ps.boj.p31403;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] strNums = new String[3];
        int[] intNums = new int[3];
        for (int i = 0; i < 3; i++) {
            String strNum = reader.readLine();
            strNums[i] = strNum;
            intNums[i] = Integer.parseInt(strNum);
        }
        String answer1 = String.valueOf(intNums[0] + intNums[1] - intNums[2]);
        String answer2 = String.valueOf(Integer.parseInt(strNums[0] + strNums[1]) - intNums[2]);
        System.out.println(answer1);
        System.out.println(answer2);
    }
}
