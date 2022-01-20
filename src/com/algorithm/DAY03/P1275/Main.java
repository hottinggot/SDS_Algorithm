package com.algorithm.DAY03.P1275;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int q;
    static long[] tree;
    static int s = 1;
    static long[] nums;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        nums = new long[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            nums[i] = Long.parseLong(st.nextToken());
        }

        while (s < n) {
            s *= 2;
        }

        tree = new long[s*2];
        init();

        for(int i=0; i<q; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            long b = Integer.parseInt(st.nextToken());

            long ans = query(1, s, 1, Math.min(x, y), Math.max(x, y));
            System.out.println(ans);
            update(1, s, 1, a, b-tree[s+a-1]);
        }
    }

    static void init() {
        for(int i=0; i<n; i++) {
            tree[s+i] = nums[i];
        }

        for(int i=s-1; i>0; i--) {
            tree[i] = tree[i*2] + tree[i*2+1];
        }
    }

    static long query(int left, int right, int node, int queryLeft, int queryRight) {
        if(queryRight < left || queryLeft > right) return 0;

        else if(queryLeft <= left && queryRight >= right) return tree[node];

        else {
            int mid = (left + right)/2;
            long leftVal = query(left, mid, node*2, queryLeft, queryRight);
            long rightVal = query(mid+1, right, node*2+1, queryLeft, queryRight);

            return leftVal + rightVal;
        }
    }

    static void update(int left, int right, int node, int target, long diff) {
        if(target < left || target > right) return;

        else {
            tree[node] += diff;
            if(left!=right) {
                int mid = (left+right)/2;
                update(left, mid, node*2, target, diff);
                update(mid+1, right, node*2+1, target, diff);
            }
        }
    }
}
