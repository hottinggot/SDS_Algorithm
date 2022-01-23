package com.algorithm.DAY01.P1103;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int m;
    static int [][] board;
    static boolean [][] check;
    static int[][] dp;
    static int maxi = 0;
    static boolean repeat = false;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        check = new boolean[n][m];
        dp = new int[n][m];

        for(int i=0; i<n; i++) {
            String temp = br.readLine();
            for(int j=0; j<m; j++) {
                if(temp.charAt(j) != 'H') board[i][j] = temp.charAt(j)-'0';
                else board[i][j] = -1;
            }
        }

        check[0][0] = true;
        go(0, 0, 1);
        if(!repeat) System.out.println(maxi);
        else System.out.println(-1);

    }

    static void go(int y, int x, int move) {


        check[y][x] = true;

        dp[y][x] = move;
        maxi = Math.max(maxi, dp[y][x]);

        for(int i=0; i<4; i++) {
            int my = y + dy[i]*board[y][x];
            int mx = x + dx[i]*board[y][x];

            if(my >= 0 && my <n && mx >=0 && mx < m && !repeat) {
                if(check[my][mx]) {
                    repeat = true;
                    continue;
                }

                if(board[my][mx]==-1) {
                    continue;
                }

                if(dp[my][mx] > move) {
                    continue;
                }

                go(my, mx, move + 1);
            }
        }
        check[y][x] = false;
    }
}
