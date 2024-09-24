package com.ps.programmers.algokit.hash.p5;

import java.util.*;

class Solution {
    // 장르별 재생 횟수를 저장하는 HashMap
    // 장르별 고유 번호 List를 저장하는 HashMap
    // 장르별 재생 횟수 List를 저장하는 HashMap
    private static final HashMap<String, Integer> totalPlayMap = new HashMap<>();
    private static final HashMap<String, List<Integer>> idListMap = new HashMap<>();
    private static final HashMap<String, List<Integer>> playCntListMap = new HashMap<>();

    public int[] solution(String[] genres, int[] plays) {
        for (int i = 0; i < genres.length; i++) {
            String genre = genres[i];
            int playCnt = plays[i];
            updateTotalPlayMap(genre, playCnt);
            updateList(i, genre, playCnt);
        }

        List<Integer> answer = new ArrayList<>();
        List<Map.Entry<String, Integer>> sortedTotalPlayEntryList = new ArrayList<>(totalPlayMap.entrySet());
        sortedTotalPlayEntryList.sort(Map.Entry.<String, Integer>comparingByValue().reversed());
        for (Map.Entry<String, Integer> entry : sortedTotalPlayEntryList) {
            String genre = entry.getKey();
            List<Integer> idList = idListMap.get(genre);
            for (int i = 0; i < idList.size(); i++) {
                if (i > 1) {
                    break;
                }
                answer.add(idList.get(i));
            }
        }
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    private void updateTotalPlayMap(String genre, int playCnt) {
        if (totalPlayMap.containsKey(genre)) {
            totalPlayMap.put(genre, totalPlayMap.get(genre) + playCnt);
        } else {
            totalPlayMap.put(genre, playCnt);
        }
    }

    private void updateList(int id, String genre, int playCnt) {
        // 장르가 아예 없어서 신규 등록해야 하는 경우
        if (!idListMap.containsKey(genre)) {
            ArrayList<Integer> idList = new ArrayList<>();
            idList.add(id);
            idListMap.put(genre, idList);

            ArrayList<Integer> playCntList = new ArrayList<>();
            playCntList.add(playCnt);
            playCntListMap.put(genre, playCntList);
        } else {
            // 장르가 있어서 순서대로 삽입이 필요한 경우
            List<Integer> idList = idListMap.get(genre);
            List<Integer> playCntList = playCntListMap.get(genre);
            int insertIdx = playCntList.size();
            for (int i = 0; i < playCntList.size(); i++) {
                int cnt = playCntList.get(i);
                if (cnt < playCnt) {
                    insertIdx = i;
                    break;
                } else if (cnt == playCnt && idList.get(i) > id) {
                    insertIdx = i;
                    break;
                }
            }
            idList.add(insertIdx, id);
            playCntList.add(insertIdx, playCnt);
        }
    }
}