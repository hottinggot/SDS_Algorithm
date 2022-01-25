package com.algorithm.DAY06.P1516;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 게임 개발
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st;

        int [] beforeThanTask = new int[n+1];
        int [] timeList = new int[n+1];
        List<Integer>[] graph = new List[n+1];

        for(int i=1; i<=n; i++) {
            st = new StringTokenizer(br.readLine());
            timeList[i] = Integer.parseInt(st.nextToken());

            beforeThanTask[i] = st.countTokens()-1;
            while (st.countTokens()>1) {
                int before = Integer.parseInt(st.nextToken());
                if(graph[before] == null) {
                    graph[before] = new ArrayList<>();
                }
                graph[before].add(i);
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int i=1; i<=n; i++) {
            if(beforeThanTask[i] == 0) {
                queue.add(i);
            }
        }

        int [] ans = new int[n+1];

        while (!queue.isEmpty()) {
            int task = queue.poll();
            ans[task] += timeList[task];

            for(int i=0; graph[task]!=null && i<graph[task].size(); i++) {
                int nextTask = graph[task].get(i);
                beforeThanTask[nextTask]--;
                if(beforeThanTask[nextTask] == 0)
                    queue.add(nextTask);

                ans[nextTask] = Math.max(ans[nextTask], ans[task]);
            }

            if(graph[task] != null) graph[task].clear();
        }

        for(int i=1; i<=n; i++) {
            System.out.println(ans[i]);
        }

    }
}
