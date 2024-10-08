package com.ps.study.combination;

import java.util.ArrayList;
import java.util.List;

public class CombinationWithRepetition {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6};
        List<Integer> current = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 1; i <= nums.length; i++) {
            combineWithRepetition(i, 0, nums, current, result);
        }
        result.forEach(System.out::println);
    }

    private static void combineWithRepetition(int n, int start, int[] nums, List<Integer> current, List<List<Integer>> result) {
        if (current.size() == n) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i < nums.length; i++) {
            current.add(nums[i]);
            combineWithRepetition(n, i, nums, current, result);
            current.remove(current.size() - 1);
        }
    }
}
