package com.ps.boj.p5525;

import java.io.*;
import java.nio.file.*;

public class Main {
    public static void main(String[] args) throws IOException {
        String path = System.getProperty("user.dir") + "/src/main/java/com/ps/boj/p5525/input.txt";
        System.setIn(Files.newInputStream(Paths.get(path)));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        String S = br.readLine();

        int answer = 0;
        boolean currentlyAdded = false;
        boolean oChecked = false;
        int oCnt = 0;
        int i = 0;
        while (i < S.length()) {
            char curr = S.charAt(i);

            if (currentlyAdded) {
                if (!oChecked && curr == 'O') {
                    oChecked = true;
                    i++;
                    continue;
                } else if (oChecked && curr == 'I') {
                    answer++;
                    oChecked = false;
                    i++;
                    continue;
                } else {
                    currentlyAdded = false;
                    oChecked = false;
                }
            }

            if (i > 0 && curr == S.charAt(i - 1)) {
                oCnt = 0;
            } else if (i > 0 && curr == 'O') {
                oCnt++;
            } else if (curr == 'I' && oCnt == N) {
                answer++;
                oCnt = 0;
                currentlyAdded = true;
            }


            i++;
        }

        System.out.println(answer);
    }
}
