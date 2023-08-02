package com.dongfangyh.algo;

/**
 * @author henry.liu
 * @date 2023/8/2
 **/
public class NextInOrderNode {

    public static TreeNodeP findInOrderNextNode(TreeNodeP current){
        if (current == null){
            return null;
        }

        if (current.right != null){
            TreeNodeP target = current.right;
            while (target.left != null){
                target = target.left;
            }
            return target;
        }else {
            TreeNodeP parent = current.parent;
            while (parent != null){
                if (current == parent.left){
                    return parent;
                }else {
                    current = parent;
                    parent = current.parent;
                }
            }
            return parent;
        }
    }

    public static void main(String[] args) {
        TreeNodeP a = new TreeNodeP("a");
        TreeNodeP b = new TreeNodeP("b");
        TreeNodeP c = new TreeNodeP("c");
        TreeNodeP d = new TreeNodeP("d");
        TreeNodeP e = new TreeNodeP("e");
        TreeNodeP f = new TreeNodeP("f");
        TreeNodeP g = new TreeNodeP("g");
        TreeNodeP h = new TreeNodeP("h");
        TreeNodeP i = new TreeNodeP("i");

        a.left = b;
        a.right = c;
        b.parent = a;
        c.parent = a;
        b.left = d;
        b.right = e;
        d.parent = b;
        e.parent = b;
        c.left = f;
        c.right = g;
        f.parent = c;
        g.parent = c;
        e.left = h;
        e.right = i;
        h.parent = e;
        i.parent = e;

        System.out.println(findInOrderNextNode(a));
        System.out.println(findInOrderNextNode(b));
        System.out.println(findInOrderNextNode(c));
        System.out.println(findInOrderNextNode(d));
        System.out.println(findInOrderNextNode(e));
        System.out.println(findInOrderNextNode(f));
        System.out.println(findInOrderNextNode(g));
        System.out.println(findInOrderNextNode(h));
        System.out.println(findInOrderNextNode(i));
    }
}
