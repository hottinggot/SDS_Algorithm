//package com.algorithm.DAY03.P2042_Example;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.StringTokenizer;
//
//public class Main {
//    static int S;
//    static int N;
//    static int M;
//    static int K;
//    static long[] tree;
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//
//        N = Integer.parseInt(st.nextToken());
//        M = Integer.parseInt(st.nextToken());
//        K = Integer.parseInt(st.nextToken());
//
//        long[] nums = new long[N];
//
//
//        for(int i=0; i<N ; i++) {
//            nums[i] = Long.parseLong(br.readLine());
//        }
//
//        S = 1;
//        while (S <N) {
//            S*=2;
//        }
//        tree = new long[S*2];
//    }
//
//    static void initBU() {
//        //Leaf 에 값 반영
//        for(int i=0; i<N; i++) {
//            tree[S+i] = nums[i];
//        }
//        for(int i=S-1; i>0; i--) {
//            tree[i] = tree[i*2] + tree[i*2+1];
//        }
//    }
//
//    static long query(int left, int right, int node, int queryLeft, int queryRight) {
//        //연관 없음
//        if(queryRight < left || queryLeft > right) return 0;
//
//        //판단 가능
//        else if(queryLeft <= left && right <= queryRight) {
//            return tree[node];
//        }
//
//        else {
//            int mid = (left + right)/2;
//            long leftResult = query(left, mid, node*2, queryLeft, queryRight);
//            long rightResult = query(mid+1, right, node*2+1, queryLeft, queryRight);
//            return  leftResult + rightResult;
//        }
//    }
//
//    static void update(int left, int right, int node, int target, int diff) {
//        //연관 없음
//        if(target < left || right < target) {
//            return;
//        }
//
//        //연관 있음
//        else {
//            tree[node] += diff;
//            if(left != right) {
//                int mid = (left + right) / 2;
//                update(left, mid, node*2, target, diff);
//                update(mid+1, right, node*2+1, target, diff);
//            }
//        }
//    }
//
//    static long queryBU(int queryLeft, int queryRight) {
//        //Leaf에서 left, right 설정
//        int left = S + queryLeft -1;
//        int right = S + queryRight - 1;
//        long sum = 0;
//        while (left <= right) {
//            //좌측 노드가 홀수이면 현재 노드값 사용하고 한칸 옆으로
//            if(left%2==1)  {
//                sum += tree[left++];
//            }
//
//            if(right%2==0) {
//                sum += tree[right--];
//            }
//
//            left /= 2;
//            right /= 2;
//
//        }
//        return sum;
//    }
//
//    static void updateBU(int target, int value) {
//        //Leaf에사 target을 찾음
//        int node = S + target -1;
//
//        //value 반영
//        tree[node] = value;
//
//        //Root에 도달할 때까지 부모에 값 반영
//        node /= 2;
//        while (node > 0) {
//            tree[node] = tree[node * 2] + tree[node * 2 + 1];
//            node/=2;
//        }
//    }
//}
