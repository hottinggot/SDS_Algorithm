package com.algorithm.DAY06.P3176;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 도로 네트워크

class Node {
    int x;
    int dist;

    public Node(int x, int dist) {
        this.x = x;
        this.dist = dist;
    }
}

public class Main {

    static int n;
    static List<Node>[] tree;
    static int [] depth;
    static int [][] parents;
    static int [][] maxDistance;
    static int [][] minDistance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        tree = new List[n+1];
        depth = new int[n+1];

        int s = 0;

        for(int i=0; i<=n; i++) {
            tree[i] = new ArrayList<>();
        }

        int tempNum = 1;
        while(tempNum < n) {
            tempNum *= 2;
            s++;
        }

        parents = new int[s+1][n+1];
        maxDistance = new int[s+1][n+1];
        minDistance = new int[s+1][n+1];

        for(int i=0; i<n-1; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            tree[a].add(new Node(b, c));
            tree[b].add(new Node(a, c));
        }

        initBFS(new Node(1, 0));

        for(int i=1; i<=s; i++) {
            for(int j=1; j<=n; j++) {
                parents[i][j] = parents[i-1][parents[i-1][j]];
                maxDistance[i][j] = Math.max(maxDistance[i-1][j], maxDistance[i-1][parents[i-1][j]]);
                minDistance[i][j] = Math.min(minDistance[i-1][j], minDistance[i-1][parents[i-1][j]]);
            }
        }

        int k = Integer.parseInt(br.readLine());

        for(int i=0; i<k; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            int diff = 0;

            int minVal = Integer.MAX_VALUE;
            int maxVal = 0;

            if(depth[d] > depth[e]) {
                diff = depth[d] - depth[e];

                for(int j=0; j<=s; j++) {
                    if((diff & (1 << j)) >= 1) {
                        maxVal = Math.max(maxVal, maxDistance[j][d]);
                        minVal = Math.min(minVal, minDistance[j][d]);
                        d = parents[j][d];
                    }
                }

            } else if(depth[d] < depth[e]) {
                diff = depth[e] - depth[d];

                for(int j=0; j<=s; j++) {
                    if((diff & (1 << j)) >= 1) {
                        maxVal = Math.max(maxVal, maxDistance[j][e]);
                        minVal = Math.min(minVal, minDistance[j][e]);
                        e = parents[j][e];
                    }
                }
            }

            if(d==e) {
                System.out.println(minVal + " " + maxVal);
                continue;
            }

            for(int j=s; j>=0; j--) {
                if(parents[j][d] != parents[j][e]) {
                    maxVal = Math.max(maxVal, Math.max(maxDistance[j][d], maxDistance[j][e]));
                    minVal = Math.min(minVal, Math.min(minDistance[j][d], minDistance[j][e]));
                    d = parents[j][d];
                    e = parents[j][e];

                }
            }

            maxVal = Math.max(maxVal, Math.max(maxDistance[0][d], maxDistance[0][e]));
            minVal = Math.min(minVal, Math.min(minDistance[0][d], minDistance[0][e]));

            System.out.println(minVal + " " + maxVal);

        }
    }

    static void initBFS(Node start) {
        Queue<Node> queue = new LinkedList<>();
        boolean [] check = new boolean[n+1];
        queue.add(start);
        check[start.x] = true;

        while (!queue.isEmpty()) {
            int now = queue.peek().x;
            int dist = queue.peek().dist;

            queue.poll();

            for(int i=0; i<tree[now].size(); i++) {
                int next = tree[now].get(i).x;
                int nextDist = tree[now].get(i).dist;

                if(!check[next]) {
                    parents[0][next] = now;
                    maxDistance[0][next] = nextDist;
                    minDistance[0][next] = nextDist;
                    depth[next] = depth[now] + 1;
                    check[next] = true;
                    queue.add(tree[now].get(i));
                }
            }
        }
    }
}
