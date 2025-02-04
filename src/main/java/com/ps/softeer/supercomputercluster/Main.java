package com.ps.softeer.supercomputercluster;

import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static long B;
    static long[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        B = Long.parseLong(st.nextToken());
        arr = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        System.out.println(binarySearch());
    }

    // O(NlogN)
    static long binarySearch() {
        long left = 0;
        long right = Long.MAX_VALUE;
        while (left + 1 < right) {
            long mid = (left + right) / 2;
            long cost = calcCost(mid);
            if (cost <= B) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return left;
    }

    // O(N)
    static long calcCost(long target) {
        long cost = 0;
        for (long n : arr) {
            if (n < target) {
                long diff = target - n;
                long currCost = diff * diff;
                cost += currCost;
                if (cost < 0) {
                    return Long.MAX_VALUE;
                }
            }
        }
        return cost;
    }
}
