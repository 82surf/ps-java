package com.ps.study;

import java.io.*;
import java.util.*;

public class JavaIterToolsExample {
    public static void main(String[] args) throws IOException {
        System.out.println("Select which function you want to run");
        System.out.println("1. Permutation");
        System.out.println("2. Permutation with repetition");
        System.out.println("3. Combination");
        System.out.println("4. Combination with repetition");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int input = Integer.parseInt(br.readLine());

        int[] nums = {1, 2, 3};

        switch (input) {
            case 1:
                runPermutation(nums);
                break;
            case 2:
                runPermutationWithRepetition(nums);
                break;
            case 3:
                runCombination(nums);
                break;
            case 4:
                runCombinationWithRepetition(nums);
                break;
        }
    }

    private static void runPermutation(int[] nums) {
        System.out.println("Run permutation");
        List<Integer> current = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];

        for (int i = 1; i <= nums.length; i++) {
            permute(i, nums, current, result, visited);
        }
        result.forEach(System.out::println);
    }

    private static void runPermutationWithRepetition(int[] nums) {
        System.out.println("Run permutation with repetition");
        List<Integer> current = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 1; i <= nums.length; i++) {
            permuteWithRepetition(i, nums, current, result);
        }
        result.forEach(System.out::println);
    }

    private static void runCombination(int[] nums) {
        System.out.println("Run combination");
        List<Integer> current = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 1; i <= nums.length; i++) {
            combine(i, 0, nums, current, result);
        }
        result.forEach(System.out::println);
    }

    private static void runCombinationWithRepetition(int[] nums) {
        System.out.println("Run combination with repetition");
        List<Integer> current = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 1; i <= nums.length; i++) {
            combineWithRepetition(i, 0, nums, current, result);
        }
        result.forEach(System.out::println);
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

    private static void combine(int n, int start, int[] nums, List<Integer> current, List<List<Integer>> result) {
        if (current.size() == n) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i < nums.length; i++) {
            current.add(nums[i]);
            combine(n, start + 1, nums, current, result);
            current.remove(current.size() - 1);
        }
    }

    private static void combineWithRepetition(int n, int start, int[] nums, List<Integer> current, List<List<Integer>> result) {
        if (current.size() == n) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i < nums.length; i++) {
            current.add(nums[i]);
            combineWithRepetition(n, start, nums, current, result);
            current.remove(current.size() - 1);
        }
    }
}
