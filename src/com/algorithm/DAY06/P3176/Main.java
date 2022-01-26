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
    static int [][] distanceToNthParents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        tree = new List[n+1];
        depth = new int[n+1];

        int s = 0;

        int tempNum = 1;
        while(tempNum < n) {
            tempNum *= 2;
            s++;
        }

        parents = new int[s+1][n+1];
        distanceToNthParents = new int[s+1][n+1];

        for(int i=1; i<=n; i++) {
            tree[i] = new ArrayList<>();
        }

        for(int i=0; i<n-1; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            tree[a].add(new Node(b, c));
            tree[b].add(new Node(a, c));
        }

        boolean[] findRoot = new boolean[n+1];

        for(int i=1; i<=n; i++) {
            for(Node node: tree[i]) {
                findRoot[node.x] = true;
            }
        }

        initBFS(new Node(1, 0));

        for(int i=1; i<=s; i++) {
            for(int j=1; j<=n; j++) {
                parents[i][j] = parents[i-1][parents[i-1][j]];
                distanceToNthParents[i][j] += distanceToNthParents[i-1][parents[i-1][j]];
            }
        }

        int k = Integer.parseInt(br.readLine());

        for(int i=0; i<k; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            int diff = 0;
            int cost = 0;

            int minVal = 0;
            int maxVal = 0;

            if(depth[d] > depth[e]) {
                diff = depth[d] - depth[e];

                for(int j=1; j<=s; j++) {
                    if((diff & (1 << j)) >= 1) {
                        d = parents[j][d];
                        cost += distanceToNthParents[j][d];
                    }
                }
            } else if(depth[d] < depth[e]) {
                diff = depth[e] - depth[d];

                for(int j=1; j<=s; j++) {
                    if((diff & (1 << j)) >= 1) {
                        e = parents[j][e];
                        cost += distanceToNthParents[j][e];
                    }
                }
            }

            if(d==e) {
                minVal = cost;
                maxVal = cost;
                System.out.println(minVal + " " + maxVal);
                continue;
            }

            for(int j=s; j>=0; j--) {
                if(parents[j][d] != parents[j][e]) {
                    d = parents[j][d];
                    cost += distanceToNthParents[j][d];

                    e = parents[j][e];
                    cost += distanceToNthParents[j][e];
                }
            }

            d = parents[0][d];
            e = parents[0][e];
            cost += distanceToNthParents[0][d];
            cost += distanceToNthParents[0][e];

            System.out.println(d + " " + cost);

        }
    }

    static void initBFS(Node start) {
        Queue<Node> queue = new LinkedList<>();
        boolean [] check = new boolean[n+1];
        queue.add(start);
        int d = 0;
        while (!queue.isEmpty()) {
            int now = queue.peek().x;
            int dist = queue.peek().dist;
            depth[now] = d++;

            queue.poll();

            for(int i=0; i<tree[now].size(); i++) {
                int next = tree[now].get(i).x;
                int nextDist = tree[now].get(i).dist;

                if(!check[next]) {
                    parents[0][next] = now;
                    distanceToNthParents[0][next] = nextDist;
                    check[next] = true;
                    queue.add(tree[now].get(i));
                }
            }
        }
    }
}
