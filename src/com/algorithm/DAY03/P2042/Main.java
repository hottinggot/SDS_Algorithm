package com.algorithm.DAY03.P2042;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int m;
    static int k;
    static int s = 1;
    static long[] num;
    static long[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()) ;

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        num = new long[n+1];
        for(int i=1; i<=n; i++) {
            st = new StringTokenizer(br.readLine());
            num[i] = Long.parseLong(st.nextToken());
        }


        while(s<n) {
            s *= 2;
        }

        tree = new long[s*2];

        init(1, s, 1);
//        intiBU();

        for(int i=0; i<k+m; i++) {

            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if(a==1) {
                updateBU(b, c);
            } else if(a==2) {
//                System.out.println(queryBU(b,c));
                System.out.println(query(1, s, 1, b, (int)c));
            }
        }
    }

    static long init(int left, int right, int node) {
        if(left == right) {
            if(left > n) return tree[node] = 0;
            return tree[node] = num[left];
        }
        int mid = (left + right)/2;
        tree[node] = init(left, mid, node*2) + init(mid+1, right, node*2+1);

        return tree[node];
    }

    static void intiBU() {
        for(int i=s; i<2*s; i++) {
            if(i<s+n) tree[i] = num[i-s+1];
            else tree[i] = 0;
        }

        for(int i = s-1; i>0; i--) {
            tree[i] = tree[i*2] + tree[i*2+1];
        }
    }

    static long query(int left, int right, int node, int queryLeft, int queryRight) {
        if(queryRight < left || queryLeft > right) {
            return 0;
        }

        else if(queryLeft <= left && queryRight >= right) {
            return tree[node];
        }

        else {
            int mid = (left + right)/2;
            long leftVal = query(left, mid, node*2, queryLeft, queryRight);
            long rightVal = query(mid+1, right, node*2+1, queryLeft, queryRight);
            return leftVal + rightVal;
        }
    }

    static long queryBU(int queryLeft, int queryRight) {
        long result = 0;
        int left = s+queryLeft-1;
        int right = s+queryRight-1;

        while(left <= right) {
            if(left%2==1) {
                result += tree[left];
                left ++;
            }

            if(right==0) {
                result += tree[right];
                right--;
            }

            left /= 2;
            right /= 2;
        }
        return result;
    }

    static void update(int left, int right, int node, int target, long diff) {
        if(node >= 2*s) return;

        if(target < left || target > right) return;

        else if(target >= left && target <=right) {
            tree[node] += diff;
        }

        int mid = (left + right)/2;
        update(left, mid, node*2, target, diff);
        update(mid+1, right, node*2+1, target, diff);

    }

    static void updateBU(int target, long value) {
        int current = s+target-1;
        tree[current] = value;

        while(current > 0) {
            current /= 2;
            tree[current] = tree[current*2] + tree[current*2 + 1];
        }

    }
}
