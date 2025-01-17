package com.ps.study.sort;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(5, 2, 8, 1, 3);
        List<Integer> ascList = list.stream().sorted().collect(Collectors.toList());
        List<Integer> descList = list.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());

        int[] arr = {5, 2, 8, 1, 3};
        int[] ascArr = Arrays.stream(arr).sorted().toArray();
        int[] descArr = Arrays.stream(arr).boxed().sorted(Comparator.reverseOrder()).mapToInt(Integer::intValue).toArray();
    }
}
