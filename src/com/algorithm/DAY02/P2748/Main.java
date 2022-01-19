package com.algorithm.DAY02.P2748;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        long[] num = new long[91];
        num[0] = 0;
        num[1] = 1;

        for(int i=2; i<=n; i++) {
            num[i] = num[i-1] + num[i-2];
        }
        System.out.println(num[n]);
    }
}
