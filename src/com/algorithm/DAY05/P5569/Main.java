package com.algorithm.DAY05.P5569;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
    static int w;
    static int h;
    static int [][][][] dp;
    static int mod = 100000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        dp = new int[h+2][w+2][2][2];


        for(int i=1; i<=w; i++) {
            dp[1][i][0][1] = 1;
        }

        for(int i=1; i<=h; i++) {
            dp[i][1][1][1] = 1;
        }

        //dp[세로][가로][전에 온 방향(0: 왼쪽, 1: 아래쪽)][이번에 코너 돌 수 있는지(0: 불가, 1: 가능)]
        for(int i=2; i<=h; i++) {
            for(int j=2; j<=w; j++) {
                dp[i][j][0][0] = dp[i][j-1][1][1];
                dp[i][j][1][0] = dp[i-1][j][0][1];
                dp[i][j][0][1] = (dp[i][j-1][0][0]%mod + dp[i][j-1][0][1]%mod)%mod;
                dp[i][j][1][1] = (dp[i-1][j][1][0]%mod + dp[i-1][j][1][1]%mod)%mod;
            }
        }
        System.out.println((dp[h][w][0][0] + dp[h][w][0][1] + dp[h][w][1][0] + dp[h][w][1][1])%mod);

    }
}
