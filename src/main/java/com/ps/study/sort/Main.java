package com.ps.study.sort;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(5, 2, 8, 1, 3);
        List<Integer> ascList = list.stream().sorted().collect(Collectors.toList());
        List<Integer> descList = list.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());

        List<Item> itemList = List.of(
                new Item(1, 2, 3),
                new Item(1, 1, 1),
                new Item(2, 3, 4),
                new Item(2, 3, 1),
                new Item(3, 5, 4),
                new Item(3, 5, 4)
        );
        List<Item> multiConditionList = itemList.stream()
                .sorted(Comparator.comparing(Item::getN1)
                        .thenComparing(Item::getN2)
                        .thenComparing(Item::getN3))
                .collect(Collectors.toList());

        multiConditionList.forEach(System.out::println);

        int[] arr = {5, 2, 8, 1, 3};
        int[] ascArr = Arrays.stream(arr).sorted().toArray();
        int[] descArr = Arrays.stream(arr).boxed().sorted(Comparator.reverseOrder()).mapToInt(Integer::intValue).toArray();
    }
}

class Item {
    int n1;
    int n2;
    int n3;

    public Item(int n1, int n2, int n3) {
        this.n1 = n1;
        this.n2 = n2;
        this.n3 = n3;
    }

    public int getN1() {
        return n1;
    }

    public int getN2() {
        return n2;
    }

    public int getN3() {
        return n3;
    }

    @Override
    public String toString() {
        return "Item{" +
                "n1=" + n1 +
                ", n2=" + n2 +
                ", n3=" + n3 +
                '}';
    }
}
