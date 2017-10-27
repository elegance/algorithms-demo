package org.orh.sorting.advance;

import org.orh.sorting.SortTestHelper;

import java.util.Arrays;

public class HeapSort {

    public static void main(String[] args) {
        int[] arr = {5, 1, 4, 2, 3};
        int[] arr2 = Arrays.copyOf(arr, arr.length);
        int[] arr3 = Arrays.copyOf(arr, arr.length);
        sort(arr);
        sort2(arr2);
        sort3(arr3);

        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(arr2));
        System.out.println(Arrays.toString(arr3));
    }

    public static void sort(int[] arr) {
        MaxHeap heap = new MaxHeap(arr.length);

        for (int i = 0; i < arr.length; i++) {
            heap.insert(arr[i]);
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            arr[i] = heap.extractMax();
        }
    }

    public static void sort2(int[] arr) {
        MaxHeap heap = MaxHeap.heapify(arr);

        for (int i = arr.length - 1; i >= 0; i--) {
            arr[i] = heap.extractMax();
        }
    }

    public static void sort3(int[] arr) {
        int n = arr.length;

        // 全量-heapify
        for (int i = (arr.length - 1) / 2; i >= 0; i--) { // 遍历完后，数组成为了一个 MaxHeap 的数据
            shiftDown(arr, n, i);
        }

        // 取值，然后增量限定范围 heapify
        for (int i = arr.length - 1; i > 0; i--) { // 堆里只剩一个元素时就不用操作了
            swap(arr, i, 0); // 最大值 放到未排序的最后，这时候破坏了 MaxHeap ,继续使用  shiftDown
            shiftDown(arr, i, 0); // 只是 0 位置的元素改变
        }
    }

    // arr, k 开始 shiftDown,并且子节点的索引取值范围在 n 以内（排除已经排好位置的元素）
    private static void shiftDown(int[] arr, int n, int k) {
        while (2 * k + 1 < n) {
            int j = 2 * k + 1; // left : 2 * k + 1
            if (j + 1 < n && arr[j + 1] > arr[j]) { // exists right and right element greater than left element
                j = j + 1;
            }
            if (arr[k] > arr[j]) {
                break;
            }
            swap(arr, k, j);
            k = j;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
