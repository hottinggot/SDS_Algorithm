package com.algorithm.DAY04.P1735;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int gcd (int a, int b) {
        if(b==0) {
            return a;
        } else {
            return gcd(b, a%b);
        }
    }

    static int lcm(int a, int b) {
        int gc = gcd(a, b);
        return gc * (a/gc) * (b/gc);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // X/A
        int X = Integer.parseInt(st.nextToken());
        int A = Integer.parseInt(st.nextToken());

        // Y/B
        st = new StringTokenizer(br.readLine());
        int Y = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int mo = lcm(A, B);
        int ja1 = X*(mo/A);
        int ja2 = Y*(mo/B);
        int ja = ja1 + ja2;

        int gc = gcd(mo, ja);
        System.out.println(ja/gc + " " + mo/gc);


    }
}
