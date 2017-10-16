package org.orh.sorting.basic;

import java.util.Arrays;

import org.orh.sorting.SortTestHelper;
import org.orh.sorting.basic.ext.SelectionSortObject;

/**
 * 插入排序: 插扑克牌，从第二张排开始往前依次比较，如果小则交换，否则可以从下一张牌继续比较
 *
 */
public class InsertionSort {

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static void sort(Comparable[] arr) {
        for (int i = 1, len = arr.length; i < len; i++) {

            for (int j = i; j > 0; j--) { // 往前比较
                if (arr[j].compareTo(arr[j - 1]) < 0) { // 挨个往前比，比前者小则交换
                    swap(arr, j, j - 1);
                } else { break;} // 切记不要忘记提前退出本轮循环，不然会丧失插入排序的优势
            }
        }
    }

    @SuppressWarnings("rawtypes")
    public static void swap(Comparable[] arr, int i, int j) {
        Comparable tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) throws InterruptedException {
        // 使用同样的数据集合 来对比 插入排序、选中排序
        Integer[] inArr = SortTestHelper.generateRandomArray(10000, 0, 100);
        Integer[] seArr = Arrays.copyOf(inArr, inArr.length);

        // ☆☆☆ 百思不得其解 ：（JDK版本：jdk1.8.0_141）
        // 1. 如果按以下代码47,48行， 先执行"插入排序"，再执行 "选择排序" ，则 “选择排序”的速度会慢出许多
        // 2. 如果调换下47,48 行，则"选择排序"的速度会比原来 快上3-4倍
        
        // 已经排除以下可能，做了以下尝试，但结果还是一样：
        // 1. 不采用 Array.copyOf，两个方法都采用 全新生成的 数组
        // 2. 不使用System.out.print ，使用Logger打印
        // 3. 不使用jdk8 的 lambda Consumer方法，使用直接调用静态方法
        
        SortTestHelper.testSort("InsectionSort", InsertionSort::sort, inArr);
        SortTestHelper.testSort("SelectionSort", SelectionSortObject::sort, seArr);
    }
}
