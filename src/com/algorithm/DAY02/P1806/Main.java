package com.algorithm.DAY02.P1806;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n+1];

        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int lo =0, hi = 0;
        int sum = arr[0];
        int minLen = 0;

        while(hi < n && lo < n) {
            if(sum >=s) {
                if(minLen == 0 || hi - lo + 1 < minLen) minLen = hi - lo + 1;
                sum -= arr[lo++];
            } else {
                sum += arr[++hi];
            }
        }

        System.out.println(minLen);
    }
}
