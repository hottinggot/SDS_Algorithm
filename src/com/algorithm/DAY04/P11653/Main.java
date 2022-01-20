package com.algorithm.DAY04.P11653;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        ArrayList<Integer> list = new ArrayList<>();


        for(int i=2; i<=n; i++) {
            while (n%i==0) {
                list.add(i);
                n /= i;
            }
        }

        Collections.sort(list);
        for(int i=0; i<list.size(); i++) {
            bw.write(list.get(i) + "\n");
        }
        bw.close();
    }
}
