package com.ps.boj.p1043;

import java.io.*;
import java.util.*;
import com.ps.util.InputManager;

// 잘못된 풀이
// 반례

// input
// 5 4
// 1 5
// 2 1 2
// 2 2 3
// 2 3 4
// 2 4 5

// ans
// 0

// wrong output
// 2
public class Try1 {
    public static void main(String[] args) throws IOException {
        InputManager.setInput("p1043");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        boolean[] answer = new boolean[M];

        Set<Integer> knowingPeople = new HashSet<>();
        st = new StringTokenizer(br.readLine());
        int knowingPeopleCnt = Integer.parseInt(st.nextToken());
        if (knowingPeopleCnt == 0) {
            System.out.println(M);
            return;
        }

        while (knowingPeopleCnt-- > 0) {
            knowingPeople.add(Integer.parseInt(st.nextToken()));
        }

        List<List<Integer>> partyToPeopleList = new ArrayList<>();
        Map<Integer, List<Integer>> personToPartyMap = new HashMap<>();
        for (int i = 1; i <= N; i++) personToPartyMap.put(i, new ArrayList<>());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int partyPeopleCnt = Integer.parseInt(st.nextToken());
            List<Integer> partyPeople = new ArrayList<>();
            boolean tellTruth = false;
            while (partyPeopleCnt-- > 0) {
                int person = Integer.parseInt(st.nextToken());
                if (knowingPeople.contains(person)) {
                    tellTruth = true;
                }
                personToPartyMap.get(person).add(i);
                partyPeople.add(person);
            }
            if (tellTruth) {
                answer[i] = true;
                for (int person : partyPeople) {
                    knowingPeople.add(person);
                }
            }
            partyToPeopleList.add(partyPeople);
        }

        for (int person : knowingPeople) {
            for (int partyIdx : personToPartyMap.get(person)) {
                answer[partyIdx] = true;
            }
        }

        int answerCnt = 0;
        for (boolean toldTruth : answer) {
            if (!toldTruth) {
                answerCnt++;
            }
        }
        System.out.println(answerCnt);
    }
}
