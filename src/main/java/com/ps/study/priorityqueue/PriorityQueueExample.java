package com.ps.study.priorityqueue;

import java.util.*;

class Item {
    int n;

    Item(int n) {
        this.n = n;
    }

    public int getN() {
        return this.n;
    }

    @Override
    public String toString() {
        return "n = " + n;
    }
}

public class PriorityQueueExample {
    
    public static void main(String[] args) {
        PriorityQueue<Item> ascPQ = new PriorityQueue<>(Comparator.comparingInt(Item::getN));
        PriorityQueue<Item> descPQ = new PriorityQueue<>((i1, i2) -> Integer.compare(i2.n, i1.n));

        int[] arr = { 6, 5, 4, 3, 2, 1 };
        for (int n : arr) {
            ascPQ.offer(new Item(n));
            descPQ.offer(new Item(n));
        }
        System.out.println(ascPQ.poll());
        System.out.println(descPQ.poll());
    }
}
