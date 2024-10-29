package com.ps.programmers.algokit.dfsbfs.p6;

import java.util.*;
import java.util.stream.*;

class Solution {
    List<List<String>> courseList = new ArrayList<>();

    public String[] solution(String[][] tickets) {
        boolean[] used = new boolean[tickets.length];
        for (int i = 0; i < tickets.length; i++) {
            used[i] = false;
        }
        List<String> course = new ArrayList<>();
        dfs("ICN", tickets, used, course);

        return getAnswer(courseList);
    }

    private void dfs(String currArrv, String[][] tickets, boolean[] used, List<String> course) {
        course.add(currArrv);

        if (usedAllTickets(used)) {
            courseList.add(new ArrayList<>(course));
            return;
        }

        for (int i = 0; i < tickets.length; i++) {
            String nextDept = tickets[i][0];
            String nextArrv = tickets[i][1];
            if (currArrv.equals(nextDept) && !used[i]) {
                used[i] = true;
                dfs(nextArrv, tickets, used, course);
                used[i] = false;
                course.remove(course.size() - 1);
            }
        }
    }

    private boolean usedAllTickets(boolean[] used) {
        for (boolean u : used) {
            if (!u) {
                return false;
            }
        }
        return true;
    }

    private String[] getAnswer(List<List<String>> courseList) {
        List<String> answerCourse = courseList
                .stream()
                .sorted(Comparator.comparing(list -> String.join(" ", list)))
                .findFirst()
                .orElse(Collections.emptyList());

        return answerCourse.toArray(new String[]{});
    }
}