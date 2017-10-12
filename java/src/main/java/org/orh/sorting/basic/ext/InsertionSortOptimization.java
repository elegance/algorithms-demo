package org.orh.sorting.basic.ext;

import java.util.Arrays;

import org.orh.sorting.SortTestHelper;
import org.orh.sorting.basic.InsertionSort;

/**
 * 插入排序优化 - 普通的插入排序会发生多次的对比交换，而一次 swap 则对应三次赋值，所以这里的优化方案是想办法 把 交换的次数减少
 *
 */
public class InsertionSortOptimization {

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static void sort(Comparable[] arr) {
        for (int i = 1; i < arr.length; i++) {

            Comparable c = arr[i]; // 得到位置元素的副本 - 每轮考察的元素，一轮只考察一个
            int j = i;

            for (; j > 0 && c.compareTo(arr[j - 1]) < 0; j--) { // 每轮都是与 考察元素做对比
                arr[j] = arr[j - 1]; // 元素往后挪一位 - 即一次赋值
            }
            arr[j] = c;
        }
    }

    public static void main(String[] args) {
        Integer[] arr = SortTestHelper.generateRandomArray(20000, 0, 3);
        Integer[] isArr = Arrays.copyOf(arr, arr.length);
        Integer[] seArr = Arrays.copyOf(arr, arr.length);

        SortTestHelper.testSort("InsertionSortOptimization", InsertionSortOptimization::sort, arr);
        SortTestHelper.testSort("InsertionSort", InsertionSort::sort, isArr);
        SortTestHelper.testSort("SelectionSort", SelectionSortObject::sort, seArr);

        Integer[] nearOrdedArr = SortTestHelper.generateNearOrderedArray(20000, 10);
        Integer[] isArr2 = Arrays.copyOf(nearOrdedArr, nearOrdedArr.length);
        Integer[] seArr2 = Arrays.copyOf(nearOrdedArr, nearOrdedArr.length);

        SortTestHelper.testSort("InsertionSortOptimization", InsertionSortOptimization::sort, nearOrdedArr);
        SortTestHelper.testSort("InsertionSort", InsertionSort::sort, isArr2);
        SortTestHelper.testSort("SelectionSort", SelectionSortObject::sort, seArr2);
    }
}
