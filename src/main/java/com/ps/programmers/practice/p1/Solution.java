package com.ps.programmers.practice.p1;

import java.util.*;

// 문제명: 서버 증설 횟수
// 링크: https://school.programmers.co.kr/learn/courses/30/lessons/389479?language=java
class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        int[] servers = new int[players.length];

        for (int i = 0; i < players.length; i++) {
            int player = players[i];
            int serverCapacity = (servers[i] + 1) * m - 1;
            if (player > serverCapacity) {
                int diff = player - serverCapacity;
                int serverToAdd = diff % m == 0 ? diff / m : diff / m + 1;
                answer += serverToAdd;
                for (int j = i; j < i + k; j++) {
                    if (j < servers.length) {
                        servers[j] += serverToAdd;
                    }
                }
            }
        }

        System.out.println(Arrays.toString(servers));

        return answer;
    }
}
