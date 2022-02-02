package com.algorithm.DAY02.P2517;
// 달리기

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

class Skill {
    int index;
    int value;

    public Skill(int index, int value) {
        this.index = index;
        this.value = value;
    }
}

public class Main {
    static int n;
    static int [] tree;
    static int S = 1;
    static Skill [] skill;
    static int [] answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        n = Integer.parseInt(br.readLine());
        skill = new Skill[n];

        while (S < n) {
            S *= 2;
        }

        tree = new int[S*2];

        for(int i=0; i<n; i++) {
            skill[i] = new Skill(i, Integer.parseInt(br.readLine()));
        }

        // 능력치 내림차순으로 정렬
        Arrays.sort(skill, new Comparator<Skill>() {
            @Override
            public int compare(Skill o1, Skill o2) {
                return o2.value - o1.value;
            }
        });

        answer = new int[n];

        for(int i=0; i<n; i++) {
            Skill tempSkill = skill[i];
            update(1, S, 1, tempSkill.index + 1, 1);
            answer[tempSkill.index] = query(1, S, 1, 1, tempSkill.index);
        }

        for(int i=0; i<n; i++) {
            bw.write(answer[i] + 1 + "\n");
        }
        bw.flush();
        bw.close();
    }


    static int query(int left, int right, int node, int queryLeft, int queryRight) {
        if(left > queryRight || right < queryLeft) {
            return 0;
        }

        else if(left >= queryLeft && right <= queryRight) {
            return tree[node];
        }

        else {
            int mid = (left + right)/2;
            int leftValue = query(left, mid, node*2, queryLeft, queryRight);
            int rightValue = query(mid+1, right, node*2+1, queryLeft, queryRight);

            return leftValue + rightValue;
        }
    }

    static void update(int left, int right, int node, int target, int diff) {

        if(left > target || right < target) {
            return;
        }

        tree[node] += diff;

        if(left != right) {
            int mid = (left + right)/2;
            update(left, mid, node*2, target, diff);
            update(mid+1, right, node*2+1, target, diff);
        }
    }
}
