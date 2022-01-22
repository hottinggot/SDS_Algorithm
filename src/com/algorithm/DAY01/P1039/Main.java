package com.algorithm.DAY01.P1039;

import java.awt.print.Pageable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int k;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        int tempN = n;
        int m = 0;
        while(tempN > 0) {
            m++;
            tempN /= 10;
        }

        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        boolean[] check = new boolean[n*10];

        q1.add(n);
        check[n] = true;

        int cnt = 0;
        int ans = 0;
        int maxNum = 0;
        boolean flag = false;
        while(true) {
            Queue<Integer> nowQ;
            Queue<Integer> anotherQ;
            if(!flag) {
                nowQ = q1;
                anotherQ = q2;
            } else {
                nowQ = q2;
                anotherQ = q1;
            }

            if(nowQ.isEmpty()) break;

            int nowN = nowQ.poll();
            check[nowN] = false;

            for(int i=0; i<m-1; i++) {
                for(int j=i+1; j<m; j++) {
                    if(i==0 && jariNum(nowN,m-j)==0) {
                        continue;
                    }
                    int newNum = nowN;
                    newNum -= Math.pow(10, m-i-1) * jariNum(nowN, m-i);
                    newNum += Math.pow(10, m-i-1) * jariNum(nowN, m-j);

                    newNum -= Math.pow(10, m-j-1) * jariNum(nowN, m-j);
                    newNum += Math.pow(10, m-j-1) * jariNum(nowN, m-i);

                    if(!check[newNum]) {
                        anotherQ.add(newNum);
                        check[newNum] = true;
                    }

                    maxNum = Math.max(maxNum, newNum);
                }
            }

            if(nowQ.isEmpty()) {
                cnt++;
                if(cnt == k) {
                    ans = maxNum;
                    break;
                }
                flag = !flag;
                maxNum = 0;
            }


        }


        if(ans==0) {
            System.out.println(-1);
        } else {
            System.out.println(ans);
        }
    }

    static int jariNum(int num, int m) {
        int temp = num;
        int k = 1;
        while (k<m) {
            temp /= 10;
            k++;
        }
        return temp%10;
    }
}
