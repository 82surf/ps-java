package com.ps.study.permutations;

import java.util.*;

public class Example1 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        List<Integer> current = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];

        for (int i = 1; i <= nums.length; i++) {
            permute(i, nums, current, result, visited);
        }
        for (List<Integer> permutation : result) {
            System.out.println(permutation);
        }
    }

    private static void permute(int n, int[] nums, List<Integer> current, List<List<Integer>> result, boolean[] visited) {
        if (current.size() == n) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                current.add(nums[i]);
                permute(n, nums, current, result, visited);
                visited[i] = false;
                current.remove(current.size() - 1);
            }
        }
    }
}
