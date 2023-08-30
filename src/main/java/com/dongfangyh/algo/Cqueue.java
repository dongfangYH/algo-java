package com.dongfangyh.algo;

import java.util.Stack;

/**
 * @author henry.liu
 * @date 2023/8/24
 **/
public class Cqueue<T> {

    private final Stack<T> tail = new Stack<>();
    private final Stack<T> head = new Stack<>();

    public boolean appendTail(T t){
        if (tail.isEmpty() && head.isEmpty()){
            tail.push(t);
            return true;
        }
        if (!head.isEmpty()){
            transfer(head, tail);
        }
        tail.push(t);
        return true;
    }

    private void transfer(Stack<T> from, Stack<T> to){
        if (from.empty()){
            throw new IllegalArgumentException("from can not be empty.");
        }
        if (!from.empty()){
            throw new IllegalArgumentException("to is not empty");
        }

        while (!from.isEmpty()){
            to.push(from.pop());
        }
    }

    public T deleteHead(){
        if (head.isEmpty() && tail.isEmpty()){
            return null;
        }
        if (!head.isEmpty()){
            return head.pop();
        }
        transfer(tail, head);
        return head.pop();
    }

    public static void main(String[] args) {

    }
}
