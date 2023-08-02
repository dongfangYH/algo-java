package com.dongfangyh.algo;

/**
 * @author henry.liu
 * @date 2023/8/2
 **/
public class TreeNodeP {

    public String val;
    public TreeNodeP parent, left, right;
    public TreeNodeP(String val) {
        this.val = val;
        this.parent = this.left = this.right = null;
    }


    @Override
    public String toString() {
        return "TreeNodeP{" +
                "val='" + val + '\'' +
                '}';
    }
}
