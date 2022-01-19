package com.algorithm.DAY02.P2003;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine()," ");
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int lo = 0;
        int hi = 0;

        int cnt = 0;
        int sum = arr[0];
        while (hi < n && lo < n) {
            if(sum < m) {
                hi++;
                if(hi >= n) break;
                sum += arr[hi];
            } else if(sum > m) {
                sum -= arr[lo];
                lo++;
            } else {
                cnt++;
                sum -= arr[lo];
                lo++;
            }
        }

        System.out.println(cnt);
    }
}
