package com.ps.study.binarysearch;

public class BinarySearch {
    
    public static void main(String[] args) {
        // 예시용 배열(미리 정렬)
        int[] arr = {1, 3, 5, 7, 9, 11, 13, 15};

        // 1) 특정 값을 찾는 이분 탐색
        int target1 = 7;
        int indexExact = binarySearchExact(arr, target1);
        System.out.println("binarySearchExact(arr, " + target1 + ") 결과: " + indexExact);
        // arr[3] = 7, 인덱스 3을 기대

        // 2) lowerBound: target 이상이 처음 등장하는 위치
        int target2 = 7;
        int indexLower = lowerBound(arr, target2);
        System.out.println("lowerBound(arr, " + target2 + ") 결과: " + indexLower);
        // arr 배열에서 7 이상의 값이 처음 등장하는 곳은 arr[3] = 7, 인덱스 3

        // 3) upperBound: target 초과가 처음 등장하는 위치
        int target3 = 7;
        int indexUpper = upperBound(arr, target3);
        System.out.println("upperBound(arr, " + target3 + ") 결과: " + indexUpper);
        // 7을 초과하는 값이 처음 등장하는 곳은 arr[4] = 9, 인덱스 4
    }

    /**
     * 1) 특정 '값'을 찾는 이분 탐색
     *    - arr[mid] == target이면 인덱스 반환
     *    - 못 찾으면 -1 반환
     *
     * @param arr 오름차순 정렬된 배열
     * @param target 찾고자 하는 값
     * @return target과 동일한 값의 인덱스, 없으면 -1
     */
    public static int binarySearchExact(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        // 못 찾은 경우
        return -1;
    }

    /**
     * 2) lowerBound
     *    - "arr[mid] >= target"을 처음 만족하는 인덱스를 찾음
     *    - 즉 target 이상의 값 중 가장 왼쪽에 있는 인덱스
     *    - 만약 arr 내에 target 이상인 값이 없다면 arr.length 반환
     *
     * @param arr 오름차순 정렬된 배열
     * @param target 기준값
     * @return arr[i] >= target을 만족하는 최소 인덱스, 없으면 arr.length
     */
    public static int lowerBound(int[] arr, int target) {
        int left = 0;
        int right = arr.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;    // left == mid
    }

    /**
     * 3) upperBound
     *    - "arr[mid] > target"을 처음 만족하는 인덱스를 찾음
     *    - 즉 target을 초과하는 값 중 가장 왼쪽에 있는 인덱스
     *    - 만약 arr 내에 target을 초과하는 값이 없다면 arr.length 반환
     *
     * @param arr 오름차순 정렬된 배열
     * @param target 기준값
     * @return arr[i] > target을 만족하는 최소 인덱스, 없으면 arr.length
     */
    public static int upperBound(int[] arr, int target) {
        int left = 0;
        int right = arr.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

}
