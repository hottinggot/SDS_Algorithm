package com.algorithm.DAY04.P2960;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        boolean [] check = new boolean[n+1];

        int cnt = 0;
        int ans = 0;
        for(int num = 2; num<=n; num++) {
            if(!check[num]) {
                int temp = num;
                boolean flag = false;
                while (temp <= n) {
                    if(!check[temp]) {
                        check[temp] = true;
                        cnt++;
                        if(cnt == k) {
                            ans = temp;
                            flag = true;
                            break;
                        }
                    }
                    temp += num;
                }
                if(flag) break;
            }
        }
        System.out.println(ans);


    }
}
