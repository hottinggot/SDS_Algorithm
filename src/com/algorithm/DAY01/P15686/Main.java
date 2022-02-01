package com.algorithm.DAY01.P15686;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int INF = Integer.MAX_VALUE;
    static int [] dy = {-1, 1, 0, 0};
    static int [] dx = {0, 0, -1, 1};
    static int n;
    static int m;
    static int [][] map;
    static boolean [][] check;
    static int minDist = INF;

    static int calChickenDist() {
        int sum = 0;
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=n; j++) {
                if(map[i][j]==1) {
                    int tempChickenDist = INF;
                    for(int u=1; u<=n; u++) {
                        for(int v=1; v<=n; v++) {
                            if(check[u][v]) {
                                tempChickenDist = Math.min(tempChickenDist, Math.abs(i-u) + Math.abs(j-v));
                            }
                        }
                    }
                    sum += tempChickenDist;
                }
            }
        }
        return sum;
    }

    static void dfs(int y, int x, int cnt) {
        if(cnt == m) {
            int temp = calChickenDist();
            minDist = Math.min(minDist, temp);
            return;
        }

        for(int i=y; i<=n; i++) {
            for(int j=0; j<=n; j++) {
                if(i==y && j<x) continue;
                if(map[i][j] == 2 && !check[i][j]) {
                    check[i][j] = true;
                    dfs(i, j , cnt+1);
                    check[i][j] = false;
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n+1][n+1];
        check = new boolean[n+1][n+1];

        for(int i=1; i<=n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(1, 1, 0);
        System.out.println(minDist);

    }
}
