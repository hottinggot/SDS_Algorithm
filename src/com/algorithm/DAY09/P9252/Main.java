package com.algorithm.DAY09.P9252;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

//LCS 2
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s1 = br.readLine();
        String s2 = br.readLine();

        int [][] dp = new int[s1.length()+1][s2.length()+1];

        for(int i=1; i<=s1.length(); i++) {
            for(int j=1; j<=s2.length(); j++) {
                if(s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j]);
                    dp[i][j] = Math.max(dp[i][j], dp[i][j-1]);
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        System.out.println(dp[s1.length()][s2.length()]);
        Stack<Integer> stack = new Stack<>();

        int y = s1.length();
        int x = s2.length();

        while (x>0 && y>0) {

            if(dp[y][x] == dp[y-1][x]) {
                y--;
            } else if (dp[y][x] == dp[y][x-1]) {
                x--;
            } else if(dp[y][x] == dp[y-1][x-1]) {
                x--;
                y--;
            } else {
                stack.add(x);
                x--;
                y--;
            }
        }

        while (!stack.empty()) {
            int idx = stack.pop();
            System.out.print(s2.charAt(idx-1));
        }
    }
}
