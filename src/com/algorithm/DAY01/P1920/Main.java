package com.algorithm.DAY01.P1920;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int [] arr = new int[n];

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<m; i++) {
            int num = Integer.parseInt(st.nextToken());
            int lo = 0;
            int hi = n-1;
            int mid;
            boolean isExist = false;

            while (lo <= hi) {
                mid = (lo + hi)/2;
                if(arr[mid] < num) {
                    lo = mid + 1;
                } else if(arr[mid] > num) {
                    hi = mid - 1;
                } else {
                    isExist = true;
                    break;
                }
            }

            if(isExist) System.out.println(1);
            else System.out.println(0);
        }
    }
}
