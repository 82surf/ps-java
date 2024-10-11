package com.ps.boj.p11055;

import java.io.*;
import java.util.*;

// 풀이: combination
// 결과: Out of memory
public class Try1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        List<Integer> current = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 1; i <= nums.length; i++) {
            combine(i, 0, nums, current, result);
        }
        int answer = 0;
        for (List<Integer> combination : result) {
            if (isValid(combination)) {
                int total = combination.stream().reduce(0, Integer::sum);
                if (answer < total) {
                    answer = total;
                }
            }
        }
        System.out.println(answer);
    }

    private static void combine(int n, int start, int[] nums, List<Integer> current, List<List<Integer>> result) {
        if (current.size() == n) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i < nums.length; i++) {
            current.add(nums[i]);
            combine(n, i + 1, nums, current, result);
            current.remove(current.size() - 1);
        }
    }

    private static boolean isValid(List<Integer> combination) {
        for (int i = 1; i < combination.size(); i++) {
            if (combination.get(i - 1) > combination.get(i)) {
                return false;
            }
        }
        return true;
    }
}
