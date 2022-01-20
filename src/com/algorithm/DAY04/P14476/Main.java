package com.algorithm.DAY04.P14476;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int gcd(int a, int b) {
        if(b==0) return a;
        else {
            return gcd(b, a%b);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int [] nums = new int[n];
        for(int i=0; i<n; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int [] gcdASC = new int[n];
        int [] gcdDESC = new int[n];

        gcdASC[0] = nums[0];
        for(int i=1; i<n; i++) {
            int a = gcdASC[i-1];
            int b = nums[i];
            gcdASC[i] = gcd(Math.max(a, b), Math.min(a, b));
        }

        gcdDESC[n-1] = nums[n-1];
        for(int i=n-2; i>=0; i--) {
            int a = gcdDESC[i+1];
            int b = nums[i];
            gcdDESC[i] = gcd(Math.max(a,b), Math.min(a,b));
        }

        int max_gcd = -1;
        int max_idx = 0;
        for(int i=0; i<n; i++) {
            int a = 0;
            int b = 0;
            int result = 0;
            if(i-1 >=0) a = gcdASC[i-1];
            if(i+1 < n) b = gcdDESC[i+1];
            result = gcd(Math.max(a, b), Math.min(a, b));

            if(nums[i] % result == 0) continue;

            if(max_gcd < result) {
                max_gcd = result;
                max_idx = i;
            }

        }

        System.out.print(max_gcd);
        if(max_gcd != -1) System.out.println(" " + nums[max_idx]);

    }
}
