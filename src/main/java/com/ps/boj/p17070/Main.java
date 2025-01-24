package com.ps.boj.p17070;

import java.io.*;
import java.util.*;
import com.ps.util.InputManager;

enum Status {
    HORIZONTAL,
    VERTICAL,
    DIAGONAL;
}

class Pipe {
    int r;
    int c;
    Status status;

    Pipe(int r, int c, Status status) {
        this.r = r;
        this.c = c;
        this.status = status;
    }

    @Override
    public String toString() {
        return "r = " + r + " c = " + c + " status = " + status;
    }
}

public class Main {
    static int N;

    public static void main(String[] args) throws IOException {
        InputManager.setInput("p17070");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[][] graph = new int[N][];
        for (int i = 0; i < N; i++) {
            int[] row = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            graph[i] = row;
        }
        if (graph[N - 1][N - 1] == 1) {
            System.out.println(0);
            return;
        }
        System.out.println(bfs(graph));
    }

    static int bfs(int[][] graph) {
        Map<Status, int[]> deltaMap = new HashMap<>();
        deltaMap.put(Status.HORIZONTAL, new int[]{0, 1});
        deltaMap.put(Status.VERTICAL, new int[]{1, 0});
        deltaMap.put(Status.DIAGONAL, new int[]{1, 1});

        ArrayDeque<Pipe> q = new ArrayDeque<>();
        q.addLast(new Pipe(0, 1, Status.HORIZONTAL));

        int[][] visit = new int[N][N];
        visit[0][1] = 1;

        while (!q.isEmpty()) {
            Pipe curr = q.removeFirst();
            for (Map.Entry<Status, int[]> entry : deltaMap.entrySet()) {
                Status nextStatus = entry.getKey();
                int[] delta = entry.getValue();
                if (curr.status == Status.DIAGONAL || nextStatus == Status.DIAGONAL || curr.status == nextStatus) {
                    int nr = curr.r + delta[0];
                    int nc = curr.c + delta[1];
                    if (0 <= nr && nr < N && 0 <= nc && nc < N && graph[nr][nc] == 0) {
                        if (nextStatus == Status.HORIZONTAL || nextStatus == Status.VERTICAL || (nextStatus == Status.DIAGONAL && graph[nr - 1][nc] == 0 && graph[nr][nc - 1] == 0)) {
                            visit[nr][nc]++;
                            Pipe nextPipe = new Pipe(nr, nc, nextStatus);
                            q.addLast(nextPipe);
                        }
                    }
                }
            }
        }

        for (int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(visit[i]));
        }

        return visit[N - 1][N - 1];
    }
}
