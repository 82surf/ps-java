package com.ps.programmers.practice.p3;

import java.util.*;

// 문제명: 과제 진행하기
// 링크: https://school.programmers.co.kr/learn/courses/30/lessons/176962
class Plan {
    public String name;
    public int startTime;
    public int duration;

    Plan(String name, String startTimeString, String durationString) {
        this.name = name;
        String[] startTimeArr = startTimeString.split(":");
        this.startTime += Integer.parseInt(startTimeArr[0]) * 60 + Integer.parseInt(startTimeArr[1]);
        this.duration = Integer.parseInt(durationString);
    }

    @Override
    public String toString() {
        return "name = " + name + " startTime = " + startTime + " duration = " + duration;
    }
}

class Solution {
    public String[] solution(String[][] plans) {
        Map<Integer, Plan> planMap = new HashMap<>();
        int totalDuration = 0;
        for (String[] p : plans) {
            Plan plan = new Plan(p[0], p[1], p[2]);
            planMap.put(plan.startTime, plan);
            totalDuration += plan.duration;
        }

        ArrayDeque<Plan> taskQ = new ArrayDeque<>();
        int time = 0;
        int endTime = 23 * 60 + 59 + totalDuration;
        List<String> answer = new ArrayList<>();
        while (time <= endTime) {
            // 시작할 과제 있는지 확인
            if (planMap.containsKey(time)) {
                taskQ.addFirst(planMap.get(time));
            }
            // 현재 진행 중 작업 처리
            if (!taskQ.isEmpty()) {
                Plan plan = taskQ.getFirst();
                plan.duration--;
                if (plan.duration == 0) {
                    answer.add(plan.name);
                    taskQ.removeFirst();
                }
            }
            time++;
        }

        return answer.stream().toArray(String[]::new);
    }
}