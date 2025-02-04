package com.ps.study.binarysearch;

public class BinarySearch {
    
    public static void main(String[] args) {
        // 예시용 배열(미리 정렬)
        int[] arr = {1, 3, 5, 5, 5, 7, 9, 9, 9, 11, 13, 15};

        // lowerBound
        int lo = lowerBound(arr, 5);
        System.out.println("lo: " + lo + " val: " + arr[lo]);

        // upperBound
        int up = upperBound(arr, 5);
        System.out.println("up: " + up + " val: " + arr[up]);
    }

    static int lowerBound(int[] arr, int target) {
        int left = 0;
        int right = arr.length;

        while (left + 1 < right) {
            int mid = (left + right) / 2;
            if (arr[mid] < target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return left;
    }

    static int upperBound(int[] arr, int target) {
        int left = 0;
        int right = arr.length;

        while (left + 1 < right) {
            int mid = (left + right) / 2;
            if (arr[mid] <= target) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return right;
    }
}
