package com.algorithm.DAY07.P1854;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Vertex {
    int vertex;
    int weight;

    public Vertex(int vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }
}
public class Main {
    static List<Vertex>[] graph;
    static PriorityQueue<Integer>[] dist;
    static int n;
    static int k;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        graph = new List[n+1];
        dist = new PriorityQueue[n+1];

        for(int i=1; i<=n; i++) {
            graph[i] = new ArrayList<>();
            dist[i] = new PriorityQueue<>(Collections.reverseOrder());
        }

        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[from].add(new Vertex(to, cost));
        }

        dijkstra(1);

        for(int i=1; i<=n; i++) {
            if(dist[i].size() < k) {
                System.out.println(-1);
            } else {
                System.out.println(dist[i].peek());
            }
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
        dist[start].add(0);

        while (!pq.isEmpty()) {
            int now = pq.peek().vertex;
            int weight = pq.peek().weight;

            pq.poll();

            if(dist[now].peek() < weight) continue;

            for(int i=0; i<graph[now].size(); i++) {
                int next = graph[now].get(i).vertex;
                int cost = graph[now].get(i).weight;

                if(dist[next].size() < k) {
                    pq.add(new Vertex(next, weight + cost));
                    dist[next].add(weight + cost);
                } else if(weight + cost < dist[next].peek()) {
                    dist[next].poll();
                    pq.add(new Vertex(next, weight + cost));
                    dist[next].add(weight + cost);
                }
            }
        }
    }
}
