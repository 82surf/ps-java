package com.ps.programmers.algokit.bruteforce.p6;

import java.util.*;

class Solution {
    public int solution(int n, int[][] wires) {
        int answer = n;
        List<List<Integer[]>> allCaseList = getAllCaseList(n, wires);
        for (List<Integer[]> caseList : allCaseList) {
            Map<Integer, List<Integer>> connectionInfoMap = getConnectionInfoMap(n, caseList);
            int cnt = getCntByBfs(n, connectionInfoMap);
            int diff = Math.abs(cnt - (n - cnt));
            if (answer > diff) {
                answer = diff;
            }
        }
        return answer;
    }

    private List<List<Integer[]>> getAllCaseList(int n, int[][] wires) {
        List<List<Integer[]>> allCaseList = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            List<Integer[]> idxList = new ArrayList<>();
            for (int j = 0; j < n - 1; j++) {
                if (i == j) {
                    continue;
                }
                Integer[] item = Arrays.stream(wires[j]).boxed().toArray(Integer[]::new);
                idxList.add(item);
            }
            allCaseList.add(idxList);
        }
        return allCaseList;
    }

    private Map<Integer, List<Integer>> getConnectionInfoMap(int n, List<Integer[]> caseList) {
        Map<Integer, List<Integer>> connectionInfoMap = new HashMap<>();
        caseList.forEach(info -> {
            putConnectionInfo(info[0], info[1], connectionInfoMap);
            putConnectionInfo(info[1], info[0], connectionInfoMap);
        });
        return connectionInfoMap;
    }

    private void putConnectionInfo(int key, int node, Map<Integer, List<Integer>> map) {
        if (map.containsKey(key)) {
            map.get(key).add(node);
        } else {
            List<Integer> nodeList = new ArrayList<>();
            nodeList.add(node);
            map.put(key, nodeList);
        }
    }

    private int getCntByBfs(int n, Map<Integer, List<Integer>> connected) {
        boolean[] visited = new boolean[n + 1];
        int start = connected.keySet().stream().findFirst().get();
        ArrayDeque<Integer> queue = new ArrayDeque<>();

        visited[start] = true;
        queue.addLast(start);
        int cnt = 1;
        while (!queue.isEmpty()) {
            int now = queue.removeFirst();
            for (int next : connected.get(now)) {
                if (!visited[next]) {
                    queue.add(next);
                    visited[next] = true;
                    cnt++;
                }
            }
        }
        return cnt;
    }
}