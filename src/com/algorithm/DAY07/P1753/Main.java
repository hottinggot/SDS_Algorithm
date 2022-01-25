package com.algorithm.DAY07.P1753;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 최단경로

class Vertex {

    int vertex;
    int weight;

    public Vertex(int vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }
}

public class Main {
    static int INF = (int) 1e7;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        int K = Integer.parseInt(br.readLine());

        List<Vertex>[] graph = new List[V+1];

        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            if(graph[u] == null) {
                graph[u] = new ArrayList<>();
            }

            graph[u].add(new Vertex(v, w));
        }

        PriorityQueue<Vertex> pq = new PriorityQueue<>(new Comparator<Vertex>() {
            @Override
            public int compare(Vertex o1, Vertex o2) {
                return o1.weight - o2.weight;
            }
        });

        int [] visited = new int[V+1];
        for(int i=0; i<=V; i++) {
            visited[i] = INF;
        }

        pq.add(new Vertex(K, 0));

        while (!pq.isEmpty()) {
            int vertex = pq.peek().vertex;
            int weight = pq.peek().weight;

            pq.poll();

            if(weight >= visited[vertex]) continue;

            visited[vertex] = weight;

            if(graph[vertex]==null) continue;
            List<Vertex> vertexList = graph[vertex];

            for(int i=0; i<graph[vertex].size(); i++) {

                if(visited[vertex] + vertexList.get(i).weight < visited[vertexList.get(i).vertex]) {
                    Vertex v = new Vertex(vertexList.get(i).vertex, visited[vertex] + vertexList.get(i).weight);
                    pq.add(v);
                }
            }
        }

        for(int i=1; i<=V; i++) {
            if(visited[i] == INF) System.out.println("INF");
            else System.out.println(visited[i]);
        }
    }
}
