package com.algorithm.DAY03.P1927;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static List<Integer> heap = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        heap.add(0);

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            if(num == 0)
                System.out.println(heap_remove());
            else
                heap_add(num);
        }
    }

    static int heap_remove() {
        if(heap.size()<2) return 0;
        int num = heap.get(1);
        int lastNodeValue = heap.get(heap.size() - 1);
        heap.set(1, lastNodeValue);
        heap.remove(heap.size()-1);

        int nodeIndex = 1;

        while(nodeIndex < heap.size()) {
            Integer leftVal = null, rightVal = null;
            if(nodeIndex*2 < heap.size())
                leftVal = heap.get(nodeIndex*2);

            if(nodeIndex*2+1 < heap.size())
                rightVal = heap.get(nodeIndex*2 + 1);

            if(leftVal != null && rightVal !=null) {
                if(heap.get(nodeIndex) <= leftVal && heap.get(nodeIndex) <= rightVal) break;
                else if(heap.get(nodeIndex) > leftVal && heap.get(nodeIndex) > rightVal) {
                    int temp = heap.get(nodeIndex);
                    if(leftVal <= rightVal) {
                        heap.set(nodeIndex, heap.get(nodeIndex*2));
                        heap.set(nodeIndex*2, temp);
                        nodeIndex = nodeIndex*2;
                    } else {
                        heap.set(nodeIndex, heap.get(nodeIndex*2+1));
                        heap.set(nodeIndex*2+1, temp);
                        nodeIndex = nodeIndex*2 + 1;
                    }
                }
                else if(leftVal > heap.get(nodeIndex)) {
                    int temp = heap.get(nodeIndex);
                    heap.set(nodeIndex, heap.get(nodeIndex*2+1));
                    heap.set(nodeIndex*2+1, temp);
                    nodeIndex = nodeIndex*2 + 1;
                } else {
                    int temp = heap.get(nodeIndex);
                    heap.set(nodeIndex, heap.get(nodeIndex*2));
                    heap.set(nodeIndex*2, temp);
                    nodeIndex = nodeIndex*2;
                }
            } else if(leftVal == null && rightVal != null && rightVal < heap.get(nodeIndex)) {
                int temp = heap.get(nodeIndex);
                heap.set(nodeIndex, heap.get(nodeIndex*2+1));
                heap.set(nodeIndex*2+1, temp);
                nodeIndex = nodeIndex*2 + 1;
            } else if(leftVal != null && leftVal < heap.get(nodeIndex)) {
                int temp = heap.get(nodeIndex);
                heap.set(nodeIndex, heap.get(nodeIndex*2));
                heap.set(nodeIndex*2, temp);
                nodeIndex = nodeIndex*2;
            } else {
                break;
            }
        }

        return num;
    }

    static void heap_add(int num) {
        heap.add(num);

        if(heap.size()>1) {
            int nodeIndex = heap.size() - 1;
            while(nodeIndex>1) {
                if(heap.get(nodeIndex/2) > heap.get(nodeIndex)) {
                    int temp = heap.get(nodeIndex/2);
                    heap.set(nodeIndex/2, heap.get(nodeIndex));
                    heap.set(nodeIndex, temp);
                }
                nodeIndex /= 2;
            }
        }
    }
}
