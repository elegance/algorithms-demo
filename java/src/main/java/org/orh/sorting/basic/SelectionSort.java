package org.orh.sorting.basic;

import java.util.Arrays;

/**
 * 选择排序：选择最小往前排
 */
public class SelectionSort {

    public static void sort(int[] arr) {
        for (int i = 0, len = arr.length; i < len; i++) {
            // 存储 剩余未排序中 最小值元素的索引，默认为剩余未排序元素中的 首个
            int minIndex = i;

            // 寻找[i, j) 中最小元素索引， 后续元素依次与 minIndex 元素比较
            for (int j = i + 1; j < len; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            // 每一次大循环最多制作一次 swap
            if (minIndex != i) {
                swap(arr, i, minIndex);
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {9, 8, 7, 6, 5, 4, 3, 2, 1};
        SelectionSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
