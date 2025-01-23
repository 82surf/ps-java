package com.ps.boj.p1043;

import java.io.*;
import java.util.*;
import com.ps.util.InputManager;

public class Main {
    private static int[] parent;

    private static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) {
            parent[Math.max(x, y)] = Math.min(x, y);
        }
    }

    private static int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public static void main(String[] args) throws IOException {
        InputManager.setInput("p1043");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        parent = new int[N + 1];
        for (int i = 0; i <= N; i++) parent[i] = i;

        int[] knowingPeople = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int peopleN = knowingPeople[0];
        if (peopleN == 0) {
            System.out.println(M);
            return;
        }
        for (int i = 1; i < peopleN; i++) {
            union(knowingPeople[i], knowingPeople[i + 1]);
        }

        List<List<Integer>> partyList = new ArrayList<>();
        while (M-- > 0) {
            int[] partyPeople = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            List<Integer> party = new ArrayList<>();
            for (int i = 1; i < partyPeople.length; i++) {
                party.add(partyPeople[i]);
                if (i < partyPeople.length - 1) {
                    union(partyPeople[i], partyPeople[i + 1]);
                }
            }
            partyList.add(party);
        }

        int answer = 0;
        for (List<Integer> party : partyList) {
            boolean isTrueParty = false;
            for (int person : party) {
                int knowingRoot = find(knowingPeople[1]);
                int personRoot = find(person);
                if (knowingRoot == personRoot) {
                    isTrueParty = true;
                    break;
                }
            }
            if (!isTrueParty) {
                answer++;
            }
        }

        System.out.println(answer);
    }
}
