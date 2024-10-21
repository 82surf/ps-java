package com.ps.programmers.algokit.bruteforce.p3;

import java.util.*;
import java.util.stream.*;

class Solution {
    public int solution(String numbers) {
        int[] nums = getNums(numbers);
        boolean[] visited = new boolean[nums.length];
        List<Integer> current = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 1; i <= nums.length; i++) {
            permute(i, nums, visited, current, result);
        }
        List<Integer> intResult = result.stream().map(this::convertToNum).collect(Collectors.toList());
        Map<Integer, Boolean> answer = new HashMap<>();
        intResult.stream().filter(this::isPrimeNum).forEach(num -> { answer.put(num, true); });
        return answer.size();
    }

    private int[] getNums(String numbers) {
        return Arrays.stream(numbers.split("")).mapToInt(Integer::parseInt).toArray();
    }

    private void permute(int n, int[] nums, boolean[] visited, List<Integer> current, List<List<Integer>> result) {
        if (current.size() == n) {
            result.add(new ArrayList(current));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                current.add(nums[i]);
                permute(n, nums, visited, current, result);
                visited[i] = false;
                current.remove(current.size() - 1);
            }
        }
    }

    private int convertToNum(List<Integer> nums) {
        if (nums.isEmpty()) {
            return 0;
        }
        return Integer.parseInt(nums.stream().map(String::valueOf).reduce("", (a, b) -> a + b));
    }

    private boolean isPrimeNum(int num) {
        if (num < 2) {
            return false;
        }
        for (int i = 2; Math.pow(i, 2) <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}