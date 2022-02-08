package com.algorithm.Extra.P9376;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Point {
    int y;
    int x;
    int weight;
    public Point(int y, int x, int weight) {
        this.y = y;
        this.x = x;
        this.weight = weight;
    }
}

public class Main {
    static int dy [] = {-1, 1, 0, 0};
    static int dx [] = {0, 0, -1, 1};
    static int INF = Integer.MAX_VALUE;
    static int FAR = 10001;
    static char [][] map;
    static int [][] dist;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());

        while (t>0) {
            t--;
            st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            map = new char[h][w];
            dist = new int[h][w];
            List<Point> startList = new ArrayList<>();

            for(int i=0; i<h; i++) {
                String temp = br.readLine();
                for(int j=0; j<w; j++) {
                    map[i][j] = temp.charAt(j);
                    dist[i][j] = INF;
                    if(map[i][j] == '$') {
                        startList.add(new Point(i, j, 0));
                    }
                }
            }



            for(int i=0; i<h; i++) {
                for(int j=0; j<w; j++) {
                    if(map[i][j] == '.' || map[i][j] == '#') {
                        for(int k=0; k<4; k++) {
                            int ny = i + dy[k];
                            int nx = j + dx[k];

                            if(ny >=0 && ny < h && nx >= 0 && nx < w) {

                            }
                        }
                    }
                }
            }
        }
    }

    static void dijkstra(Point start) {

        PriorityQueue<Point> pq = new PriorityQueue<>();

        pq.add(start);


    }
}
