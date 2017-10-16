package org.orh.sorting.basic;

import org.orh.sorting.SortTestHelper;

public class BubbleSort {

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static void sort(Comparable[] arr) {
        int cycles = arr.length;

        for (int i = 0; i < arr.length; i++) {
            boolean isSwaped = false;
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    swap(arr, j, j + 1);
                    isSwaped = true;
                }
            }
             if (!isSwaped) {
             break;
             }
        }
//        System.out.println("unswape:" + cycles);
    }

    private static void swap(Object[] arr, int i, int j) {
        Object tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        Integer[] arr = SortTestHelper.generateRandomArray(10000, 0, 100);
        SortTestHelper.testSort("Bubble Sort", BubbleSort::sort, arr);
    }
}
