package com.algorithm.DAY08.P11049;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class MatSize {
    int r;
    int c;

    public MatSize (int r, int c) {
        this.r = r;
        this.c = c;
    }
}
public class Main {
    static int n;
    static MatSize [] arr;
    static int [][] dp;

    static int cal(int start, int end) {
        if(dp[start][end] >= 0) return dp[start][end];
        if(start == end) return 0;
        if(start+1 == end) {
            dp[start][end] = arr[start].r * arr[start].c * arr[end].c;
            return dp[start][end];
        }

        for(int i=start; i<=end; i++) {
            int temp = cal(start,i) + cal(i+1, end);
            temp += arr[start].r * arr[i].c * arr[end].c;
            if(dp[start][end] == -1 || dp[start][end] > temp) dp[start][end] = temp;
        }

        return dp[start][end];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());

        arr = new MatSize[n];
        dp = new int[n+1][n+1];

        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                dp[i][j] = -1;
            }
        }

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            arr[i] = new MatSize(r, c);
        }

        // for문으로 계산
        // dp[i][j] : i번 matrix 에서 j번 matrix 까지 곱할 때 최소 연산값
//         for(int i=n-1; i>=1; i--) {
//            for(int j=i+1; j<=n; j++) {
//                dp[i][j] = Integer.MAX_VALUE;
//                for(int k=i; k<=j; k++) {
//                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k+1][j] + arr[i].r * arr[k].c * arr[j].c);
//                }
//            }
//        }

        System.out.println(cal(0, n-1));
    }
}
