package com.algorithm.DAY08.P12738;

// 가장 긴 증가하는 부분 수열 3
// 14003을 풀기 위한 연습용

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        List<Integer> dp = new ArrayList<>();

        dp.add(Integer.parseInt(st.nextToken()));

        for(int i=1; i<n; i++) {
            int num = Integer.parseInt(st.nextToken());
            if(num > dp.get(dp.size()-1)) dp.add(num);
            else {
                int lo = 0;
                int hi = dp.size()-1;
                int mid;
                int findingIdx = 0;
                while(lo <= hi) {
                    mid = (lo+hi)/2;

                    if(dp.get(mid) >= num) {
                        findingIdx = mid;
                        hi = mid-1;
                    } else {
                        lo = mid+1;
                    }
                }

                dp.set(findingIdx, num);
            }
        }

        System.out.println(dp.size());
    }
}
