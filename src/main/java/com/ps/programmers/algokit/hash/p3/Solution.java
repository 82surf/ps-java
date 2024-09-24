package com.ps.programmers.algokit.hash.p3;

import java.util.HashMap;

class Solution {
    public static HashMap<String, Boolean> headerMap = new HashMap<>();

    public boolean solution(String[] phone_book) {
        for (String phoneNum : phone_book) {
            headerMap.put(phoneNum, true);
        }
        for (String phoneNum : phone_book) {
            if (checkPhoneNumHasHeader(phoneNum)) {
                return false;
            }
        }
        return true;
    }

    public boolean checkPhoneNumHasHeader(String phoneNum) {
        for (int i = 1; i < phoneNum.length(); i++) {
            String header = phoneNum.substring(0, i);
            if (headerMap.containsKey(header)) {
                return true;
            }
        }
        return false;
    }
}
