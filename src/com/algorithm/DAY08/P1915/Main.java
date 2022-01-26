package com.algorithm.DAY08.P1915;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int [][] arr;
    static int [][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        dp = new int[n][m];

        for(int i=0; i<n; i++) {
            String temp = br.readLine();
            for(int j=0; j<m; j++) {
                arr[i][j] = Integer.parseInt(Character.toString(temp.charAt(j)));
                dp[i][j] = arr[i][j];
            }
        }

        int length = Math.min(n, m);

        for(int k=2; k<=length; k++) {

            for(int i=0; i<=n-k; i++) {
                for(int j=0; j<=m-k; j++) {
                    int val = k-1;
                    if(dp[i][j] == val && dp[i+1][j] == val && dp[i][j+1] == val && dp[i+1][j+1] == val) {
                        dp[i][j] = k;
                    }
                }
            }
        }

        int maxi = 0;

        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                maxi = Math.max(maxi, dp[i][j]);
            }
        }

        System.out.println(maxi*maxi);

    }
}
