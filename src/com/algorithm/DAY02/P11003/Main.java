package com.algorithm.DAY02.P11003;

import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Num {
    int value;
    boolean deleted;

    public Num(int value) {
        this.value = value;
        this.deleted = false;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        Num [] A = new Num[n+1];
        PriorityQueue<Num> pq = new PriorityQueue<>(new Comparator<Num>() {
            @Override
            public int compare(Num o1, Num o2) {
                return o1.value - o2. value;
            }
        });

        st = new StringTokenizer(br.readLine());

        A[0] = new Num( 0);
        for(int i=1; i<l; i++) {
            A[i] = new Num(Integer.parseInt(st.nextToken()));
            pq.add(A[i]);
            bw.write(pq.peek().value + " ");
        }

        for(int i=l; i<=n; i++) {
            A[i-l].deleted = true;
            A[i] = new Num(Integer.parseInt(st.nextToken()));
            pq.add(A[i]);

            while (pq.peek().deleted) {
                pq.poll();
            }
            bw.write(pq.peek().value + " ");
        }

        bw.flush();
        bw.close();
    }
}
