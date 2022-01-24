package com.algorithm.DAY06.P1922_2;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Vertex {
    int x;
    int weight;

    public Vertex(int x, int weight) {
        this.x = x;
        this.weight = weight;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        StringTokenizer st;
        List<Vertex> [] lists = new List[n+1];

        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            if(lists[from] == null) {
                lists[from] = new ArrayList<>();
            }

            if(lists[to] == null) {
                lists[to] = new ArrayList<>();
            }

            lists[from].add(new Vertex(to, w));
            lists[to].add(new Vertex(from, w));
        }

        PriorityQueue<Vertex> pq = new PriorityQueue<>(new Comparator<Vertex>() {
            @Override
            public int compare(Vertex o1, Vertex o2) {
                return o1.weight - o2.weight;
            }
        });

        pq.add(new Vertex(1, 0));

        boolean [] visited = new boolean[n+1];

        int cost = 0;

        while (!pq.isEmpty()) {
            Vertex vertex = pq.poll();

            if(visited[vertex.x]) continue;

            visited[vertex.x] = true;
            cost += vertex.weight;

            for(int i=0; i<lists[vertex.x].size(); i++) {
                pq.add(lists[vertex.x].get(i));
            }
        }

        System.out.println(cost);
    }
}
