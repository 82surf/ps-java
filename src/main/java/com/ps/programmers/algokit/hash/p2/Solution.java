package com.ps.programmers.algokit.hash.p2;

import java.util.HashMap;
import java.util.ArrayList;

class Solution {
    public String solution(String[] participant, String[] completion) {
        HashMap<String, Integer> pMap = new HashMap<>();

        for (String p : participant) {
            if (pMap.containsKey(p)) {
                pMap.put(p, pMap.get(p) + 1);
            } else {
                pMap.put(p, 1);
            }
        }

        for (String c : completion) {
            int cnt = pMap.get(c);
            if (cnt == 1) {
                pMap.remove(c);
            } else {
                pMap.put(c, cnt - 1);
            }
        }

        ArrayList<String> pList = new ArrayList<>(pMap.keySet());
        return pList.get(0);
    }
}