package com.dongfangyh.algo;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * @author henry.liu
 * @date 2023/7/31
 **/
public class BinaryTree {

    private TreeNode root;

    public BinaryTree(int val) {
        this.root = new TreeNode(val);
    }

    public static void preOrder(TreeNode node){
        if (node == null){
            return;
        }
        System.out.println(node.val);
        preOrder(node.left);
        preOrder(node.right);
    }

    public static void preOrder2(TreeNode node){
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.push(node);
        while (!stack.isEmpty()){
            TreeNode c = stack.pop();
            System.out.println(c.val);
            if (c.right != null){
                stack.push(c.right);
            }
            if (c.left != null){
                stack.push(c.left);
            }
        }
    }

    public static void midOrder(TreeNode node){
        if (node == null){
            return;
        }
        midOrder(node.left);
        System.out.println(node.val);
        midOrder(node.right);
    }

    public static void midOrder2(TreeNode node){
        LinkedList<TreeNode> stack = new LinkedList<>();
        TreeNode c = node;
        while (c != null || !stack.isEmpty()){
            while (c != null){
                stack.push(c);
                c = c.left;
            }
            c = stack.pop();
            System.out.println(c.val);
            c = c.right;
        }
    }

    public static void postOrder(TreeNode node){
        if (node == null){
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.val);
    }

    public static void postOrder2(TreeNode node){
        LinkedList<Integer> rs = new LinkedList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.push(node);
        while (!stack.isEmpty()){
            TreeNode c = stack.pop();
            rs.push(c.val);
            if (c.left != null){
                stack.push(c.left);
            }
            if (c.right != null){
                stack.push(c.right);
            }
        }
        rs.forEach(System.out::println);
    }


    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree(10);
        tree.root.left = new TreeNode(6);
        tree.root.left.left = new TreeNode(4);
        tree.root.left.right = new TreeNode(8);
        tree.root.right = new TreeNode(14);
        tree.root.right.left = new TreeNode(12);
        tree.root.right.right = new TreeNode(16);

        midOrder2(tree.root);
    }
}
