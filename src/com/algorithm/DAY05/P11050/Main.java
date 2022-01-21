package com.algorithm.DAY05.P11050;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] pascal = new int[11][11];

        for(int i=0; i<11; i++) {
            for(int j=0; j<=i; j++) {
                if(j==0 || i==j) {
                    pascal[i][j] = 1;
                    continue;
                }

                pascal[i][j] = pascal[i-1][j-1] + pascal[i-1][j];
            }
        }
        System.out.println(pascal[n][k]);
    }
}
