package com.ps.boj.p28702;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] inputArr = new String[3];
        int[] intArr = new int[3];
        for (int i = 0; i < 3; i++) {
            inputArr[i] = reader.readLine();
        }
        for (int i = 0; i < 3; i++) {
            String input = inputArr[i];
            if (input.chars().allMatch(Character::isDigit)) {
                int n = Integer.parseInt(input);
                intArr[i] = n;
                if (i + 1 < 3) {
                    intArr[i + 1] = n + 1;
                }
                if (i + 2 < 3) {
                    intArr[i + 2] = n + 2;
                }
                break;
            }
        }
        printN(intArr[2] + 1);
    }

    private static void printN(int n) {
        if (n % 15 == 0) {
            System.out.println("FizzBuzz");
        } else if (n % 5 == 0) {
            System.out.println("Buzz");
        } else if (n % 3 == 0) {
            System.out.println("Fizz");
        } else {
            System.out.println(n);
        }
    }
}
