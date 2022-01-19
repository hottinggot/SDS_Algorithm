package com.algorithm.DAY02.P1072;

import java.awt.print.Pageable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        double x = Double.parseDouble(st.nextToken());
        double y = Double.parseDouble(st.nextToken());

        int lo = 1;
        int hi = 1000000000;

        int mid;
        double originalZ = Math.floor(y*100/x);

        if(originalZ >= 99) {
            System.out.println(-1);
            return;
        }

        while(lo <= hi) {
            mid = (lo+hi)/2;
            double z = Math.floor((y+(double)mid)*100/(x+(double)mid));
            if(originalZ < z) {
                hi = mid-1;
            } else {
                lo = mid+1;
            }
        }
        System.out.println(lo);
    }
}
