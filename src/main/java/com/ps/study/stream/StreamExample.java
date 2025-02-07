package com.ps.study.stream;

import java.util.*;
import java.util.stream.*;

class Item {
    int n;

    Item(int n) {
        this.n = n;
    }

    public int getN() {
        return this.n;
    }
}

public class StreamExample {
    
    public static void main(String[] args) {
        int[] primitiveIntArr = {3, 2, 1, 4, 5, 5};
        // 기본형 배열로 스트림을 생성하면 기본형 스트림이 생성된다.
        IntStream intStream = Arrays.stream(primitiveIntArr);
        // 기본형 스트림은 toArray 메서드에 인자를 따로 넣을 필요가 없다.
        int[] intStreamArr = intStream.toArray();

        Integer[] referenceIntArr = {3, 2, 1, 4, 5};
        // 참조형 배열로 스트림을 생성하면 객체 스트림이 생성된다.
        Stream<Integer> objStream = Arrays.stream(referenceIntArr);
        // 객체 스트림은 toArray 메서드에 생성자를 넣어줘야 한다.
        Integer[] objStreamArr = objStream.toArray(Integer[]::new);

        Item[] itemArr = new Item[primitiveIntArr.length];
        List<Item> itemList = new ArrayList<>();
        for (int i = 0; i < primitiveIntArr.length; i++) {
            itemArr[i] = new Item(primitiveIntArr[i]);
            itemList.add(new Item(primitiveIntArr[i]));
        }

        // 배열 정렬
        int[] sortedArr = Arrays.stream(itemArr).mapToInt(Item::getN).sorted().toArray();
        System.out.println(Arrays.toString(sortedArr));

        // 리스트 정렬
        List<Integer> sortedList = itemList.stream().map(Item::getN).sorted().collect(Collectors.toList());
        System.out.println(sortedList);

        // 배열 최댓값
        int maxN = Arrays.stream(sortedArr).max().orElse(-1);
        // 리스트 최솟값
        int minN = sortedList.stream().mapToInt(Integer::intValue).min().orElse(Integer.MAX_VALUE);
    }
}
