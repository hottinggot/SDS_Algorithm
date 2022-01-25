package com.algorithm.DAY06.P11438;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// LCA2

public class Main {
    static List<Integer>[] tree;
    static int [] depth;
    static int[][] parent;
    static boolean[] check;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st;
        tree = new List[n+1];
        depth = new int[n+1];
        check = new boolean[n+1];

        int num = 1;
        int s = 0;
        while (num<n) {
            num *= 2;
            s++;
        }

        parent = new int[s+1][n+1];

        depth[1] = 0;
        parent[0][1] = 0;

        for(int i=0; i<=n; i++) {
            tree[i] = new ArrayList<>();
        }

        for(int i=0; i<n-1; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            tree[a].add(b);
            tree[b].add(a);
        }

        bfs(1);

        for(int i=1; i<=s; i++) {
            for(int j=1; j<=n; j++) {
                parent[i][j] = parent[i-1][parent[i-1][j]];
            }
        }

        int m = Integer.parseInt(br.readLine());

        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            int diff = 0;
            int higher = x;
            int now = y;

            if(depth[x] < depth[y]) {
                diff = depth[y] - depth[x];
                higher = x;
                now = y;

            } else if(depth[x] > depth[y]) {
                diff = depth[x] - depth[y];
                higher = y;
                now = x;
            }

            for(int j=0; j<=s; j++) {
                if((diff & (1 << j)) >= 1) {
                    now = parent[j][now];
                }
            }

            x = higher;
            y = now;

            if(x == y) {
                System.out.println(x);
                continue;
            }

            for(int j=s; j>=0; j--) {
                if(parent[j][x] != parent[j][y]) {
                    x = parent[j][x];
                    y = parent[j][y];
                }
            }

            System.out.println(parent[0][x]);
        }
    }

    static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();

        queue.add(start);
        check[start] = true;

        while (!queue.isEmpty()) {
            int now = queue.poll();

            for(int i=0; i<tree[now].size(); i++) {
                int next = tree[now].get(i);
                if(!check[next]) {
                    queue.add(next);
                    check[next] = true;
                    parent[0][next] = now;
                    depth[next] = depth[now] + 1;
                }
            }
        }
    }

}
