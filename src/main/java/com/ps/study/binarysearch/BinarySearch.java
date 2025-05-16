package com.ps.study.binarysearch;

public class BinarySearch {
    
    public static void main(String[] args) {
        // 예시용 배열(미리 정렬)
        int[] arr = {1, 3, 5, 5, 5, 7, 9};

        // 5 이상인 수가 처음 나오는 인덱스
        int loIdx = lowerBound(arr, 5);
        System.out.println("loIdx: " + loIdx + " val: " + arr[loIdx]);

        // 5 초과인 수가 처음 나오는 인덱스
        int upIdx = upperBound(arr, 5);
        System.out.println("upIdx: " + upIdx + " val: " + arr[upIdx]);
    }

    /**
     * @param arr
     * @param target
     * @return target 이상의 수가 처음 나오는 인덱스
     */
    static int lowerBound(int[] arr, int target) {
        // 구간은 [left, right)로 설정한다.
        int left = 0;
        int right = arr.length;

        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        // while문이 종료되면 left와 right는 동일해지므로 무엇을 반환해도 상관 없다.
        return left;
    }

    /**
     * @param arr
     * @param target
     * @return target을 초과하는 수가 처음 나오는 인덱스
     */
    static int upperBound(int[] arr, int target) {
        // 구간은 [left, right)로 설정한다.
        int left = 0;
        int right = arr.length;

        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        // while문이 종료되면 left와 right는 동일해지므로 무엇을 반환해도 상관 없다.
        return right;
    }
}
