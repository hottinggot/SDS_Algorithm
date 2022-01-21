package com.algorithm.DAY05.P1256;
//사전
import java.awt.print.Pageable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static long[][] pascal = new long[201][201];
    static StringBuilder sb = new StringBuilder();
    static int maxK = 1000000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        long k = Long.parseLong(st.nextToken());

        pascal[0][0] = 0;
        for(int i=1; i<201; i++) {
            for(int j=0; j<=i; j++) {
                if(j==0) {
                    pascal[i][j] = 1;
                    continue;
                }

                if(j==i) {
                    pascal[i][j] = 1;
                    continue;
                }

                if(pascal[i-1][j-1] + pascal[i-1][j] >= maxK)
                    pascal[i][j] = maxK;
                else
                    pascal[i][j] = pascal[i-1][j-1] + pascal[i-1][j];
            }
        }

        if(pascal[n+m][m] < k) {
            System.out.println(-1);
            return;
        }

        query(n, m, k);
        System.out.println(sb.toString());
    }

    static void query(int n, int m, long k) {
        if(n+m == 1) {
            if(n==1) sb.append('a');
            else sb.append('z');
            return;
        }
        int x = n+m;
        if(k <= pascal[x-1][m]) {
            sb.append('a');
            query(n-1, m, k);
        } else {
            sb.append('z');
            query(n, m-1, k-pascal[x-1][m]);
        }
    }
}
