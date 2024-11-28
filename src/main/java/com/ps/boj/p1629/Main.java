package com.ps.boj.p1629;

import java.io.*;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        BigInteger A = new BigInteger(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        BigInteger C = new BigInteger(st.nextToken());

        System.out.println(modularExponentiation(A, B, C));
    }

    // 모듈러 거듭제곱 계산
    private static BigInteger modularExponentiation(BigInteger base, int exponent, BigInteger mod) {
        BigInteger result = BigInteger.ONE; // 초기값: 1
        base = base.mod(mod); // 밑을 mod로 나눔

        while (exponent > 0) {
            // 지수가 홀수인 경우
            if (exponent % 2 == 1) {
                result = result.multiply(base).mod(mod);
            }
            // 밑을 제곱
            base = base.multiply(base).mod(mod);
            // 지수를 절반으로 줄임
            exponent /= 2;
        }

        return result;
    }
}