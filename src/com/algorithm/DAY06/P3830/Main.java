package com.algorithm.DAY06.P3830;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int m;
    static int [] sampleParent;
    static long [] sampleDiff;

    public static void union(int a, int b, int w) {
        int root_a = find(a);
        int root_b = find(b);

        if(root_a == root_b) {
            return;
        }

        sampleParent[root_a] = root_b;
        sampleDiff[root_a] = sampleDiff[b] - sampleDiff[a] + w;
    }

    public static int find(int x) {
        if(sampleParent[x] == x) {
            return x;
        } else {
            // parent : 최종 부모, sampleParent[x] : 현재 부모
            int parent = find(sampleParent[x]);
            sampleDiff[x] += sampleDiff[sampleParent[x]];

            //현재 부모 갱신
            return sampleParent[x] = parent;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            if(n==0 && m==0) break;

            sampleParent = new int[n+1];
            sampleDiff = new long[n+1];

            for(int i=1; i<=n; i++) {
                sampleParent[i] = i;
            }

            for(int i=1; i<=n; i++) {
                sampleDiff[i] = 0;
            }

            for(int i=0; i<m; i++) {
                st = new StringTokenizer(br.readLine());

                String op = st.nextToken();

                if(op.equals("!")) {
                    int a = Integer.parseInt(st.nextToken());
                    int b = Integer.parseInt(st.nextToken());
                    int w = Integer.parseInt(st.nextToken());

                    union(a, b, w);

                } else {
                    int a = Integer.parseInt(st.nextToken());
                    int b = Integer.parseInt(st.nextToken());

                    if(find(a) != find(b)) {
                        System.out.println("UNKNOWN");
                    } else {
                        System.out.println(sampleDiff[a] - sampleDiff[b]);
                    }
                }
            }
        }
    }
}
