package com.algorithm.DAY05.P1722;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 순열의 순서
public class Main {
    static List<Integer> list = new ArrayList<>();
    static long[] dp = new long[21];
    static int N;
    static boolean [] check = new boolean[21];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int type = Integer.parseInt(st.nextToken());

        if(type==1) {
            long x = Long.parseLong(st.nextToken());
            query(x, N);
            for(int i=0; i<list.size(); i++) {
                System.out.print(list.get(i) + " ");
            }
            System.out.println();

        } else if(type==2) {
            int []arr = new int[N];
            long cnt = 0;
            int tempN = N-1;
            for(int i=0; i<N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                int numBefore = 0;
                for(int j=1; j<=N; j++) {
                    if(!check[j]) {
                        if(j==arr[i]) {
                            check[j] = true;
                            break;
                        }
                        numBefore++;
                    }
                }
                cnt += numBefore*factorial(tempN);
                tempN--;
            }
            cnt++;
            System.out.println(cnt);
        }
    }

    static void query(long x, int n) {
        if(n==0) {
            return;
        }
        int cnt = 1;
        for(int i=1; i<=N; i++) {
            if(check[i]) continue;
            if(cnt*factorial(n-1) >= x) {
                list.add(i);
                check[i] = true;
                query(x-(cnt-1)*factorial(n-1), n-1);
            } else {
                cnt++;
            }
        }
    }

    static long factorial(int n) {
        if(dp[n] > 0) return dp[n];
        if(n==0 || n==1) return 1;
        else return dp[n] = n * factorial(n-1);
    }
}
