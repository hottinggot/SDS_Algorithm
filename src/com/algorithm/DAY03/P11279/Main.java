package com.algorithm.DAY03.P11279;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        MaxHeap maxHeap = new MaxHeap();

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            if(num==0) System.out.println(maxHeap.delete());
            else maxHeap.insert(num);
        }

    }
}

class MaxHeap {
    List<Integer> list;

    public MaxHeap() {
        list = new ArrayList<>();
        list.add(0);
    }

    public void insert(int val) {
        list.add(val);

        int current = list.size()-1;
        int parent = current / 2;

        while(true) {
            if(parent == 0 || list.get(parent) >= list.get(current)) {
                break;
            }

            int temp = list.get(parent);
            list.set(parent, list.get(current));
            list.set(current, temp);

            current = parent;
            parent = current/2;
        }
    }

    public int delete() {
        if(list.size()==1) return 0;
        int top = list.get(1);

        list.set(1, list.get(list.size()-1));
        list.remove(list.size()-1);

        int currentPos = 1;
        while(true) {
            int leftPos = currentPos*2;
            int rightPos = currentPos*2 + 1;

            if(leftPos >= list.size()) {
                break;
            }

            int maxValue = list.get(leftPos);
            int maxPos = leftPos;

            if(rightPos < list.size() && list.get(rightPos) > maxValue) {
                maxValue = list.get(rightPos);
                maxPos = rightPos;
            }

            if(list.get(currentPos) < maxValue) {
                int temp = list.get(currentPos);
                list.set(currentPos, maxValue);
                list.set(maxPos, temp);
                currentPos = maxPos;
            } else {
                break;
            }
        }

        return top;
    }
}