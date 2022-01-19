package com.algorithm.DAY01.P3055;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    static class Point {
        int y;
        int x;

        Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static int[] direction = {-1, 1};
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int r = scanner.nextInt();
        int c = scanner.nextInt();

        char [][] map = new char[r][c];
        int [][] check = new int[r][c];

        Queue<Point> queue = new LinkedList<>();
        Point start = new Point(0,0);
        Point dest = new Point(0,0);

        for(int i=0; i<r; i++) {

            String temp = scanner.next();
            String[] strArray = temp.split("");

            for(int j=0; j<strArray.length; j++) {
                map[i][j] = strArray[j].charAt(0);
                if(map[i][j] == 'S') {
                    start.y = i;
                    start.x = j;
                } else if(map[i][j] == '*') {
                    queue.add(new Point(i, j));
                    check[i][j] = -1;
                } else if(map[i][j] == 'X') {
                    check[i][j] = -2;
                } else if(map[i][j] == 'D') {
                    dest.y = i;
                    dest.x = j;
                }
            }
        }

        check[start.y][start.x] = 1;
        queue.add(start);

        while (!queue.isEmpty()) {
            int nowY = queue.peek().y;
            int nowX = queue.peek().x;
            queue.poll();

            for(int i =0; i<2; i++) {
                int newY = nowY + direction[i];
                int newX = nowX + direction[i];

                if(newX >=0 && newX < c && check[nowY][newX] == 0) {
                    if(check[nowY][nowX]!=-1) {
                        check[nowY][newX] = check[nowY][nowX] + 1;
                        if(map[nowY][newX] == 'D') {
                            break;
                        }
                        queue.add(new Point(nowY, newX));
                    } else {
                        if(map[nowY][newX] != 'D') {
                            check[nowY][newX] = -1;
                            queue.add(new Point(nowY, newX));
                        }
                    }
                }

                if(newY >=0 && newY < r && check[newY][nowX] == 0) {
                    if(check[nowY][nowX]!=-1) {
                        check[newY][nowX] = check[nowY][nowX] + 1;
                        if(map[newY][nowX] == 'D') {
                            break;
                        }
                        queue.add(new Point(newY, nowX));
                    } else {
                        if(map[newY][nowX] != 'D') {
                            check[newY][nowX] = -1;
                            queue.add(new Point(newY, nowX));
                        }
                    }
                }
            }
        }

        if(check[dest.y][dest.x] == 0) {
            System.out.println("KAKTUS");
        } else {
            System.out.println(check[dest.y][dest.x]-1);
        }
    }
}

