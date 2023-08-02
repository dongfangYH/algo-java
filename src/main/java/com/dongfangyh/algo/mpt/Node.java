package com.dongfangyh.algo.mpt;

/**
 * @author henry.liu
 * @date 2023/7/27
 **/
import java.util.HashMap;
import java.util.Map;

public class Node {
    private String key;
    private String value;
    private final Map<String, Node> children;

    public Node(String key, String value) {
        this.key = key;
        this.value = value;
        this.children = new HashMap<>();
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public Map<String, Node> getChildren() {
        return children;
    }

    public void addChild(String key, Node child) {
        children.put(key, child);
    }

    public boolean hasChild(String key) {
        return children.containsKey(key);
    }

    public Node getChild(String key) {
        return children.get(key);
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
