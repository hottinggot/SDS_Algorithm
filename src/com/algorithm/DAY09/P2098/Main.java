package com.algorithm.DAY09.P2098;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n;
    static int [][] weights;
    static int [][] dp;
    static int INF = 16*1000001;
    static int cal(int now, int visited) {

        if(visited == (1<<n)-1) {
            if(weights[now][0] == 0) return INF;
            return weights[now][0];
        }
        if(dp[now][visited] > 0) {
            return dp[now][visited];
        }

        dp[now][visited] = INF;

        for(int i=0; i<n; i++) {
            // 방문하지 않은 도시
            if((visited & (1<<i)) == 0 && weights[now][i] > 0)  {
                int temp = weights[now][i] + cal(i, visited | (1<<i));
                dp[now][visited] = Math.min(dp[now][visited], temp);
            }

        }

        return dp[now][visited];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());

        weights = new int[n+1][n+1];
        dp = new int[n+1][1<<16];

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                weights[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(cal(0, 1));

    }

}
