package com.ps.boj.p6064;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        String path = System.getProperty("user.dir") + "/src/main/java/com/ps/boj/p6064/input.txt";
        System.setIn(Files.newInputStream(Paths.get(path)));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        while (TC-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int lcm = (M * N) / getGCD(M, N);
            int answer = -1;
            for (int i = x; i < lcm; i += M) {
                if (i % N == y) {
                    answer = i + 1;
                    break;
                }
            }
            System.out.println(answer);
        }
    }

    public static int getGCD(int a, int b) {
        if (a < b) {
            int tmp = a;
            a = b;
            b = tmp;
        }
        if (b == 0) return a;
        return getGCD(b, a % b);
    }
}
