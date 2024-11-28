package com.ps.boj.p16928;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        String path = System.getProperty("user.dir") + "/src/main/java/com/ps/boj/p16928/input.txt";
        System.setIn(Files.newInputStream(Paths.get(path)));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        HashMap<Integer, Integer> ladders = new HashMap<>();
        HashMap<Integer, Integer> snakes = new HashMap<>();
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            ladders.put(from, to);
        }
        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            snakes.put(from, to);
        }

        System.out.println(bfs(ladders, snakes));
    }

    public static int bfs(HashMap<Integer, Integer> ladders, HashMap<Integer, Integer> snakes) {
        boolean[] visited = new boolean[101];
        visited[1] = true;
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.addLast(new int[]{1, 0});
        int min = 101;
        while (!q.isEmpty()) {
            int[] item = q.removeFirst();
            int curr = item[0];
            int cnt = item[1];
            if (curr >= 94 && min > cnt + 1) {
                min = cnt + 1;
            }
            for (int i = 6; i > 0; i--) {
                int next = curr + i;
                if (next <= 100) {
                    if (ladders.containsKey(next)) {
                        next = ladders.get(next);
                    } else if (snakes.containsKey(next)) {
                        next = snakes.get(next);
                    }
                    if (!visited[next]) {
                        visited[next] = true;
                        q.addLast(new int[]{next, cnt + 1});
                    }
                }
            }
        }
        return min;
    }
}
