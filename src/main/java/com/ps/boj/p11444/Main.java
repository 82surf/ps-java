package com.ps.boj.p11444;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Main {
    public static int MOD;
    public static Map<Long, Long> memo;

    public static void main(String[] args) throws IOException {
        String path = System.getProperty("user.dir") + "/src/main/java/com/ps/boj/p11444/input.txt";
        System.setIn(Files.newInputStream(Paths.get(path)));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(br.readLine());
        MOD = 1000000007;
        memo = new HashMap<>();
        memo.put(0L, 0L);
        memo.put(1L, 1L);
        memo.put(2L, 1L);
        System.out.println(fib(N));
    }

    public static long fib(long n) {
        if (memo.containsKey(n)) {
            return memo.get(n);
        } else if (n % 2 == 0) {
            long k = n / 2;
            long val = (fib(k) * (fib(k + 1) + fib(k - 1))) % MOD;
            memo.put(n, val);
            return val;
        } else {
            long a = fib((n - 1) / 2);
            long b = fib((n + 1) / 2);
            long val = (a * a + b * b) % MOD;
            memo.put(n, val);
            return val;
        }
    }
}
