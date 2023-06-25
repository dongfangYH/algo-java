package com.dongfangyh.algo;

import java.util.*;

/**
 * 基于邻接表实现的图
 * @author henry.liu
 * @date 2023/6/25
 **/
public class TableGraph<T> {

    Map<T, List<T>> adjMap;

    /**
     *      b ---- d
     *    /          \
     *   a ---------- f
     *    \          /
     *     c ----  e
     *
     * 构造函数, edges例子:
     *  a b
     *  a c
     *  a f
     *  b d
     *  c e
     *  d f
     *  e f
     *
     * @param edges
     */
    public TableGraph(T[][] edges){
        adjMap = new HashMap<>();
        for (T[] edge: edges){
            addNode(edge[0]);
            addNode(edge[1]);
            addEdge(edge[0], edge[1]);
        }
    }

    public void addEdge(T t1, T t2) {
        if (Objects.equals(t1, t2)){
            throw new RuntimeException("t1 equals t2");
        }
        adjMap.get(t1).add(t2);
        adjMap.get(t2).add(t1);
    }

    public void addNode(T t) {
        if (adjMap.containsKey(t)){
            adjMap.put(t, new ArrayList<>());
        }
    }

    public int size() {
        return adjMap.keySet().size();
    }

    public void removeEdge(T t1, T t2) {
        adjMap.get(t1).remove(t2);
        adjMap.get(t2).remove(t1);
    }

    public void removeNode(T t){
        adjMap.get(t).forEach(linkT -> {
            adjMap.get(linkT).remove(t);
        });
        adjMap.remove(t);
    }

    public void print() {

    }

}
