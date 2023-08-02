package com.dongfangyh.algo;

import java.util.*;

/**
 * @author henry.liu
 * @date 2023/6/30
 **/
public class AllPossibleFBT {

    public static List<TreeNode> allPossibleFBT(int n) {
        List<TreeNode> treeList = new LinkedList<>();
        if (n % 2 != 1){
            return treeList;
        }
        if (n == 1){
            TreeNode root = new TreeNode(0);
            treeList.add(root);
            return treeList;
        }
        for(int i = 1; i < n; i+=2){
            List<TreeNode> left = allPossibleFBT(i);
            List<TreeNode> right = allPossibleFBT(n - 1 - i);
            for (TreeNode lt : left){
                for (TreeNode rt : right){
                    TreeNode root = new TreeNode(0);
                    root.left = lt;
                    root.right = rt;
                    treeList.add(root);
                }
            }
        }
        return treeList;
    }

    /**
     * 1 -> 1
     * 3 -> 1
     * 5 -> 2
     * 7 ->
     * 输入：7
     * 输出：[
     *          [0,0,0,null,null,0,0,null,null,0,0],
     *          [0,0,0,null,null,0,0,0,0],
     *          [0,0,0,0,0,0,0],
     *          [0,0,0,0,0,null,null,null,null,0,0],
     *          [0,0,0,0,0,null,null,0,0]
     *      ]
     * @param n
     * @return
     */
    public static List<TreeNode> allPossibleFBT2(int n) {
        List<TreeNode> treeList = new LinkedList<>();
        if (n % 2 != 1){
            return treeList;
        }
        TreeNode root = new TreeNode(0);
        treeList.add(root);
        growTreeSeq(1, n, treeList);
        return treeList;
    }

    private static void growTreeSeq(int nodeNums, int targetNum, List<TreeNode> treeList) {
        if (nodeNums < targetNum){
            nodeNums += 2;
            Set<String> treePatterns = new HashSet<>();
            List<TreeNode> trees = new LinkedList<>();
            while (!treeList.isEmpty()){
                TreeNode root = treeList.remove(0);
                List<TreeNode> leafNodes = getLeafNodes(root);
                for (TreeNode leafNode : leafNodes){
                    leafNode.left = new TreeNode(0);
                    leafNode.right = new TreeNode(0);
                    String pattern = getTreePattern(root);
                    if (!treePatterns.contains(pattern)){
                        treePatterns.add(pattern);
                        trees.add(cloneTree(root));
                    }
                    //recover
                    leafNode.left = null;
                    leafNode.right = null;
                }
            }
            treeList.addAll(trees);
            growTreeSeq(nodeNums, targetNum, treeList);
        }
    }

    private static List<TreeNode> getLeafNodes(TreeNode root) {
        List<TreeNode> leafNodes = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            if (node.left == null && node.right == null){
                leafNodes.add(node);
            }
            if (node.left != null){
                queue.offer(node.left);
            }
            if (node.right != null){
                queue.offer(node.right);
            }
        }
        return leafNodes;
    }

    public static String getTreePattern(TreeNode node){
        StringBuilder builder = new StringBuilder();
        LinkedList<TreeNode> stack = new LinkedList<>();
        stack.offer(node);
        while (!stack.isEmpty()){
            TreeNode currentNode = stack.poll();
            if (currentNode != null){
                builder.append(currentNode.val);
                stack.offer(currentNode.left);
                stack.offer(currentNode.right);
            }else {
                builder.append(1);
            }
        }
        String pattern = builder.toString();
        int eIdx = pattern.length() - 1;
        for (;;){
            if (pattern.charAt(eIdx) == '1'){
                eIdx--;
            }else {
                break;
            }
        }
        return pattern.substring(0, eIdx + 1);
    }

    public static TreeNode cloneTree(TreeNode root){
        if (root == null){
            return null;
        }
        TreeNode newTree = new TreeNode(root.val);
        newTree.left = cloneTree(root.left);
        newTree.right = cloneTree(root.right);
        return newTree;
    }


    public static TreeNode getTreeFromPattern(String pattern){
        TreeNode[] tmpList = new TreeNode[pattern.length()];
        for (int i = 0; i < pattern.length(); i++){
            boolean isNode = pattern.charAt(i) == '0';
            if (isNode){
                tmpList[i] = new TreeNode(0);
            }else {
                tmpList[i] = null;
            }

        }
        for (int i = 0; i < tmpList.length; i++){
            TreeNode currenNode = tmpList[i];
            if (currenNode != null){
                int lIdx = i * 2 + 1;
                int rIdx = i * 2 + 2;
                if (lIdx < tmpList.length){
                    currenNode.left = tmpList[lIdx];
                }
                if (rIdx < tmpList.length){
                    currenNode.right = tmpList[rIdx];
                }
            }
        }
        return tmpList[0];
    }

    public static void main(String[] args) {
        /*TreeNode root = new TreeNode(0);
        TreeNode l1 = new TreeNode(0);
        TreeNode r1 = new TreeNode(0);
        TreeNode l2 = new TreeNode(0);
        TreeNode r2 = new TreeNode(0);
        TreeNode l3 = new TreeNode(0);
        TreeNode r3 = new TreeNode(0);
        root.left = l1;
        root.right = r1;
        l1.left = l2;
        l1.right = r2;
        l2.left = l3;
        l2.right = r3;
        String pattern = getTreePattern(root);
        System.out.println(pattern);
        TreeNode newTree = getTreeFromPattern(pattern);
        System.out.println(newTree);
        System.out.println(getTreePattern(newTree));*/
        //List<TreeNode> treeNodes = allPossibleFBT(7);
        //treeNodes.forEach(treeNode -> System.out.println(getTreePattern(treeNode)));
        System.out.println(getTreeFromPattern("000110000"));
    }
}
