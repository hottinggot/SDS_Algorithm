package com.algorithm.DAY09.P7578;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

//공장
public class Main {
    static int n;
    static int [] A;
    static HashMap<Integer, Integer> B;
    static long [] tree;
    static int S = 1;
    static long cnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        A = new int[n+1];
        B = new HashMap<>();

        while (S < n) {
            S *= 2;
        }

        tree = new long[S*2];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++) {
            B.put(Integer.parseInt(st.nextToken()), i);
        }

        for(int i=1; i<=n; i++) {
            int index = B.get(A[i]);
            cnt += query(1, S, 1, index+1, 2*S-1);
            update(1, S, 1, index, 1);
        }

        System.out.println(cnt);

    }

    static long query(int left, int right, int node, int queryLeft, int queryRight) {
        if(queryRight < left || queryLeft > right) return 0;

        else if(queryLeft <= left && queryRight >= right) {
            return tree[node];
        }

        else {
            int mid = (left + right) / 2;
            long leftVal = query(left, mid, node*2, queryLeft, queryRight);
            long rightVal = query(mid+1, right, node*2+1, queryLeft, queryRight);
            return leftVal + rightVal;
        }
    }

    static void update(int left, int right, int node, int target, long diff) {
        if(node >= 2*S) return;

        if(target < left || target > right) return;

        tree[node] += diff;

        int mid = (left + right) / 2;
        update(left, mid, node*2, target, diff);
        update(mid+1, right, node*2+1, target, diff);
    }
}
