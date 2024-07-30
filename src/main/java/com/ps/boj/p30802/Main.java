package com.ps.boj.p30802;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] people = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] TP = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int T = TP[0];
        int P = TP[1];

        int answer1 = 0;
        for (int cnt : people) {
            if (cnt % T == 0) {
                answer1 += cnt / T;
            } else {
                answer1 += cnt / T + 1;
            }
        }
        int total = Arrays.stream(people).reduce(Integer::sum).getAsInt();
        int answer2 = total / P;
        int answer3 = total - answer2 * P;
        System.out.println(answer1);
        System.out.println(answer2 + " " + answer3);
    }
}
