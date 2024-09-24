package com.ps.programmers.algokit.hash.p4;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

class Solution {
    private HashMap<String, List<String>> clothMap = new HashMap();

    public int solution(String[][] clothes) {
        for (String[] clothInfo : clothes) {
            String cloth = clothInfo[0];
            String category = clothInfo[1];
            if (clothMap.containsKey(category)) {
                clothMap.get(category).add(cloth);
            } else {
                List<String> clothList = new ArrayList<>();
                clothMap.put(category, clothList);
                clothList.add(cloth);
            }
        }

        if (clothMap.size() == 1) {
            List<String> keyList = new ArrayList<>(clothMap.keySet());
            String key = keyList.get(0);
            return clothMap.get(key).size();
        } else {
            int answer = 1;
            for (List<String> clothList : clothMap.values()) {
                answer *= clothList.size() + 1;
            }
            return answer;
        }
    }
}