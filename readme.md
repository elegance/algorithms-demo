## 算法相关
---

#### 基础排序
1. 选择排序，记忆口诀：**选出最小排首位** (首位是指剩余未排序位置中的首位，第1轮确定第1个位置，第2轮确定第2个位置...)
    * 特点：
        - 一轮大循环只发生一次交换
        - 取最小要遍历比较，故不可提前结束
    * 代码：
        - [java选择排序的实现](.\java\src\main\java\org\orh\sorting\basic\SelectionSort.java)
        - [java选择排序泛型支持](.\java\src\main\java\org\orh\sorting\basic\ext\SelectionSortObject.java)

2. 插入排序，记忆口诀：**挨个往前比较插入** (往前比较，意味着循环开始从第二个元素开始)
    * 特点：
        - 挨个比较不满足条件时，可**提前退出循环**
        - 挨个比较插入，发生多次swap (一次 swap = 3次赋值)
    * 代码：
        - [java插入排序的实现](.\java\src\main\java\org\orh\sorting\basic\InsertionSort.java)