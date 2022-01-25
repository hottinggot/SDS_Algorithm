package com.algorithm.DAY07.P11404;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static final int INF = (int) 1e7;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int [][] matrixGraph = new int[n+1][n+1];

        for(int i=0; i<=n; i++) {
            for(int j=0; j<=n; j++) {
                if(i!=j) matrixGraph[i][j] = INF;
            }
        }

        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());


            if(c < matrixGraph[a][b]) matrixGraph[a][b] = c;
        }

        for(int k=1; k<=n; k++) {
            for(int i=1; i<=n; i++) {
                for(int j=1; j<=n; j++) {
                    matrixGraph[i][j] = Math.min(matrixGraph[i][j], matrixGraph[i][k] + matrixGraph[k][j]);
                }
            }
        }

        for(int i=1; i<=n; i++) {
            for(int j=1; j<=n; j++) {
                if(matrixGraph[i][j] == INF) System.out.print(0 + " ");
                else System.out.print(matrixGraph[i][j] + " ");
            }
            System.out.println();
        }
    }
}
