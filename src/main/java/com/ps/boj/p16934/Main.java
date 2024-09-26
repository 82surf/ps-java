package com.ps.boj.p16934;

import java.io.*;
import java.util.*;

public class Main {
    private static final HashMap<String, Integer> nickNameCntMap = new HashMap<>();
    private static final HashMap<String, Boolean> prefixMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] nickNameArr = new String[N];
        for (int i = 0; i < N; i++) {
            nickNameArr[i] = br.readLine();
        }
        for (String nickName : nickNameArr) {
            System.out.println(createPrefix(nickName));
        }
    }

    private static String createPrefix(String nickName) {
        String finalPrefix = null;
        for (int i = 1; i < nickName.length() + 1; i++) {
            String prefix = nickName.substring(0, i);
            if (finalPrefix == null && !prefixMap.containsKey(prefix)) {
                finalPrefix = prefix;
                if (nickNameCntMap.containsKey(nickName)) {
                    nickNameCntMap.put(nickName, nickNameCntMap.get(nickName) + 1);
                } else {
                    nickNameCntMap.put(nickName, 1);
                }
            }
            prefixMap.put(prefix, true);
        }
        if (finalPrefix == null) {
            if (nickNameCntMap.containsKey(nickName)) {
                nickNameCntMap.put(nickName, nickNameCntMap.get(nickName) + 1);
                finalPrefix = nickName + nickNameCntMap.get(nickName);
            } else {
                nickNameCntMap.put(nickName, 1);
                finalPrefix = nickName;
            }
        }
        return finalPrefix;
    }
}
