package com.ps.study.stream;

import java.util.*;
import java.util.stream.*;

public class StreamExample {
    
    public static void main(String[] args) {
        int[] primitiveIntArr = {3, 2, 1, 4, 5};
        // 기본형 배열로 스트림을 생성하면 기본형 스트림이 생성된다.
        IntStream intStream = Arrays.stream(primitiveIntArr);
        // 기본형 스트림은 toArray 메서드에 인자를 따로 넣을 필요가 없다.
        int[] intStreamArr = intStream.toArray();

        Integer[] referenceIntArr = {3, 2, 1, 4, 5};
        // 참조형 배열로 스트림을 생성하면 객체 스트림이 생성된다.
        Stream<Integer> objStream = Arrays.stream(referenceIntArr);
        // 객체 스트림은 toArray 메서드에 생성자를 넣어줘야 한다.
        Integer[] objStreamArr = objStream.toArray(Integer[]::new);
    }
}
