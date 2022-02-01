package com.algorithm.DAY07.P3860;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Edge {
    Point from;
    Point to;
    int weight;

    public Edge(Point from, Point to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}

class Point {
    int y;
    int x;
    public Point(int y, int x) {
        this.y = y;
        this.x = x;
    }
}
public class Main {
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0 , -1, 1};
    static int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        while(true) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            if(w==0 && h==0) break;

            int [][] map = new int[h][w];
            int g = Integer.parseInt(br.readLine());
            for(int i=0; i<g; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                map[y][x] = -1;
            }

            int e = Integer.parseInt(br.readLine());
            List<Edge> edgeList = new ArrayList<>();

            for(int i=0; i<e; i++) {
                st = new StringTokenizer(br.readLine());
                int x1 = Integer.parseInt(st.nextToken());
                int y1 = Integer.parseInt(st.nextToken());

                int x2 = Integer.parseInt(st.nextToken());
                int y2 = Integer.parseInt(st.nextToken());

                int weight = Integer.parseInt(st.nextToken());

                map[y1][x1] = -2;
                Edge edge = new Edge(new Point(y1, x1), new Point(y2, x2), weight);
                edgeList.add(edge);
            }

            int [][] cost = new int[h][w];
            for(int i=0; i<h; i++) {
                for(int j=0; j<w; j++) {
                    cost[i][j] = INF;
                    if(i==h-1 && j==w-1) continue;
                    for(int k = 0; k<4; k++) {
                        if(map[i][j] == 0 && i+dy[k] >= 0 && i+dy[k] < h && j+dx[k] >= 0 && j+dx[k] < w) {
                            if(map[i+dy[k]][j+dx[k]] != -1) {
                                Edge edge = new Edge(new Point(i, j), new Point(i+dy[k], j+dx[k]), 1);
                                edgeList.add(edge);
                            }
                        }
                    }
                }
            }

            cost[0][0] = 0;

            for(int i=0; i<w*h-1; i++) {
                for(int j=0; j<edgeList.size(); j++) {
                    Edge nowEdge = edgeList.get(j);
                    if(cost[nowEdge.from.y][nowEdge.from.x] != INF) {
                        cost[nowEdge.to.y][nowEdge.to.x] = Math.min(cost[nowEdge.to.y][nowEdge.to.x], cost[nowEdge.from.y][nowEdge.from.x] + nowEdge.weight);
                    }
                }
            }

            //음수 사이클 확인
            boolean isCycle = false;
            for(int j=0; j<edgeList.size(); j++) {
                Edge nowEdge = edgeList.get(j);
                if(cost[nowEdge.from.y][nowEdge.from.x] != INF) {
                    if(cost[nowEdge.to.y][nowEdge.to.x] > cost[nowEdge.from.y][nowEdge.from.x] + nowEdge.weight) {
                        isCycle = true;
                        break;
                    }
                }
            }

            if(isCycle) {
                System.out.println("Never");
            } else if(cost[h-1][w-1] == INF) {
                System.out.println("Impossible");
            } else {
                System.out.println(cost[h-1][w-1]);
            }
        }
    }
}
