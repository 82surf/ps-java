package com.ps.study.copy;

import java.util.*;
import java.util.stream.Collectors;

public class Copy {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};
        int[] shalloCopyArr = arr.clone();

        int[][] arr2 = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        };
        int[][] deepCopyArr = Arrays.stream(arr2).map(r -> r.clone()).toArray(int[][]::new);

        List<Integer> list = List.of(1, 2, 3, 4);
        List<Integer> shallowCopyList = new ArrayList<>(list);

        List<List<Integer>> list2 = new ArrayList<>();
        list2.add(new ArrayList<>(List.of(1, 2, 3)));
        list2.add(new ArrayList<>(List.of(4, 5, 6)));
        list2.add(new ArrayList<>(List.of(7, 8, 9)));

        List<List<Integer>> deepCopy = list2.stream().map(r -> new ArrayList<>(r)).collect(Collectors.toList());
    }
}
