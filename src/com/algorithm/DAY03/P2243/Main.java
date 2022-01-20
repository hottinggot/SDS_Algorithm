package com.algorithm.DAY03.P2243;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int s = 1;
    static int n;
    static long [] tree;
    final static int candyNum = 1000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        while(s < candyNum) {
            s *= 2;
        }

        tree = new long[s*2];

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());

            if(cmd == 1) {
                long candyRank = Long.parseLong(st.nextToken());
                int candyKind = queryTD(1, s, 1, candyRank);
                System.out.println(candyKind);
                updateTD(1, s, 1, candyKind, -1);

            } else {
                int candyKind = Integer.parseInt(st.nextToken());
                long diff = Integer.parseInt(st.nextToken());
                updateTD(1, s, 1, candyKind, diff);
            }
        }
    }

    static int queryTD(int left, int right, int node, long query) {

        if(left == right) return left;

        long leftVal = tree[node*2];

        int mid = (left + right)/2;
        if(leftVal >= query) {
            return queryTD(left, mid, node*2, query);
        } else {
            return queryTD(mid+1, right, node*2+1, query - leftVal);
        }
    }

    static void updateTD(int left, int right, int node, int target, long diff) {
        if(target < left || target > right) return;

        else {
            tree[node] += diff;
            if(left!=right) {
                int mid = (left+right)/2;
                updateTD(left, mid, node*2, target, diff);
                updateTD(mid+1, right, node*2+1, target, diff);
            }
        }
    }
}
