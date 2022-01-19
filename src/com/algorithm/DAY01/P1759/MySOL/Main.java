package com.algorithm.DAY01.P1759.MySOL;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static char[] cipher = new char[16];
    static char[] moum = {'a', 'e', 'i', 'o', 'u'};

    static void dfs(int l, int c, int arrIdx, int cipherIdx, char[] arr) {
        if(cipherIdx==l) {
            int cnt = 0;
            String str = String.valueOf(cipher);
            for(int i=0; i<str.length(); i++) {
                for(int j=0; j<moum.length; j++) {
                    if(str.charAt(i)==moum[j]) cnt++;
                }
            }
            if(cnt>=1 && l-cnt>=2) {
                System.out.println(str.substring(0, l));
            }
            return;
        }
        if(arrIdx >= c) return;

        for(int i=arrIdx; i<c; i++) {
            cipher[cipherIdx] = arr[i];
            dfs(l, c, i+1, cipherIdx+1, arr);
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int l = scanner.nextInt();
        int c = scanner.nextInt();
        char[] arr = new char[c];
        for(int i=0; i<c; i++) {
            String temp = scanner.next();
            arr[i] = temp.charAt(0);
        }
        Arrays.sort(arr);

        dfs(l, c, 0, 0, arr);
    }
}
