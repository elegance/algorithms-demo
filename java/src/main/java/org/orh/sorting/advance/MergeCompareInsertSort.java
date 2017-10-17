package org.orh.sorting.advance;

import java.util.Arrays;

import org.orh.sorting.SortTestHelper;
import org.orh.sorting.basic.ext.InsertionSortOptimization;

public class MergeCompareInsertSort {

    public static void main(String[] args) {
        // 对 5w 接近有序的数据，分别用 插入排序、归并排序来对比

        Integer[] arr = SortTestHelper.generateNearOrderedArray(50000, 10);
        Integer[] arr2 = Arrays.copyOf(arr, arr.length);

        SortTestHelper.testSort("InsertionSortOptimization", InsertionSortOptimization::sort, arr);
        SortTestHelper.testSort("MergeSort", MergeSort::sort, arr2);
        // ===> 结论：在接近有序的数组排序场景下，插入排序的优势明显

//        // 对 5w 无序的数据，分别用 插入排序、归并排序来对比
//
        arr = SortTestHelper.generateRandomArray(1000000, 0, 100);
        arr2 = Arrays.copyOf(arr, arr.length);
//
//        SortTestHelper.testSort("InsertionSortOptimization", InsertionSortOptimization::sort, arr);
        SortTestHelper.testSort("MergeSort", MergeSort::sort, arr2);
//        // ===> 归并排序优势绝对明显
    }
}
