package com.algorithm.DAY07.P5719;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 거의 최단 경로
class Vertex {
    int vertex;
    int weight;
    boolean flag;

    public Vertex(int vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }
}
public class Main {
    static int n;
    static int m;
    static int start;
    static int destination;
    static List<Vertex>[] graph;
    static int[] dist;
    static int INF = (int) 1e8;
    static List<Integer>[] tracking;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            if(n==0 && m==0) break;

            st = new StringTokenizer(br.readLine());

            start = Integer.parseInt(st.nextToken());
            destination = Integer.parseInt(st.nextToken());

            graph = new List[n];
            dist = new int[n];
            tracking = new List[n];

            for(int i=0; i<n; i++) {
                graph[i] = new ArrayList<>();
                dist[i] = INF;
                tracking[i] = new ArrayList<>();
            }

            for(int i=0; i<m; i++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                int p = Integer.parseInt(st.nextToken());

                graph[u].add(new Vertex(v, p));
            }

            dijkstra(start);
            markShortestPath(destination);

            for(int i=0; i<n; i++ ) {
                dist[i] = INF;
            }

            dijkstra(start);
            if(dist[destination] == INF) System.out.println(-1);
            else System.out.println(dist[destination]);
        }


    }

    static void dijkstra(int start) {
        PriorityQueue<Vertex> pq = new PriorityQueue<>(new Comparator<Vertex>() {
            @Override
            public int compare(Vertex o1, Vertex o2) {
                return o1.weight - o2.weight;
            }
        });

        pq.add(new Vertex(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()) {
            int now = pq.peek().vertex;
            int nowWeight = pq.peek().weight;
            pq.poll();

            if(nowWeight > dist[now]) continue;

            for(int i=0; i<graph[now].size(); i++) {

                if(graph[now].get(i).flag) continue;

                int next = graph[now].get(i).vertex;
                int nextWeight = graph[now].get(i).weight;

                if(dist[now] + nextWeight < dist[next]) {
                    dist[next] = dist[now] + nextWeight;
                    tracking[next].clear();
                    tracking[next].add(now);
                    pq.add(graph[now].get(i));
                } else if(dist[now] + nextWeight == dist[next]) {
                    tracking[next].add(now);
                }
            }
        }
    }

    static void markShortestPath(int now) {

        if(now == start) return;

        for(int before: tracking[now]) {
            for(Vertex next: graph[before]) {
                if(next.vertex == now && !next.flag) {
                    next.flag = true;
                    markShortestPath(before);
                }
            }
        }
    }
}
