package com.algorithm.DAY02.P7453;
// 합이 0인 네 정수

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int [] A = new int[n];
        int [] B = new int[n];
        int [] C = new int[n];
        int [] D = new int[n];

        StringTokenizer st;

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
            C[i] = Integer.parseInt(st.nextToken());
            D[i] = Integer.parseInt(st.nextToken());
        }

        int [] sum1 = new int[n*n];
        int [] sum2 = new int[n*n];
        int idx = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                sum1[idx] = A[i] + B[j];
                sum2[idx] = C[i] + D[j];
                idx++;
            }
        }

        Arrays.sort(sum1);
        Arrays.sort(sum2);

        int p1 = 0;
        int p2 = n*n-1;

        long cnt = 0;

        while (p1 < sum1.length && p2 >= 0) {
            int tempSum = sum1[p1] + sum2[p2];
            if(tempSum == 0) {
                long same1 = 0;
                long same2 = 0;
                int temp = sum1[p1];
                while (p1 < sum1.length && temp == sum1[p1]) {
                    p1++;
                    same1++;
                }

                temp = sum2[p2];
                while (p2 >= 0 && temp == sum2[p2]) {
                    p2--;
                    same2++;
                }
                cnt += same1*same2;
            } else if(tempSum > 0) {
                p2--;
            } else {
                p1++;
            }
        }

        System.out.println(cnt);
    }
}
