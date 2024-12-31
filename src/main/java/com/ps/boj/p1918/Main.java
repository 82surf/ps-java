package com.ps.boj.p1918;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        String path = System.getProperty("user.dir") + "/src/main/java/com/ps/boj/p1918/input.txt";
        System.setIn(Files.newInputStream(Paths.get(path)));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '(') {
                stack.addLast(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peekLast() != '(') {
                    System.out.print(stack.removeLast());
                }
                stack.removeLast();
            } else if (c == '*' || c == '/') {
                while (!stack.isEmpty() && (stack.peekLast() == '*' || stack.peekLast() == '/')) {
                    System.out.print(stack.removeLast());
                }
                stack.addLast(c);
            } else if (c == '+' || c == '-') {
                while (!stack.isEmpty() && stack.peekLast() != '(') {
                    System.out.print(stack.removeLast());
                }
                stack.addLast(c);
            } else {
                System.out.print(c);
            }
        }
        while (!stack.isEmpty()) {
            System.out.print(stack.removeLast());
        }
    }
}