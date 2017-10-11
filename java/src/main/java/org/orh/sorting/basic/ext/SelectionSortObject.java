package org.orh.sorting.basic.ext;

import java.util.Arrays;

/**
 * 选择排序，泛型(模板)支持 (之前是指定了 int 数组，现在可以支持其他对象)
 */
public class SelectionSortObject {

    @SuppressWarnings({"rawtypes", "unchecked"})
    public static void sort(Comparable[] arr) {
        for (int i = 0, len = arr.length; i < len; i++) {
            int minEleIndex = i; // 默认当前元素为最小元素的索引

            // 寻找最小元素索引
            for (int j = i + 1; j < len; j++) {
                if (arr[j].compareTo(arr[minEleIndex]) < 0) {
                    minEleIndex = j;
                }
            }
            if (minEleIndex != i) {
                swap(arr, i, minEleIndex);
            }
        }

    }

    public static void swap(Comparable<?>[] arr, int i, int j) {
        Comparable<?> tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        // Integer 比较
        Integer[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        SelectionSortObject.sort(arr);
        System.out.println(Arrays.toString(arr));

        // Double 类型比较
        Double[] doubleArr = {9.1, 8.2, 7.3, 6.5};
        SelectionSortObject.sort(doubleArr);
        System.out.println(Arrays.toString(doubleArr));

        // String 类型比较 （String 内部是遍历 char[] 挨个字符进行比较，最终以字符码点进行比较）
        String[] strArr = {"D", "C", "A", "B"};
        SelectionSortObject.sort(strArr);
        System.out.println(Arrays.toString(strArr));

        // 自定义 Student 对象比较
        Student[] stuArr = {new Student("C", 80.0), new Student("D", 80.0), new Student("A", 90.0), new Student("B", 70.0)};
        SelectionSortObject.sort(stuArr);
        System.out.println(Arrays.toString(stuArr));
    }

    // 自定义对象，用于测试比较
    static class Student implements Comparable<Student> {
        private String userName;
        private Double score;

        public Student(String userName, Double score) {
            this.userName = userName;
            this.score = score;
        }

        @Override
        public int compareTo(Student o) {
            int scoreCompareRs = this.score.compareTo(o.score); // 成绩比较结果
            return scoreCompareRs != 0 ? scoreCompareRs : this.userName.compareTo(o.userName); // 成绩相等时
                                                                                               // 再以名称
                                                                                               // 比较
        }

        @Override
        public String toString() {
            return "Student@userName=" + userName + ",score=" + score;
        }

    }
}
