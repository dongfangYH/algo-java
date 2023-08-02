package com.dongfangyh.algo.mpt;

/**
 * @author henry.liu
 * @date 2023/7/27
 **/
public class Main {
    public static void main(String[] args) {
        // 创建一个Merkle Patricia树
        MerklePatriciaTree tree = new MerklePatriciaTree();

        tree.insert("app", "n1");
        tree.insert("apple", "red");
        tree.insert("banana", "yellow");
        tree.insert("cherry", "red");

        // 获取节点值
        System.out.println(tree.getValue("apple"));   // 输出: red
        System.out.println(tree.getValue("banana"));  // 输出: yellow
        System.out.println(tree.getValue("cherry"));  // 输出: red
        System.out.println(tree.getValue("orange"));  // 输出: null
    }
}

