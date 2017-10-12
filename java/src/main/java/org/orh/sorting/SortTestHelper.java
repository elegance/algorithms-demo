package org.orh.sorting;

import java.time.Duration;
import java.util.function.Consumer;

import org.orh.sorting.basic.ext.SelectionSortObject;

/**
 * 排序测试帮助类
 *
 */
public class SortTestHelper {

    /**
     * 随机生成指定长度的整数数组 [rangeL, rangeR]
     * 
     * @param size 需要生成的元素个数
     * @param rangeL 区间开始值
     * @param rangeR 区间结束值
     * @return
     */
    public static Integer[] generateRandomArray(int size, int rangeL, int rangeR) {
        // 注意 assert 是需要 jvm 参数 开关-enableassertions或-ea来开启的
        // http://lavasoft.blog.51cto.com/62575/43735/
        assert rangeL <= rangeR : "区间结束值必须大于等于起始值！";

        Integer[] arr = new Integer[size];

        int choices = rangeR - rangeL + 1;

        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * choices + rangeL);
        }
        return arr;
    }

    // 可以fork/join 分段 来优化大数组的初始化

    /**
     * 测试排序
     * 
     * @param consumer 因为排序方法是无返回值的，所以此处使用 Consumer<T>，如果有返回值可以使用 Function<T, R>
     * @param arr
     */
    @SuppressWarnings("rawtypes")
    public static void testSort(String sortName, Consumer<Comparable[]> sortFn, Comparable[] arr) {
        long start = System.currentTimeMillis();
        sortFn.accept(arr);
        long end = System.currentTimeMillis();

        assert isSorted(arr);

        Duration consumptionTime = Duration.ofMillis(end - start); // 便于单位转换，虽然暂时没用到转换

        System.out.printf("%-20s: sort %d elements take %d ms\n", sortName, arr.length, consumptionTime.toMillis());
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static boolean isSorted(Comparable[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i].compareTo(arr[i - 1]) < 0) { // 后者比前者小则返回false
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        SortTestHelper.testSort("SelectionSort", SelectionSortObject::sort, SortTestHelper.generateRandomArray(1000, 0, 100));
    }
}
