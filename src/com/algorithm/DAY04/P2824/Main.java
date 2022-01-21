package com.algorithm.DAY04.P2824;

import java.awt.print.Pageable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int mod = 1000000000;
    public static long gcd(int a, int b) {
        if(b==0) return a;
        else {
            return gcd(b, a%b);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] a = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());

        int[] b = new int[m];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<m; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }

        boolean flag = false;

        long ans = 1;
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                long g;
                if(a[i] < b[j]) {
                    g = gcd(b[j], a[i]);
                } else {
                    g = gcd(a[i], b[j]);
                }
                ans *= g;
                if(ans >= mod) {
                    ans %= mod;
                    flag = true;
                }

                a[i] /= g;
                b[j] /= g;
            }
        }

        if(!flag) {
            System.out.println(ans);
        } else {
            System.out.println(String.format("%09d", ans));
        }

    }
}
