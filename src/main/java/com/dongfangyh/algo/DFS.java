package com.dongfangyh.algo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class DFS {

    private static class Point{
        private final int x;
        private final int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public String toString() {
            return "P(" +
                    "x=" + x +
                    ", y=" + y +
                    ')';
        }
    }

    private static class DirectPoint extends Point{
        private int direct;
        public DirectPoint(int x, int y) {
            super(x, y);
            direct = -1;
        }

        public DirectPoint(int x, int y, int direct) {
            super(x, y);
            this.direct = direct;
        }

        public Point toPoint() {
            return this;
        }
    }

    /**
     * direction:
     *        3 up
     * 2 left       0 right
     *        1 down
     * @param maze
     * @param startX
     * @param startY
     * @param destX
     * @param destY
     * @return
     */
    public static List<Point> getPossiblePath(int[][] maze, int startX, int startY, int destX, int destY){
        // TODO check whether params are valid
        Stack<DirectPoint> stack = new Stack<>();
        DirectPoint entry = new DirectPoint(startX, startY, -1);
        stack.push(entry);
        maze[startX][startY] = -1;
        int depth = maze.length;
        int width = maze[0].length;

        int d = 0;
        int pX = startX;
        int pY = startY;
        while (!stack.empty()){
            boolean passed = false;
            for (int dr = d; dr < 4; dr++){
                // right
                if (dr == 0 && pY + 1 < width && maze[pX][pY + 1] == 0){
                    ++pY;
                    stack.push(new DirectPoint(pX, pY, dr));
                    passed = true;
                    maze[pX][pY] = -1;
                    break;
                }
                // down
                if (dr == 1 && pX + 1 < depth && maze[pX + 1][pY] == 0){
                    ++pX;
                    stack.push(new DirectPoint(pX, pY, dr));
                    passed = true;
                    maze[pX][pY] = -1;
                    break;
                }
                // left
                if (dr == 2 && pY - 1 >= 0 && maze[pX][pY - 1] == 0){
                    --pY;
                    stack.push(new DirectPoint(pX, pY, dr));
                    passed = true;
                    maze[pX][pY] = -1;
                    break;
                }
                // up
                if (dr == 3 && pX - 1 >= 0 && maze[pX - 1][pY] == 0){
                    --pX;
                    stack.push(new DirectPoint(pX, pY, dr));
                    passed = true;
                    maze[pX][pY] = -1;
                    break;
                }
            }
            // nowhere to go, popup last directPoint
            if (!passed){
                stack.pop();
                DirectPoint dp = stack.peek();
                pX = dp.getX();
                pY = dp.getY();
                d = dp.direct + 1;
            }
            // reach destination
            if (pX == destX && pY == destY){
                break;
            }
        }
        List<Point> paths = new ArrayList<>();
        while (!stack.empty()){
            paths.add(stack.pop().toPoint());
        }
        Collections.reverse(paths);
        return paths;
    }



    public static void main(String[] args) {
        int[][] maze = new int[][]{
                {0, 1, 0, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 0, 0, 0},
                {0, 1, 1, 1, 0},
                {0, 0, 0, 1, 0}
        };
        int startX = 0, startY = 0;
        int destX = 4, destY = 4;
        List<Point> paths = getPossiblePath(maze, startX, startY, destX, destY);
        paths.forEach(point -> System.out.println(point.toString()));
    }
}
