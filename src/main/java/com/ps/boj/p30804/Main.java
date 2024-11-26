package com.ps.boj.p30804;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        String path = System.getProperty("user.dir") + "/src/main/java/com/ps/boj/p30804/input.txt";
        System.setIn(new FileInputStream(path));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String nums = br.readLine();

        HashSet<Character> set = new HashSet<>();
        int maxCnt = 0;
        int cnt = 0;
        char lastChunk = 'a';
        int lastChunkCnt = 0;
        for (int i = 0; i < nums.length(); i += 2) {
            char curr = nums.charAt(i);

            if (set.size() < 2) {
                set.add(curr);
                cnt++;
            } else if (set.contains(curr)) {
                cnt++;
            } else {
                if (maxCnt < cnt) {
                    maxCnt = cnt;
                }

                cnt = lastChunkCnt + 1;
                Iterator<Character> iterator = set.iterator();
                while (iterator.hasNext()) {
                    char c = iterator.next();
                    if (c != lastChunk) {
                        iterator.remove();
                        break;
                    }
                }
                set.add(curr);
            }

            if (curr != lastChunk) {
                lastChunk = curr;
                lastChunkCnt = 1;
            } else {
                lastChunkCnt++;
            }
        }

        if (maxCnt < cnt) {
            maxCnt = cnt;
        }

        System.out.println(maxCnt);
    }
}
