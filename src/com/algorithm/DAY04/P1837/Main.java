package com.algorithm.DAY04.P1837;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String P = st.nextToken();
        int k = Integer.parseInt(st.nextToken());

        boolean[] nums = new boolean[k+1];

        for(int i=2; i<=k; i++) {
            if(!nums[i]) {
                int idx = 2*i;
                while(idx <= k) {
                    if(!nums[idx]) {
                        nums[idx] = true;
                    }
                    idx += i;
                }
            }
        }

        for(int i=2; i<k; i++) {
            if(!nums[i]) {
                if(moduler(P, i)==0) {
                    System.out.println("BAD " + i);
                    return;
                }
            }
        }
        System.out.println("GOOD");

    }

    static int moduler(String num, int primeNum) {
        StringBuilder sb = new StringBuilder();
        int modNum = -1;
        for(int i=0; i<num.length(); i++) {
            sb.append(num.charAt(i));
            while(Integer.parseInt(sb.toString()) < primeNum && i+1 < num.length()) {
                sb.append(num.charAt(++i));
            }
            int tempInt = Integer.parseInt(sb.toString());
            sb.delete(0, sb.length());
            modNum = tempInt % primeNum;
            sb.append(modNum);
        }
        return modNum;
    }
}
