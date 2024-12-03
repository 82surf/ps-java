package com.ps.boj.p9251;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // String path = System.getProperty("user.dir") + "/src/main/java/com/ps/boj/p9251/input.txt";
        // System.setIn(Files.newInputStream(Paths.get(path)));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str1 = br.readLine();
        String str2 = br.readLine();
        int[][] lcs = new int[str1.length() + 1][str2.length() + 1];
        int answer = 0;
        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    int val = lcs[i - 1][j - 1] + 1;
                    lcs[i][j] = val;
                    if (answer < val) {
                        answer = val;
                    }
                } else {
                    int val = Math.max(lcs[i - 1][j], lcs[i][j - 1]);
                    lcs[i][j] = val;
                    if (answer < val) {
                        answer = val;
                    }
                }
            }
        }
        
        System.out.println(answer);
    }
}
