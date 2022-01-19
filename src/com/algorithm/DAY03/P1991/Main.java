package com.algorithm.DAY03.P1991;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Node {
    int left;
    int right;

    public Node(int left, int right) {
        this.left = left;
        this.right = right;
    }
}
public class Main {
    static Node[] tree = new Node[30];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            char c1 = st.nextToken().charAt(0);
            int current = c1-'A';

            tree[current] = new Node(-1, -1);

            char c2 = st.nextToken().charAt(0);
            int left = c2-'A';
            tree[current].left = left;

            char c3 = st.nextToken().charAt(0);
            int right = c3-'A';
            tree[current].right = right;

        }

        pre_order(0);
        System.out.println();

        in_order(0);
        System.out.println();

        post_order(0);
        System.out.println();
    }

    static void pre_order(int root) {
        if(root < 0) return;
        System.out.print((char)(root+'A'));
        pre_order(tree[root].left);
        pre_order(tree[root].right);
    }

    static void in_order(int root) {
        if(root < 0) return;
        in_order(tree[root].left);
        System.out.print((char)(root+'A'));
        in_order(tree[root].right);
    }

    static void post_order(int root) {
        if(root < 0) return;
        post_order(tree[root].left);
        post_order(tree[root].right);
        System.out.print((char)(root+'A'));
    }
}
