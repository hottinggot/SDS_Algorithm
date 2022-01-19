package com.algorithm.DAY03.P1655;

import java.io.*;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(st.nextToken());
        //작은수(최대힙)
        PriorityQueue<Integer> smallnum_pq = new PriorityQueue<>(Collections.reverseOrder());

        //큰수(최소힙)
        PriorityQueue<Integer> bignum_pq = new PriorityQueue<>();

        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        smallnum_pq.add(x);
        int mid = x;
        System.out.println(mid);

        for(int i=1; i<n; i++) {

            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            if(num <= mid) {
                smallnum_pq.add(num);
            } else {
                bignum_pq.add(num);
            }

            if(i%2==0 && smallnum_pq.size() < bignum_pq.size()) {
                smallnum_pq.add(bignum_pq.poll());
            }

            else if (i%2==1 && bignum_pq.size() < smallnum_pq.size()) {
                bignum_pq.add(smallnum_pq.poll());
            }
            mid = smallnum_pq.peek();
            bw.write(mid+"\n");
        }
        bw.close();
    }
}
