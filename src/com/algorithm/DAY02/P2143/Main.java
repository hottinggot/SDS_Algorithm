package com.algorithm.DAY02.P2143;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long T = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        long [] A = new long[n];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        long [] B = new long[m];
        for(int i=0; i<m; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        int N;
        int M;

        if(n%2==0) N = (n+1)*n/2;
        else N = (n+1)*(n/2) + (n+1)/2;

        if(m%2==0) M = (m+1)*m/2;
        else M = (m+1)*(m/2) + (m+1)/2;

        long [] A_sub = new long[N];
        long [] B_sub = new long[M];

        int A_index = 0;
        A_sub[0] = A[0];
        for(int i=1; i<n; i++) {
            A_sub[i] = A_sub[i-1] + A[i];
        }
        A_index = n;
        for(int i=1; i<n; i++) {
            for(int j=i; j<n; j++) {
                A_sub[A_index++] = A_sub[j] - A_sub[i-1];
            }
        }

        int B_index = 0;
        B_sub[0] = B[0];
        for(int i=1; i<m; i++) {
            B_sub[i] = B_sub[i-1] + B[i];
        }
        B_index = m;
        for(int i=1; i<m; i++) {
            for(int j=i; j<m; j++) {
                B_sub[B_index++] = B_sub[j] - B_sub[i-1];
            }
        }

        Arrays.sort(A_sub);
        Arrays.sort(B_sub);

        int a_pointer = 0;
        int b_pointer = M-1;
        long cnt = 0;

        while (a_pointer < N && b_pointer >=0) {
            long currentA = A_sub[a_pointer];
            long target = T - currentA;

            //currentB == target -> subA, subB 같은 수 갯수 체크
            if(B_sub[b_pointer] == target) {
                long countA = 0;
                long countB = 0;

                while (a_pointer < N && A_sub[a_pointer] == currentA) {
                    countA ++;
                    a_pointer ++;
                }

                while (b_pointer >= 0 && B_sub[b_pointer] == target) {
                    countB ++;
                    b_pointer --;
                }

                cnt += (countA * countB);
            }
            //currentB > target -> b_pointer 줄임
            else if(B_sub[b_pointer] > target) {
                b_pointer--;
            }
            //currentB < target -> a_pointer 올림
            else {
                a_pointer++;
            }

        }
        System.out.println(cnt);
    }
}
