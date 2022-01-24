package com.algorithm.DAY07.P11657;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Edge {
    int from;
    int to;
    int weight;

    public Edge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}

public class Main {
    static int INF = (int) 1e7;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<Edge> list = new ArrayList<>();

        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list.add(new Edge(a, b, c));
        }

        int [] dist = new int[n+1];

        for(int i=2; i<=n; i++) {
            dist[i] = INF;
        }

        for(int i=0; i<n; i++) {
            for(int j=0; j<list.size(); j++) {
                Edge edge = list.get(j);

                if(dist[edge.from] < INF) {
                    dist[edge.to] = Math.min(dist[edge.to], dist[edge.from] + edge.weight);
                }
            }
        }

        boolean cycle = false;
        for(int j=0; j<list.size(); j++) {
            Edge edge = list.get(j);

            if(dist[edge.from] < INF) {
                if(dist[edge.to] != Math.min(dist[edge.to], dist[edge.from] + edge.weight)) {
                    cycle = true;
                    break;
                }
            }
        }

        if(cycle) {
            System.out.println(-1);
        } else {
            for(int i=2; i<=n; i++) {
                if(dist[i] == INF) System.out.println(-1);
                else System.out.println(dist[i]);
            }
        }

    }
}
