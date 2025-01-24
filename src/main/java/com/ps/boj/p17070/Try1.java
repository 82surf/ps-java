// package com.ps.boj.p17070;

// import java.io.*;
// import java.util.*;
// import com.ps.util.*;

// // 시간초과
// // DFS, BFS 방식으로 구현했으나 O(2^N)으로 시간초과

// enum Status {
//     VERTICAL,
//     HORIZONTAL,
//     DIAGONAL;

//     public static Status getStatus(int ar, int ac, int br, int bc) {
//         if (ar == br) {
//             return Status.HORIZONTAL;
//         } else if (ac == bc) {
//             return Status.VERTICAL;
//         } else {
//             return Status.DIAGONAL;
//         }
//     }
// }

// class Pipe {
//     int ar;
//     int ac;
//     int br;
//     int bc;
//     Status status;

//     Pipe(int ar, int ac, int br, int bc) {
//         this.ar = ar;
//         this.ac = ac;
//         this.br = br;
//         this.bc = bc;
//         this.status = Status.getStatus(ar, ac, br, bc);
//     }

//     public static Pipe goHorizontal(Pipe pipe) {
//         if (pipe.status == Status.HORIZONTAL) {
//             return new Pipe(pipe.ar, pipe.ac + 1, pipe.br, pipe.bc + 1);
//         } else if (pipe.status == Status.DIAGONAL) {
//             return new Pipe(pipe.ar + 1, pipe.ac + 1, pipe.br, pipe.bc + 1);
//         } else {
//             return new Pipe(-1, -1, -1, -1);
//         }
//     }

//     public static Pipe goVertical(Pipe pipe) {
//         if (pipe.status == Status.VERTICAL) {
//             return new Pipe(pipe.ar + 1, pipe.ac, pipe.br + 1, pipe.bc);
//         } else if (pipe.status == Status.DIAGONAL) {
//             return new Pipe(pipe.ar + 1, pipe.ac + 1, pipe.br + 1, pipe.bc);
//         } else {
//             return new Pipe(-1, -1, -1, -1);
//         }
//     }

//     public static Pipe goDiagonal(Pipe pipe) {
//         if (pipe.status == Status.HORIZONTAL) {
//             return new Pipe(pipe.ar, pipe.ac + 1, pipe.br + 1, pipe.bc + 1);
//         } else if (pipe.status == Status.VERTICAL) {
//             return new Pipe(pipe.ar + 1, pipe.ac, pipe.br + 1, pipe.bc + 1);
//         } else {
//             return new Pipe(pipe.ar + 1, pipe.ac + 1, pipe.br + 1, pipe.bc + 1);
//         }
//     }

//     public boolean isValid(int N, int[][] walls) {
//         return isInside(ar, N) && isInside(ac, N) && isInside(br, N) && isInside(bc, N) && isNotBlocked(walls);
//     }

//     private boolean isInside(int n, int N) {
//         return 0 <= n && n < N;
//     }

//     private boolean isNotBlocked(int[][] walls) {
//         if (status != Status.DIAGONAL) {
//             return walls[br][bc] == 0;
//         }
//         return walls[br][bc] == 0 && walls[br - 1][bc] == 0 && walls[br][bc - 1] == 0;
//     }

//     public boolean checkEnd(int N) {
//         return br == N - 1 && bc == N - 1;
//     }
// }

// public class Try1 {

//     public static int N;

//     public static void main(String[] args) throws IOException {
//         InputManager.setInput("p17070");
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         N = Integer.parseInt(br.readLine());
//         int[][] walls = new int[N][N];
//         for (int i = 0; i < N; i++) {
//             int[] row = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
//             walls[i] = row;
//         }
//         int answer = walls[N - 1][N - 1] == 1 ? 0 : bfs(N, walls);
//         System.out.println(answer);
//     }

//     public static int bfs(int N, int[][] walls) {
//         ArrayDeque<Pipe> q = new ArrayDeque<>();
//         q.addLast(new Pipe(0, 0, 0, 1));
//         int cnt = 0;
//         while (!q.isEmpty()) {
//             Pipe curr = q.removeFirst();
//             if (curr.checkEnd(N)) {
//                 cnt++;
//                 continue;
//             }
//             List<Pipe> candidates = new ArrayList<>();
//             if (curr.status == Status.HORIZONTAL) {
//                 candidates.add(Pipe.goHorizontal(curr));
//                 candidates.add(Pipe.goDiagonal(curr));
//             } else if (curr.status == Status.VERTICAL) {
//                 candidates.add(Pipe.goVertical(curr));
//                 candidates.add(Pipe.goDiagonal(curr));
//             } else {
//                 candidates.add(Pipe.goVertical(curr));
//                 candidates.add(Pipe.goHorizontal(curr));
//                 candidates.add(Pipe.goDiagonal(curr));
//             }
//             for (Pipe next : candidates) {
//                 if (next.isValid(N, walls)) {
//                     q.addLast(next);
//                 }
//             }
//         }
//         return cnt;
//     }
// }
