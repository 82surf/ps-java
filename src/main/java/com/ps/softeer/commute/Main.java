package com.ps.softeer.commute;

import java.io.*;
import java.util.*;

// [HSAT 6회 정기 코딩 인증평가 기출] 출퇴근길
// https://softeer.ai/practice/6248
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        Map<Integer, List<Integer>> edges = new HashMap<>();
        Map<Integer, List<Integer>> reversedEdges = new HashMap<>();
        while (m-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            addItemToMap(x, y, edges);
            addItemToMap(y, x, reversedEdges);
        }
        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());

        Set<Integer> nodes1 = dfs(n, s, t, edges, false);
        Set<Integer> nodes2 = dfs(n, s, -1, reversedEdges, true);
        Set<Integer> nodes3 = dfs(n, t, s, edges, false);
        Set<Integer> nodes4 = dfs(n, t, -1, reversedEdges, true);

        nodes1.retainAll(nodes2);
        nodes1.retainAll(nodes3);
        nodes1.retainAll(nodes4);
        System.out.println(nodes1.size());
    }

    public static void addItemToMap(int x, int y, Map<Integer, List<Integer>> map) {
        if (map.containsKey(x)) {
            map.get(x).add(y);
        } else {
            List<Integer> list = new ArrayList<>();
            list.add(y);
            map.put(x, list);
        }
    }

    public static Set<Integer> dfs(int n, int start, int end, Map<Integer, List<Integer>> edges, boolean reversed) {
        Set<Integer> nodes = new HashSet<>();
        boolean[] visited = new boolean[n + 1];
        if (!reversed) {
            visited[start] = true;
            visited[end] = true;
        }
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.addLast(start);
        while (!stack.isEmpty()) {
            int curr = stack.removeLast();
            if (curr == end) {
                continue;
            }
            if (edges.containsKey(curr)) {
                for (int next : edges.get(curr)) {
                    if (!visited[next]) {
                        visited[next] = true;
                        nodes.add(next);
                        stack.addLast(next);
                    }
                }
            }
        }
        return nodes;
    }
}
