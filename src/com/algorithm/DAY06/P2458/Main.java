package com.algorithm.DAY06.P2458;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        boolean [][] graph = new boolean[n+1][n+1];

        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a][b] = true;
        }

        for(int k=1; k<=n; k++) {
            for(int i=1; i<=n; i++) {
                for(int j=1; j<=n; j++) {
                    if(i==j) continue;

                    if(graph[i][k] && graph[k][j]) graph[i][j] = true;
                }
            }
        }

        int ans = 0;
        for(int k=1; k<=n; k++) {
            int cnt = 0;
            for(int i=1; i<=n; i++) {
                if(graph[k][i]) cnt++;
            }

            for(int i=1; i<=n; i++) {
                if(graph[i][k]) cnt++;
            }

            if(cnt == n-1) ans++;
        }

        System.out.println(ans);

    }
}
