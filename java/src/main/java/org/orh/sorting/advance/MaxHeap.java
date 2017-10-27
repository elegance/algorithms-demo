package org.orh.sorting.advance;

public class MaxHeap {

    // 一、定义堆的基本数据结构：
    // 这里使用经典的数组实现堆
    // 最大堆：一颗完全按二叉树，并且父节点比子节点大
    // 定义：int[] data, 初始 传入 capacity，data 初始长度为  new int[capacity + 1]
    // 索引：0元素不使用，从1开始
    // 左是 ：父2倍
    // 右是：父2倍+1

    protected int[] data;
    protected int capacity;
    protected int count;

    public MaxHeap(int capacity) {
        this.data = new int[capacity + 1]; // 0 元素不使用，从1开始
        this.capacity = capacity;
        this.count = 0;
    }

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    //二、 添加元素：shift up
    // 1. 加入：添加到 size 位置，size++
    // 2. 调整：与父节点比较，比父大则，交换，再递归与父比较
    public void insert(int item) {
        assert count + 1 <= capacity : "超过了最大容量"; // 可扩展data实现自动扩容的功能
        data[++count] = item;
        shiftUp(count);
    }

    private void shiftUp(int k) {
        while (k > 1 && data[k] > data[k / 2]) { // 如果字节点比父节点还要小，则违背了定义，子节点与父节点需要交换，子上升
            swap(k, k / 2);
            k = k / 2;
        }
    }

    // 三、从堆中取元素 - shift down
    // 1. 取: 元素只能取，顶端最大的元素
    // 2. 填：用最后元素节点填补顶端空白元素，count--
    // 3. 维：shift down，向左？向右？ 谁大和谁换，然后递归往下换
    public int extractMax() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("MaxHeap empty");
        }
        int v = data[1];

        data[1] = data[count];
        count--;
        shiftDown(1);

        return v;
    }

    private void shiftDown(int k) {
        while (k * 2 <= count) { // 有子节点-左子节点存在
            int j = k * 2; // 循环中 data[k] 与 data[j] 交换位置
            if (j + 1 <= count && data[j + 1] > data[j]) { // 有右子节点，并且右子节点大于左子节点
                j = j + 1; // 表示将与右子节点交换
            }
            if (data[k] >= data[j]) {
                break;
            }
            swap(k, j);
            k = j;
        }
    }

    // 四：提供 Heapify 将数组直接转换为数组-主要供堆排序优化
    // 1. 数组直接按索引顺序排列成 完全二叉树   (每个叶子节点可以看做成满足条件的二叉堆)
    // (规律：第一个非叶子节点的索引: n / 2)
    // 2. 递归(索引递减即可)将非叶子节点 转换成二叉堆，即找到那个元素 ，执行我们写的 shift down 操作即可
    public MaxHeap(int[] arr) {
        this.data = new int[arr.length + 1];
        this.capacity = arr.length;
        this.count = arr.length;

        System.arraycopy(arr, 0, data, 1, arr.length); // 空出 第1个位置的索引

        for (int i = count / 2; i >= 1; i--) { // count /2 得到 最后面的那个不是叶子节点的元素，即它不是二叉堆
            shiftDown(i);
        }
    }

    public static MaxHeap heapify(int[] arr) {
        return new MaxHeap(arr);
    }

    private void swap(int k, int i) {
        int tmp = data[k];
        data[k] = data[i];
        data[i] = tmp;
    }


    public static void main(String[] args) {
        MaxHeap heap = new MaxHeap(100);

        for (int i = 0; i < 100; i++) {
            int v = (int) (Math.random() * 100 + 1); // 生成 [1-100] 随机数
            heap.insert(v);
            System.out.printf("%d\t", v);
        }
        System.out.println();

        for (int i = 0; i < 100; i++) {
            System.out.printf("%d\t", heap.extractMax());
        }
    }
}
