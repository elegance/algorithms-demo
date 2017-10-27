package org.orh.search;

public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 8, 9, 12, 14, 18, 20, 27, 30, 25};
        System.out.printf("14 index is %d\n", search(arr, 14));
        System.out.printf("15 index is %d\n", search(arr, 15));
        System.out.printf("20 index is %d\n", search(arr, 20));

        System.out.println("============");

        System.out.printf("14 index is %d\n", binarySearch(arr, 14));
        System.out.printf("15 index is %d\n", binarySearch(arr, 15));
        System.out.printf("20 index is %d\n", binarySearch(arr, 20));
    }

    // 二分查找法，在有序数组 arr中，查找 target
    // 如果找到 则返回对应 的index
    // 如果没有找到，则返回 -1
    public static int search(int[] arr, int target) {
        // 定义区间 l, r
        // 在 arr[l...r] 中查找 target
        int l = 0, r = arr.length - 1;

        while (l <= r) {
            // 1. 取mid
//            int mid = (l + r) / 2; // (l + r ) may be over flow
            int mid = (r - l) / 2 + l;// r minus offset, then divided 2, then plus offset

            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] > target) {
                // in left area
                r = mid - 1;
            } else {
                // in right area
                l = mid + 1;
            }
        }
        return -1;
    }

    // 递归版本实现的：二分搜索法
    public static int binarySearch(int[] arr, int target) {
        return binarySearch(arr, 0, arr.length - 1, target);
    }

    public static int binarySearch(int[] arr, int l, int r, int target) {
        if (l > r) {
            return -1;
        }
        int mid = (r - l) / 2 + l;

        if (arr[mid] == target) {
            return mid;
        } else if (arr[mid] > target) {
            // in left area
            return binarySearch(arr, l, mid - 1, target);
        } else {
            // in right area
            return binarySearch(arr, mid + 1, r, target);
        }
    }
}
