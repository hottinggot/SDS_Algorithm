package com.algorithm.DAY01.P1339;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 단어 수학
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int [] alphabet = new int[26];
        for(int i=0; i<n; i++) {
            String str = br.readLine();
            for(int j=0; j<str.length(); j++) {
                alphabet[str.charAt(j) - 'A'] += Math.pow(10, str.length() - j - 1);
            }
        }

        Arrays.sort(alphabet);
        int sum = 0;

        int k = 9;
        for(int i=alphabet.length-1; i>=0; i--) {
            sum += alphabet[i] * k--;
            if(alphabet[i]==0) break;
        }

        System.out.println(sum);
    }
}

