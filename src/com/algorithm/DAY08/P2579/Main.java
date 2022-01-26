package com.algorithm.DAY08.P2579;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int n;
    static int [] score;
    static int [] dp;

    // cnt : 연속으로 밟은 계단 수

   static int cal(int num) {
        if(num<=0) return 0;
        if(dp[num] > 0) return dp[num];

        int maxi = 0;

        maxi = Math.max(maxi, cal(num-2));
        maxi = Math.max(maxi, cal(num-3) + score[num-1]);

        dp[num] = maxi + score[num];
        return dp[num];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        score = new int[n+1];
        dp = new int[n+1];

        for(int i=1; i<=n; i++) {
            score[i] = Integer.parseInt(br.readLine());
        }

        System.out.println(cal(n));
    }
}
