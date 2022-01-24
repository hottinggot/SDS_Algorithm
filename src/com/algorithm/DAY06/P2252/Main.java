package com.algorithm.DAY06.P2252;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static List<Integer>[] graph;
    static int[] smallerThanMe;
    static boolean[] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        graph = new List[n+1];
        smallerThanMe = new int[n+1];
        check = new boolean[n+1];

        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int small = Integer.parseInt(st.nextToken());
            int big = Integer.parseInt(st.nextToken());

            if(graph[small] == null) {
                graph[small] = new ArrayList<>();
            }
            graph[small].add(big);
            smallerThanMe[big]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int i=1; i<=n; i++) {
            if(smallerThanMe[i]==0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int node = queue.poll();
            System.out.print(node + " ");
            if(graph[node]==null) continue;
            for(int i=0; i<graph[node].size(); i++) {
                smallerThanMe[graph[node].get(i)]--;
                if(smallerThanMe[graph[node].get(i)]==0) {
                    queue.add(graph[node].get(i));
                }

            }
            graph[node].clear();
        }
    }
}
