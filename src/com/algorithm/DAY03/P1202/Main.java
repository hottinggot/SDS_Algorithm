package com.algorithm.DAY03.P1202;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Jewelry {
        int m;
        int v;

        public Jewelry (int m, int v) {
            this.m = m;
            this.v = v;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Jewelry[] jewelries = new Jewelry[n];

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            jewelries[i] = new Jewelry(m, v);
        }

        int [] bags = new int[k];

        for(int i=0; i<k; i++) {
            st = new StringTokenizer(br.readLine());
            bags[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(bags);
        Arrays.sort(jewelries, new Comparator<Jewelry>() {
            @Override
            public int compare(Jewelry o1, Jewelry o2) {
                return o1.m - o2.m;
            }
        });

        PriorityQueue<Jewelry> pq = new PriorityQueue<>(new Comparator<Jewelry>() {
            @Override
            public int compare(Jewelry o1, Jewelry o2) {
                return o2.v - o1.v;
            }
        });

        long cnt = 0;
        int index = 0;

        for(int i=0; i<k; i++) {
            for(; index<n; index++) {
                if(jewelries[index].m <= bags[i]) {
                    pq.add(jewelries[index]);
                } else break;
            }
            if(!pq.isEmpty()) {
                cnt += pq.peek().v;
                pq.poll();
            }
        }

        System.out.println(cnt);
    }
}
