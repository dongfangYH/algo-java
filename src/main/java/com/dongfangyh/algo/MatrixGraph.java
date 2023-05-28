package com.dongfangyh.algo;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * 邻接矩阵图
 */
public class MatrixGraph {

    private Object[] nodes;
    private int[][] relations;

    public MatrixGraph(Object[] nodes, int[][] relations){
        this.nodes = nodes;
        this.relations = relations;
    }

    /* 获取顶点数量 */
    public int size() {
        return nodes.length;
    }

    /* 添加顶点 */
    public void addVertex(Object node) {
        nodes = Arrays.copyOf(nodes, nodes.length + 1);
        nodes[nodes.length - 1] = node;

        int[][] rels = new int[nodes.length][nodes.length];
        for (int y = 0; y < relations.length ; y++){
            for (int x = 0; x < relations.length; x++){
                rels[y][x] = relations[y][x];
            }
        }
        relations = rels;
    }

    /* 删除顶点 */
    public void removeVertex(int index) {
        Object[] newNodes = new Object[nodes.length - 1];
        for (int i = 0; i < newNodes.length; i++){
            if (i < index){
                newNodes[i] = nodes[i];
            }else {
                newNodes[i] = nodes[i+1];
            }
        }
        nodes = newNodes;
        int[][] newRelations = new int[relations.length - 1][relations.length - 1];
        for (int y = 0; y < newRelations.length; y++){
            for (int x = 0; x < newRelations.length; x++){
                if (y < index){
                    if (x < index){
                        newRelations[y][x] = relations[y][x];
                    }else {
                        newRelations[y][x] = relations[y][x+1];
                    }
                }else {
                    if (x < index){
                        newRelations[y][x] = newRelations[y+1][x];
                    }else {
                        newRelations[y][x] = newRelations[y+1][x+1];
                    }
                }
            }
        }
        relations = newRelations;
    }

    /**
     * 找到目标节点坐标
     * @param target
     * @return
     */
    public int getIndex(Object target){
        for (int i = 0; i < nodes.length; i++){
            if (Objects.equals(target, nodes[i])){
                return i;
            }
        }
        return -1;
    }

    public void removeVertex(Object node){
        int idx = getIndex(node);
        if (idx != -1){
            removeVertex(idx);
        }
    }

    /* 添加边 */
    // 参数 i, j 对应 vertices 元素索引
    public void addEdge(int y, int x) {
        checkArgument(y, x);
        if (y != x){
            relations[y][x] = 1;
            relations[x][y] = 1;
        }
    }

    /* 删除边 */
    // 参数 i, j 对应 vertices 元素索引
    public void removeEdge(int y, int x) {
        checkArgument(y ,x);
        relations[y][x] = 0;
        relations[x][y] = 0;
    }

    private void checkArgument(int y, int x){
        int maxL = relations.length - 1;
        if (y > maxL || x > maxL || y < 0 || x < 0){
            throw new IllegalArgumentException("illegal argument.");
        }
    }

    /* 打印邻接矩阵 */
    public void print() {
        StringBuilder printBuffer = new StringBuilder();
        for (int y = -1; y < nodes.length; y++){
            for (int x = -1; x < nodes.length; x++){
                if (y == -1 && x == -1){
                    printBuffer.append("[").append("#").append("]");
                }
                if (y == -1 && x != -1){
                    printBuffer.append("[").append(nodes[x]).append("]");
                }
                if (x == -1 && y != -1){
                    printBuffer.append("[").append(nodes[y]).append("]");
                }
                if (x != -1 && y != -1){
                    printBuffer.append("[").append(relations[y][x]).append("]");
                }
            }
            printBuffer.append("\r\n");
        }
        System.out.println(printBuffer);
    }

    public static void main(String[] args) {
        String[] nodes = new String[]{
          "A","B","C","E","F"
        };
        int[][] rel = new int[nodes.length][nodes.length];
        MatrixGraph graph = new MatrixGraph(nodes, rel);
        graph.print();
        graph.addEdge(0, 1);
        graph.print();
        graph.addEdge(0, 2);
        graph.print();
        graph.addEdge(1, 3);
        graph.print();
        graph.addEdge(2, 4);
        graph.print();
        graph.addVertex("G");
        graph.print();
        graph.addEdge(3, 5);
        graph.print();
        graph.addEdge(4, 5);
        graph.print();
        graph.removeVertex(5);
        graph.print();
    }
}
