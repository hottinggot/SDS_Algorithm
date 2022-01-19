package com.algorithm.DAY02.P2805;

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

        st = new StringTokenizer(br.readLine());
        long[] arr = new long[n];
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        long lo = 0;
        long hi = 1000000000;

        long mid;
        long ans = 0;

        while(lo <= hi) {
            mid = (lo+hi)/2;
            long sum = 0;
            for(int i=0; i<n; i++) {
                if(arr[i] <= mid) continue;
                sum += arr[i] - mid;
            }

            if(sum < m) {
                hi = mid - 1;
            } else {
                if(ans < mid) ans = mid;
                lo = mid + 1;
            }
        }

        System.out.println(ans);
    }
}
