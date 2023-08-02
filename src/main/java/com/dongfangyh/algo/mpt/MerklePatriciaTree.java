package com.dongfangyh.algo.mpt;

/**
 * @author henry.liu
 * @date 2023/7/27
 **/
public class MerklePatriciaTree {
    private Node root;

    public MerklePatriciaTree() {
        this.root = new Node("", "");
    }

    public void insert(String key, String value) {
        insertNode(root, key, value);
    }

    private void insertNode(Node node, String key, String value) {
        if (node.getKey().isEmpty()) { // root node
            if (node.hasChild(key)) {
                Node child = node.getChild(key);
                insertNode(child, key.substring(child.getKey().length()), value);
            } else {
                Node newChild = new Node(key, value);
                node.addChild(key, newChild);
            }
        } else { // non-root node
            String commonPrefix = findCommonPrefix(node.getKey(), key);
            if (commonPrefix.isEmpty()) {
                // Create new child node with the remaining part of the key
                String remainKey = key.length() <= node.getKey().length() ? "" : key.substring(node.getKey().length());
                Node newChild = new Node(remainKey, value);
                node.addChild(remainKey, newChild);
            } else if (commonPrefix.equals(node.getKey())) {
                Node child = node.getChild(commonPrefix);
                insertNode(child, key.substring(commonPrefix.length()), value);
            } else {
                // Split the node
                String remainingNodeKey = node.getKey().substring(commonPrefix.length());
                Node newChild1 = new Node(remainingNodeKey, node.getValue());
                Node newChild2 = new Node(key.substring(commonPrefix.length()), value);
                node.setValue("");
                node.addChild(remainingNodeKey, newChild1);
                node.addChild(key.substring(commonPrefix.length()), newChild2);
                node.setKey(commonPrefix);
            }
        }
    }

    public String getValue(String key) {
        return searchNode(root, key);
    }

    private String searchNode(Node node, String key) {
        if (node.getKey().isEmpty()) { // root node
            if (node.hasChild(key)) {
                Node child = node.getChild(key);
                return searchNode(child, key.substring(child.getKey().length()));
            } else {
                return null;
            }
        } else { // non-root node
            String commonPrefix = findCommonPrefix(node.getKey(), key);
            if (commonPrefix.isEmpty()) {
                return null;
            } else if (commonPrefix.equals(node.getKey())) {
                Node child = node.getChild(commonPrefix);
                return searchNode(child, key.substring(commonPrefix.length()));
            } else {
                return null;
            }
        }
    }

    private String findCommonPrefix(String str1, String str2) {
        int minLength = Math.min(str1.length(), str2.length());
        int index = 0;
        while (index < minLength && str1.charAt(index) == str2.charAt(index)) {
            index++;
        }
        return str1.substring(0, index);
    }
}

