package com.algorithm.DAY02.P2096;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n][3];

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int [] dp_max = {arr[0][0], arr[0][1], arr[0][2]};
        int [] dp_min = {arr[0][0], arr[0][1], arr[0][2]};


        for(int i=1; i<n; i++) {
            int a = Math.max(dp_max[0], dp_max[1]) + arr[i][0];
            int b = Math.max(Math.max(dp_max[0], dp_max[1]), dp_max[2]) + arr[i][1];
            int c = Math.max(dp_max[1], dp_max[2]) + arr[i][2];

            dp_max[0] = a;
            dp_max[1] = b;
            dp_max[2] = c;

            a = Math.min(dp_min[0], dp_min[1]) + arr[i][0];
            b = Math.min(Math.min(dp_min[0], dp_min[1]), dp_min[2]) + arr[i][1];
            c = Math.min(dp_min[1], dp_min[2]) + arr[i][2];

            dp_min[0] = a;
            dp_min[1] = b;
            dp_min[2] = c;
        }

        System.out.print(Math.max(Math.max(dp_max[0], dp_max[1]), dp_max[2]) + " ");
        System.out.println(Math.min(Math.min(dp_min[0], dp_min[1]), dp_min[2]));

    }
}
