package org.orh.search;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class BST<K extends Comparable, V> {
    public static void main(String[] args) {
        BST<Integer, String> bst = new BST<>();
        bst.insert(2, "Jim");
        bst.insert(1, "hi");
        bst.insert(3, "!");

        System.out.println("search 2：" + bst.search(2));
        System.out.println("contain 2：" + bst.contain(2));
        System.out.println("search 1:" + bst.search(1));
        System.out.println("contain 1：" + bst.contain(1));
        System.out.println("search 5:" + bst.search(5));
        System.out.println("contain 5：" + bst.contain(5));

        System.out.println("----------------------------------------- preOrder:");
        bst.preOrder();

        System.out.println("----------------------------------------- inOrder:");
        bst.inOrder();

        System.out.println("----------------------------------------- postOrder:");
        bst.postOrder();

        System.out.println("----------------------------------------- levelOrder:");
        bst.levelOrder();

        System.out.println("maximum: " + bst.maximum());
        System.out.println("minimum: " + bst.minimum());
        bst.removeMin();
        System.out.println("minimum after removeMin: " + bst.minimum());
        bst.insert(1, "Hi");
        bst.removeMax();
        System.out.println("maximum after removeMax: " + bst.maximum());
        bst.insert(3, "!");
        bst.levelOrder();
        bst.remove(2);
        System.out.println("----------------------------------------- remove 2:");
        bst.levelOrder();


    }

    private Node root;
    private int count;

    private class Node {
        private K key;
        private V value;
        private Node left;
        private Node right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public int getCount() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public void insert(K key, V value) {
        root = insert(root, key, value);
    }

    public boolean contain(K key) {
        if (isEmpty()) {
            return false;
        }
        return contain(root, key);
    }

    public V search(K key) {
        Node searchRs = search(root, key);
        return searchRs == null ? null : searchRs.value;
        // use optional manner:
        // return Optional.ofNullable(search(root, key)).map(n -> n.value).orElse(null);
    }

    public void preOrder() {
        preOrder(root);
    }

    public void inOrder() {
        inOrder(root);
    }

    public void postOrder() {
        postOrder(root);
    }

    public void levelOrder() {
        levelOrder(root);
    }

    public V maximum() {
        if (isEmpty()) {
            return null;
        }
        return maximum(root).value;
    }

    public V minimum() {
        if (isEmpty()) {
            return null;
        }
        return minimum(root).value;
    }

    public void removeMin() {
        root = removeMin(root);
    }

    public void removeMax() {
        root = removeMax(root);
    }


    public void remove(K k) {
        root = remove(root, k);
    }


    // 向指定节点为根的二叉树插入 元素
    private Node insert(Node node, K key, V value) {
        if (node == null) {
            node = new Node(key, value);
            count++;
            return node;
        }
        int compareRs = node.key.compareTo(key);

        if (compareRs == 0) {
            node.value = value;
        } else if (compareRs > 0) { // 需要往本节点为根的左子树insert
            node.left = insert(node.left, key, value);
        } else {
            node.right = insert(node.right, key, value);
        }

        return node;
    }

    // 指定节点为根的二叉树查看是否存在某元素
    private boolean contain(Node node, K key) {
        if (node == null) {
            return false;
        }

        int compareRs = node.key.compareTo(key);

        if (compareRs == 0) {
            return true;
        } else if (compareRs > 0) { // 我比你大，你去左边子树找去
            return contain(node.left, key);
        } else {
            return contain(node.right, key);
        }
    }

    // 向以指定节点为根的二叉树查找元素
    private Node search(Node node, K key) {
        if (node == null) {
            return null;
        }
        int compareRs = node.key.compareTo(key);

        if (compareRs == 0) {
            return node;
        } else if (compareRs > 0) { // 我比你大，你去左边子树找去
            return search(node.left, key);
        } else {
            return search(node.right, key);
        }
    }

    // 前序方式遍历以指定节点为根的二叉树
    private void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.println(node.key + "\t" + node.value);
        preOrder(node.left);
        preOrder(node.right);
    }

    // 中序方式遍历以指定节点为根的二叉树
    private void inOrder(Node node) {
        if (node == null) {
            return;
        }

        inOrder(node.left);
        System.out.println(node.key + "\t" + node.value);
        inOrder(node.right);
    }

    // 后序方式遍历以指定节点为根的二叉树
    private void postOrder(Node node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.key + "\t" + node.value);
    }

    // 层级遍历（广度优先）以指定节点为根的二叉树
    private void levelOrder(Node node) {
        if (node == null) {
            return;
        }
        Queue<Node> queue = new ArrayBlockingQueue(count);
        queue.add(node);

        while (!queue.isEmpty()) {
            Node tmp = queue.poll();
            System.out.println(tmp.key + "\t" + tmp.value);

            if (tmp.left != null)
                queue.add(tmp.left);
            if (tmp.right != null)
                queue.add(tmp.right);
        }
    }

    // 在以指定节点为根的二叉树中查找最大值
    private Node maximum(Node node) {
        if (node.right == null) {
            return node;
        }
        return maximum(node.right);
    }

    // 在以指定节点为根的二叉树中查找最小值
    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    // 在以指定节点为根的二叉树中删除最小节点
    private Node removeMin(Node node) {
        if (node == null) {
            return null;
        }
        if (node.left == null) {
            node = node.right;
            count--;
        } else {
            node.left = removeMin(node.left);
        }
        return node;
    }

    // 在以指定节点为根的二叉树中删除最大节点
    private Node removeMax(Node node) {
        if (node == null) {
            return null;
        }
        if (node.right == null) {
            node = node.right;
            count--;
        } else {
            node.right = removeMax(node.right);
        }
        return node;
    }

    // 在以指定节点为根的二叉树中删除节点
    private Node remove(Node node, K key) {
        if (node == null) {
//            throw new NoSuchElementException("没有key为：" + key + "元素");
            return null;
        }
        int rs = node.key.compareTo(key);

        if (rs == 0) { // 找到了节点
            if (node.right == null) {
                node = node.left;
            } else if (node.left == null) {
                node = node.right;
            } else {
                // 找一个节点来替代当前要删除的节点
                Node delNode = node;
                Node successor = minimum(node.right); // 从右子树中找最小的节点来 替代 要删除的节点
                successor.right = removeMin(node.right);
            }
            count--;
        } else if (rs > 0) { // 我比你大， 你去左边找吧
            node.left = remove(node.left, key);
        } else {// 我比你大， 你去右边找吧
            node.right = remove(node.right, key);
        }
        return node;
    }
}