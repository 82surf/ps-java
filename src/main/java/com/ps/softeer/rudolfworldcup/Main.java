package com.ps.softeer.rudolfworldcup;

import java.io.*;
import java.util.*;

public class Main {
    static int[] powers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        powers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        List<int[]> pairList = createPairList(powers.length);
        List<List<Character>> resultList = new ArrayList<>();
        permutationR(new char[]{'w', 'l', 'd'}, new ArrayList<>(), resultList);
        double answer = 0d;
        for (List<Character> result : resultList) {
            answer += calcTotalRate(pairList, result);
        }
        System.out.printf("%.3f", answer * 100);
    }

    static List<int[]> createPairList(int n) {
        List<int[]> pairList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                pairList.add(new int[]{i, j});
            }
        }
        return pairList;
    }

    static void permutationR(char[] items, List<Character> result, List<List<Character>> resultList) {
        if (result.size() == 6) {
            resultList.add(new ArrayList(result));
            return;
        }

        for (char item : items) {
            result.add(item);
            permutationR(items, result, resultList);
            result.remove(result.size() - 1);
        }
    }

    static double calcTotalRate(List<int[]> pairList, List<Character> result) {
        double totalRate = 1d;
        int[] scores = new int[]{0, 0, 0, 0};
        for (int i = 0; i < 6; i++) {
            int[] pair = pairList.get(i);
            char r = result.get(i);
            recordScore(pair, r, scores);
            totalRate *= calcRate(pair, r);
        }
        return isValid(scores) ? totalRate : 0d;
    }

    static double calcRate(int[] pair, char result) {
        double a = powers[pair[0]];
        double b = powers[pair[1]];
        if (result == 'w') {
            return (4 * a) / (5 * a + 5 * b);
        } else if (result == 'l') {
            return (4 * b) / (5 * a + 5 * b);
        } else {
            return (a + b) / (5 * a + 5 * b);
        }
    }

    static void recordScore(int[] pair, char result, int[] scores) {
        int a = pair[0];
        int b = pair[1];
        if (result == 'w') {
            scores[a] += 3;
        } else if (result == 'l') {
            scores[b] += 3;
        } else {
            scores[a] += 1;
            scores[b] += 1;
        }
    }

    static boolean isValid(int[] scores) {
        int maxVal = 0;
        int maxIdx = 0;
        for (int i = 0; i < 4; i++) {
            int score = scores[i];
            if (maxVal < score) {
                maxVal = score;
                maxIdx = i;
            }
        }
        if (maxIdx == 0) {
            return true;
        }

        int nextMaxVal = 0;
        int nextMaxIdx = 0;
        for (int i = 0; i < 4; i++) {
            if (i == maxIdx) {
                continue;
            }
            int score = scores[i];
            if (nextMaxVal < score) {
                nextMaxVal = score;
                nextMaxIdx = i;
            }
        }
        if (nextMaxIdx == 0) {
            return true;
        }
        return false;
    }
}

