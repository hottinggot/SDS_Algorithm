package com.algorithm.DAY09.P2342;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = 0;
        int [] direction = new int[100001];

        for(int i=1; ; i++) {
            int x = Integer.parseInt(st.nextToken());
            if(x==0) {
                n = i-1;
                break;
            }
            direction[i] = x;
        }


        int[][][] dp = new int[n+1][5][5];

        for(int i=1; i<=n; i++) {
            for(int j=0; j<5; j++) {
                for(int k=0; k<5; k++) {
                    dp[i][j][k] = INF;
                }
            }
        }

        dp[1][0][direction[1]] = 2;
        dp[1][direction[1]][0] = 2;

        int next;
        for(int i=1; i<n; i++) {
            for(int j=0; j<5; j++) {
                for(int k=0; k<5; k++) {
                    if(dp[i][j][k]!=INF) {
                        next = direction[i+1];
                        if(k!=next) {
                            dp[i+1][next][k] = Math.min(dp[i+1][next][k], dp[i][j][k] + getCost(j, next));
                        }

                        if(j!=next) {
                            dp[i+1][j][next] = Math.min(dp[i+1][j][next], dp[i][j][k] + getCost(k, next));
                        }
                    }

                }
            }
        }

        int answer = INF;

        for(int j=0; j<5; j++) {
            for(int k=0; k<5; k++) {
                answer = Math.min(answer, dp[n][j][k]);
            }
        }

        System.out.println(answer);

    }

    static int getCost (int from, int to){
        if(from == to) {
            return 1;
        } else if(from == 0) {
            return 2;
        } else if (Math.abs(from - to) == 2) {
            return 4;
        } else {
            return 3;
        }
    }
}
