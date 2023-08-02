package com.dongfangyh.algo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author henry.liu
 * @date 2023/6/29
 **/
public class LeafSimilar {

    /**
     * @param root1: the first tree
     * @param root2: the second tree
     * @return: returns whether the leaf sequence is the same
     */
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {

        List<TreeNode> leavesInTree1 = getLeafList(root1);
        List<TreeNode> leavesInTree2 = getLeafList(root2);
        if (leavesInTree1.size() != leavesInTree2.size()){
            return false;
        }
        for (int idx = 0; idx < leavesInTree1.size(); idx++){
            TreeNode n1 = leavesInTree1.get(idx);
            TreeNode n2 = leavesInTree2.get(idx);
            if (n1.val != n2.val){
                return false;
            }
        }
        return true;
    }

    private List<TreeNode> getLeafList(TreeNode root1) {
        List<TreeNode> result = new ArrayList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.push(root1);
        while (!stack.isEmpty()){
            TreeNode currentNode = stack.poll();
            TreeNode leftNode = currentNode.left;
            TreeNode rightNode = currentNode.right;
            if (leftNode != null){
                stack.push(leftNode);
            }
            if (rightNode != null){
                stack.push(rightNode);
            }

            if (leftNode == null && rightNode == null){
                result.add(currentNode);
            }
        }
        return result;
    }
}
