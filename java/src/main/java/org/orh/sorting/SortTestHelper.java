package org.orh.sorting;

/**
 * 排序测试帮助类
 *
 */
public class SortTestHelper {

    /**
     * 随机生成指定长度的整数数组  [rangeL, rangeR]
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
}
