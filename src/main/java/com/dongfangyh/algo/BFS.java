package com.dongfangyh.algo;

import java.util.LinkedList;
import java.util.Stack;

public class BFS {
    private static class Point{
        final int x;
        final int y;
        Point parent;

        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }
    private static class Maze{
        final int[][] maze;
        final int[][] visit;
        final Point sP;
        final Point dP;
        private static final int VISITED = 1;
        private static final int UNVISITED = 0;

        private static final int PATH = 0;
        private static final int WALL = 1;

        private Maze(int[][]maze, int sX, int sY, int eX, int eY) {
            this.maze = maze;
            this.visit = new int[maze.length][maze[0].length];
            this.sP = new Point(sX, sY);
            this.dP = new Point(eX, eY);
        }

        public boolean isValid(Point p){
            int maxRId = maze.length - 1;
            int maxCId = maze[0].length -1;
            if (p.x < 0 || p.y < 0 || p.x > maxRId || p.y > maxCId){
                // reach or out of border
                return false;
            }
            if (maze[p.x][p.y] == WALL){
                // reach wall
                return false;
            }
            return true;
        }

        public boolean visited(Point p){
            return visit[p.x][p.y] == VISITED;
        }

        public boolean isExit(Point p){
            return (p.x == dP.x) && (p.y == dP.y);
        }

        public Point moveUp(Point cp){
            Point p = new Point(cp.x-1, cp.y);
            if (isValid(p)){
                p.parent = cp;
                visit[cp.x][cp.y] = VISITED;
                return p;
            }
            return null;
        }

        public Point moveDown(Point cp){
            Point p = new Point(cp.x+1, cp.y);
            if (isValid(p)){
                p.parent = cp;
                visit[cp.x][cp.y] = VISITED;
                return p;
            }
            return null;
        }

        public Point moveLeft(Point cp){
            Point p = new Point(cp.x, cp.y - 1);
            if (isValid(p)){
                p.parent = cp;
                visit[cp.x][cp.y] = VISITED;
                return p;
            }
            return null;
        }

        public Point moveRight(Point cp){
            Point p = new Point(cp.x, cp.y + 1);
            if (isValid(p)){
                p.parent = cp;
                visit[cp.x][cp.y] = VISITED;
                return p;
            }
            return null;
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {0, 0, 1, 0, 0},
                {0, 0, 1, 0, 1},
                {1, 0, 0, 0, 0},
                {0, 0, 0, 1, 0},
                {0, 1, 0, 1, 0}
        };
        int sX = 0, sY = 0;
        int eX = 4, eY = 4;
        Maze maze = new Maze(matrix, sX, sY,  eX, eY);
        solve(maze);
    }


    public static void solve(Maze maze){
        LinkedList<Point> nextToVisit = new LinkedList<>();
        nextToVisit.add(maze.sP);
        while (!nextToVisit.isEmpty()){
            Point cp = nextToVisit.removeFirst();
            if (maze.visited(cp)){
                continue;
            }
            if (maze.isExit(cp)){
                // print Path
                printPath(cp);
            }
            Point up = maze.moveUp(cp);
            if (up != null){
                nextToVisit.add(up);
            }
            Point dp = maze.moveDown(cp);
            if (dp != null){
                nextToVisit.add(dp);
            }
            Point lp = maze.moveLeft(cp);
            if (lp != null){
                nextToVisit.add(lp);
            }
            Point rp = maze.moveRight(cp);
            if (rp != null){
                nextToVisit.add(rp);
            }
        }
    }

    private static void printPath(Point p) {
        LinkedList<Point> stack = new LinkedList<>();
        Point cp = p;
        while (cp != null){
            stack.push(cp);
            cp = cp.parent;
        }
        while (!stack.isEmpty()){
            Point path = stack.pop();
            System.out.println("[" + path.x + " ," + path.y + "]");
        }
    }
}
