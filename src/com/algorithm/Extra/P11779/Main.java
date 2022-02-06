package com.algorithm.Extra.P11779;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Vertex {
    int to;
    long cost;

    public Vertex(int to, long cost) {
        this.to = to;
        this.cost = cost;
    }
}

public class Main {
    static List<Vertex>[] graph;
    static int n;
    static long INF = (long)1e11;
    static int [] dp;
    static long [] dist;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        graph = new List[n+1];

        for(int i=1; i<=n; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            long cost = Integer.parseInt(st.nextToken());

            graph[from].add(new Vertex(to, cost));
        }

        st = new StringTokenizer(br.readLine());

        int start = Integer.parseInt(st.nextToken());
        int dest = Integer.parseInt(st.nextToken());

        dist = new long[n+1];
        dp = new int[n+1];

        for(int i=0; i<=n; i++) {
            dist[i] = INF;
        }

        dijkstra(start);

        System.out.println(dist[dest]);

        Stack<Integer> stack = new Stack<>();
        stack.add(dest);
        int now = dest;
        while (true) {
            now = dp[now];
            stack.add(now);
            if(now == start) break;
        }
        System.out.println(stack.size());

        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }

    static void dijkstra(int start) {
        PriorityQueue<Vertex> pq = new PriorityQueue<>(new Comparator<Vertex>() {
            @Override
            public int compare(Vertex o1, Vertex o2) {
                return (int)(o1.cost - o2.cost);
            }
        });

        pq.add(new Vertex(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            int now = pq.peek().to;
            long nowCost = pq.peek().cost;
            pq.poll();

            if(nowCost > dist[now]) continue;

            for(int i=0; i<graph[now].size(); i++) {
                int next = graph[now].get(i).to;
                long nextCost = graph[now].get(i).cost;

                if(nowCost + nextCost < dist[next]) {
                    dp[next] = now;
                    dist[next] = nowCost + nextCost;
                    pq.add(new Vertex(next, nowCost + nextCost));
                }
            }
        }
    }
}
