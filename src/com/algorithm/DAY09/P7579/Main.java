package com.algorithm.DAY09.P7579;

import java.awt.print.Pageable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 앱
public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int [] M = new int[n+1];
        int [] C = new int[n+1];

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++) {
            M[i] = Integer.parseInt(st.nextToken());
        }

        int costMax = 0;
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++) {
            C[i] = Integer.parseInt(st.nextToken());
            costMax += C[i];
        }

        costMax += 1;

        int [][] dp = new int[n+1][costMax];

        for(int i=1; i<=n; i++) {
            for(int j=0; j<costMax; j++) {
                dp[i][j] = dp[i-1][j];
                if(j >= C[i]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j-C[i]] + M[i]);
                }
            }
        }

        for(int i=1; i<costMax; i++) {
            if(dp[n][i] >=m ) {
                System.out.println(i);
                break;
            }
        }

    }
}
