package com.algorithm.DAY01.P1065;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int cnt = 0;
        for(int i=1; i<=n; i++) {
            int k = i;
            boolean flag = true;
            int smaller = 0;
            int bigger = 0;
            int cha = 100;
            while(k!=0) {
                smaller = k%10;
                bigger = (k/10)%10;
                if(k/10 != 0) {
                    if(cha == 100) cha = bigger-smaller;
                    else {
                        if(bigger-smaller != cha) {
                            flag = false;
                            break;
                        }
                    }
                }
                k /= 10;
            }
            if(flag) cnt++;
        }
        System.out.println(cnt);
    }
}
