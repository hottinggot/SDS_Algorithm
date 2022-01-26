package com.algorithm.DAY08.P11660;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int [][] square = new int[n][n];

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                square[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int [][] psum = new int[n][n];

        for(int i=0; i<n; i++) {
            psum[i][0] = square[i][0];
        }

        for(int i=0; i<n; i++) {
            for(int j=1; j<n; j++) {
                psum[i][j] = psum[i][j-1] + square[i][j];
            }
        }

        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());

            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            int sum = 0;

            x1--;
            y1--;
            x2--;
            y2--;

            for(int j=x1; j<=x2; j++) {
                int temp = 0;
                if(y1>=1) temp = psum[j][y1-1];
                sum += psum[j][y2] - temp;
            }

            bw.write(sum + "\n");
        }

        bw.flush();
        bw.close();
    }
}
