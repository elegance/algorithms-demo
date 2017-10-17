package org.orh.sorting.advance;

import java.util.Arrays;

import org.orh.sorting.SortTestHelper;
import org.orh.sorting.basic.ext.InsertionSortOptimization;

public class MergeSort {

    public static void sort(Comparable[] arr) {
        reduce(arr, 0, arr.length - 1);
    }

    public static void reduce(Comparable[] arr, int l, int r) {
        // if (l >= r) {
        // return;
        // }
        if (r - l <= 15) { // 优化2： 小数组使用插入排序； 因为：优化 插入排序 O(n) ,当 n 小于一定值时 其性能优于 nlogN
            InsertionSortOptimization.sort(arr, l, r);
            return;
        }

        int mid = (l + r) / 2;
        // System.out.println("mid:" + mid);

        reduce(arr, l, mid);
        reduce(arr, mid + 1, r);

        if (arr[mid].compareTo(arr[mid + 1]) > 0) { // 优化1： 如果后面第一个元素 比 前面 最后一个元素小时才有必要 merge
            merge(arr, l, mid, r);
        }
    }

    public static void merge(Comparable[] arr, int l, int mid, int r) {
        // 辅助空间
        Comparable[] aux = Arrays.copyOfRange(arr, l, r + 1);// new Comparable[r - l + 1];

        // System.out.println("aux:" + Arrays.toString(aux));

        int i = 0, j = 0, k = mid - l + 1;
        do {
            // System.out.printf("i: %d, j: %d, k:%d , l: %d, mid: %d, r:%d\n", i, j, k, l, mid, r);
            if (j > mid - l) { // 左边索引超过左边长度，取右边
                arr[i + l] = aux[k];
                // System.out.println("debug 1");
                k++;
            } else if (k >= aux.length) { // 右边索引 超过右边 长度，取左边
                arr[i + l] = aux[j];
                // System.out.println("debug 2");
                j++;
            } else if (aux[j].compareTo(aux[k]) > 0) { // 右边 数据比左边数据小，取右边
                arr[i + l] = aux[k];
                // System.out.println("debug 3");
                k++;
            } else { // 左边数据比右边 小或者相等，取左边
                arr[i + l] = aux[j];
                // System.out.println("debug 4");
                j++;
            }
            // System.out.println(Arrays.toString(arr));
            i++;
        } while (i < aux.length);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            Integer[] arr = SortTestHelper.generateRandomArray(1000000, 1, 100);
            // Integer[] arr = {5, 4, 3, 2, 1};
            SortTestHelper.testSort("Merge Sort", MergeSort::sort, arr);
            // System.out.println(Arrays.toString(arr));
        }
    }
}
