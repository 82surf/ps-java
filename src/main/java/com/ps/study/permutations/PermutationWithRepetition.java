package com.ps.study.permutations;

import java.util.ArrayList;
import java.util.List;

public class PermutationWithRepetition {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        List<Integer> current = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 1; i <= nums.length; i++) {
            permuteWithRepetition(i, nums, current, result);
        }
        for (List<Integer> permutation : result) {
            System.out.println(permutation);
        }
    }

    private static void permuteWithRepetition(int n, int[] nums, List<Integer> current, List<List<Integer>> result) {
        if (current.size() == n) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int num : nums) {
            current.add(num);
            permuteWithRepetition(n, nums, current, result);
            current.remove(current.size() - 1);
        }
    }
}
