package com.algorithm.DAY07.P11266;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static List<Integer>[] graph;
    static int [] searchOrder;
    static int [] low;
    static int order = 0;
    static List<Integer> answer;
    static boolean[] isCutVertex;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        graph = new List[V+1];

        for(int i=1; i<graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);

            graph[b].add(a);
        }

        searchOrder = new int[V+1];
        low = new int[V+1];
        answer = new ArrayList<>();
        isCutVertex = new boolean[V+1];

        for(int i=1; i<=V; i++) {
            if(searchOrder[i]==0) {
                dfs(i, true);
            }
        }

        for(int i=1; i<=V; i++) {
            if(isCutVertex[i]) answer.add(i);
        }

        System.out.println(answer.size());

        Collections.sort(answer);
        for(int i=0; i<answer.size(); i++) {
            System.out.print(answer.get(i) + " ");
        }
    }

    static int dfs(int now, boolean isRoot) {

        searchOrder[now] = ++order;
        int ret = order;
        int child = 0; // 루트일 경우 단절점 판단을 위함

        for(int next: graph[now]) {
            if(searchOrder[next] == 0) {
                child++;

                int low = dfs(next, false);

                if(!isRoot && low >= searchOrder[now]) {
                    isCutVertex[now] = true;
                }
                ret = Math.min(ret, low);

            } else {
                ret = Math.min(ret, searchOrder[next]);
            }
        }

        if(isRoot && child >= 2) {
            isCutVertex[now] = true;
        }

        return ret;

    }

}
