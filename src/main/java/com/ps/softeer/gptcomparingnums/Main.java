package com.ps.softeer.gptcomparingnums;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<String> answer = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            answer.add(br.readLine());
        }

        Collections.sort(answer, (n1, n2) -> {
            Integer n1A = null;
            Integer n1B = null;
            Integer n2A = null;
            Integer n2B = null;
            if (n1.contains(".")) {
                String[] n1Arr = n1.split("\\.");
                // System.out.println(n1);
                // System.out.println(Arrays.toString(n1Arr));
                n1A = Integer.parseInt(n1Arr[0]);
                n1B = Integer.parseInt(n1Arr[1]);
            } else {
                n1A = Integer.parseInt(n1);
            }
            if (n2.contains(".")) {
                String[] n2Arr = n2.split("\\.");
                n2A = Integer.parseInt(n2Arr[0]);
                n2B = Integer.parseInt(n2Arr[1]);
            } else {
                n2A = Integer.parseInt(n2);
            }
            if (n1A != n2A) {
                return Integer.compare(n1A, n2A);
            } else if (n1B != null && n2B != null) {
                return Integer.compare(n1B, n2B);
            } else if (n1B != null && n2B == null) {
                return 1;
            } else if (n1B == null && n2B != null) {
                return -1;
            } else {
                return 0;
            }
        });
        answer.forEach(System.out::println);
    }
}
