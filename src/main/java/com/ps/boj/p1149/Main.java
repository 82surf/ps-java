package com.ps.boj.p1149;

import java.io.*;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        String path = System.getProperty("user.dir") + "/src/main/java/com/ps/boj/p1149/input.txt";
        System.setIn(Files.newInputStream(Paths.get(path)));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] houses = new int[N][];
        for (int i = 0; i < N; i++) {
            houses[i] = Arrays
                    .stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            if (i > 0) {
                houses[i][0] += Math.min(houses[i - 1][1], houses[i - 1][2]);
                houses[i][1] += Math.min(houses[i - 1][0], houses[i - 1][2]);
                houses[i][2] += Math.min(houses[i - 1][0], houses[i - 1][1]);
            }
        }
        System.out.println(Arrays.stream(houses[N - 1]).min().getAsInt());
    }
}
