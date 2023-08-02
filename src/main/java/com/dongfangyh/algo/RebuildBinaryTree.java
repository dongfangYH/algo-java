package com.dongfangyh.algo;

import java.util.Arrays;

/**
 * @author henry.liu
 * @date 2023/8/1
 **/
public class RebuildBinaryTree {
    public static TreeNode build(int[] preOrder, int[] inOrder){
        if (preOrder.length == 0 || inOrder.length == 0){
            return null;
        }
        if (preOrder.length != inOrder.length){
            throw new IllegalArgumentException();
        }
        TreeNode root = new TreeNode(preOrder[0]);
        int rootIdx = -1;
        for(int i = 0; i < inOrder.length; i++){
            if (preOrder[0] == inOrder[i]){
                rootIdx = i;
                break;
            }
        }
        if (rootIdx == -1){
            throw new IllegalArgumentException();
        }
        root.left = build( Arrays.copyOfRange(preOrder,1, rootIdx + 1),
                Arrays.copyOfRange(inOrder, 0, rootIdx));
        root.right = build(Arrays.copyOfRange(preOrder, rootIdx+1, preOrder.length),
                Arrays.copyOfRange(inOrder, rootIdx+1, inOrder.length));
        return root;
    }




    public static void main(String[] args) {
        //preOrder 1, 2, 4, 7, 3, 5, 6, 8
        //InOrder  4, 7, 2, 1, 5, 3, 8, 6
        int[] preOrder = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] inOrder = {4, 7, 2, 1, 5, 3, 8, 6};
        TreeNode root = build(preOrder, inOrder);
        System.out.println(root);
    }
}
