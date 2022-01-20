//package com.algorithm.DAY03.P10845;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.StringTokenizer;
//
//public class Main {
//    static int [] queue;
//    static int p = -1;
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        int n = Integer.parseInt(st.nextToken());
//        queue = new int[n];
//
//        for(int i=0; i<n; i++) {
//            st = new StringTokenizer(br.readLine());
//            String cmd = st.nextToken();
//
//            if(cmd.equals("push")) {
//
//            } else if(cmd.equals("pop")) {
//
//            } else if(cmd.equals("size")) {
//
//            } else if(cmd.equals("empty")) {
//
//            } else if(cmd.equals("front")) {
//
//            } else if(cmd.equals("back")) {
//
//            }
//        }
//    }
//    static void push(int num) {
//        queue[++p] = num;
//    }
//
//    static int pop() {
//        int num = queue[0];
//        for(int i=1; i<=p; i++) {
//            queue[i-1] = queue[i];
//        }
//        p--;
//        return num;
//    }
//
//    static int size() {
//        if(rear == -1) return -1;
//        else return rear - front;
//    }
//
//    static int empty() {
//
//    }
//
//    static int front() {
//
//    }
//
//    static int back() {
//
//    }
//
//}
