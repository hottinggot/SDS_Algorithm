package com.algorithm.DAY06.P1922;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Edge {
    int from;
    int to;
    int w;

    public Edge(int from, int to, int w) {
        this.from = from;
        this.to = to;
        this.w = w;
    }
}

public class Main {
    static int[] parent;
    static boolean connected = false;
    static void union(int x, int y) {
        connected = false;
        int x_root = find(x);
        int y_root = find(y);

        if(x_root==y_root) return;

        parent[x_root] = y_root;
        connected = true;
    }

    static int find(int x) {
        if(parent[x] == x) {
            return x;
        } else {
            return parent[x] = find(parent[x]);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        parent = new int[n+1];
        for(int i=1; i<=n; i++) {
            parent[i] = i;
        }

        StringTokenizer st;
        List<Edge> list = new ArrayList<>();

        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            list.add(new Edge(from, to, w));
        }

        Collections.sort(list, new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.w - o2.w;
            }
        });

        int cost = 0;

        for(int i=0; i<list.size(); i++) {
            Edge edge = list.get(i);
            union(edge.from, edge.to);

            if(connected) {
                cost += edge.w;
            }
        }

        System.out.println(cost);

    }
}
