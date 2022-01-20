package com.algorithm.DAY03.P10828;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] stack;
    static int p = -1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        stack = new int[n];

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            if(cmd.equals("push")) {
                int num = Integer.parseInt(st.nextToken());
                push(num);
            } else if(cmd.equals("pop")) {
                System.out.println(pop());
            } else if(cmd.equals("top")) {
                System.out.println(top());
            } else if(cmd.equals("size")) {
                System.out.println(size());
            } else if(cmd.equals("empty")) {
                if(empty())
                    System.out.println(1);
                else {
                    System.out.println(0);
                }
            }
        }
    }

    static void push(int num) {
        stack[++p] = num;
    }

    static int pop() {
        int num = -1;
        if(!empty()) {
            num = stack[p];
            p--;
        }
        return num;
    }

    static int size() {
        return p+1;
    }

    static boolean empty() {
        if(p==-1) return true;
        else return false;
    }

    static int top() {
        if(!empty()) return stack[p];
        else return -1;
    }
}
