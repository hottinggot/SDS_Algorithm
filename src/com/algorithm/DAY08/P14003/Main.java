package com.algorithm.DAY08.P14003;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        List<Integer> dp = new ArrayList<>();
        int [] originalArr = new int[n];
        int [] LISIdx = new int[n];

        dp.add(Integer.parseInt(st.nextToken()));
        originalArr[0] = dp.get(0);
        LISIdx[0] = 0;

        for(int i=1; i<n; i++) {
            int num = Integer.parseInt(st.nextToken());
            originalArr[i] = num;
            if(num > dp.get(dp.size()-1)) {
                dp.add(num);
                LISIdx[i] = dp.size()-1;
            } else {
                int lo = 0;
                int hi = dp.size()-1;
                int mid;
                int findingIdx = 0;

                while (lo <= hi) {
                    mid = (lo+hi)/2;
                    if(dp.get(mid) >= num) {
                        findingIdx = mid;
                        hi = mid - 1;
                    } else {
                        lo = mid + 1;
                    }
                }

                dp.set(findingIdx, num);
                LISIdx[i] = findingIdx;
            }
        }

        System.out.println(dp.size());

        Stack<Integer> LIS = new Stack<>();

        int LISSize = dp.size()-1;

        for(int i=n-1; i>=0; i--) {
            if(LISIdx[i] == LISSize) {
                LIS.add(originalArr[i]);
                LISSize--;
            }
        }

        while (!LIS.isEmpty()) {
            System.out.print(LIS.pop() + " ");
        }
    }
}
