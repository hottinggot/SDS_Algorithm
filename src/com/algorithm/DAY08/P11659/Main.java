package com.algorithm.DAY08.P11659;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int [] nums = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int [] pSum = new int[n];
        pSum[0] = nums[0];

        for(int i=1; i<n; i++) {
            pSum[i] = nums[i] + pSum[i-1];
        }

        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            start--;
            end--;

            if(start == 0) System.out.println(pSum[end]);
            else System.out.println(pSum[end] - pSum[start-1]);
        }
    }
}
