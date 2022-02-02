package com.algorithm.DAY02.P2842;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Point {
    int y;
    int x;
    public Point(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

public class Main {
    static int [] dy = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int [] dx = {-1, 0, 1, -1, 1, -1, 0, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int K = 0;

        int [][] map = new int[n][n];
        Point startPoint = new Point(0, 0);

        for(int i=0; i<n; i++) {
            String str = br.readLine();
            for(int j=0; j<n; j++) {
                char temp = str.charAt(j);
                switch (temp) {
                    case 'P':
                        map[i][j] = 1;
                        startPoint.y = i;
                        startPoint.x = j;
                        break;
                    case 'K':
                        map[i][j] = 2;
                        K++;
                        break;
                    case '.':
                        map[i][j] = 3;
                        break;
                }
            }
        }

        int [][] height = new int[n][n];
        StringTokenizer st;
        HashMap<Integer, Boolean> hashMap = new HashMap<>();

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                height[i][j] = Integer.parseInt(st.nextToken());
                if(!hashMap.containsKey(height[i][j])) {
                    hashMap.put(height[i][j], true);
                }
            }
        }

        List<Integer> list = new ArrayList<>();

        for(Integer key: hashMap.keySet()) {
            list.add(key);
        }

        Collections.sort(list);

        int minAns = Integer.MAX_VALUE;

        int lo = 0;
        int hi = 0;
        while (lo<=hi && hi < list.size()) {

            int cntHouse = 0;

            if(list.get(lo) <= height[startPoint.y][startPoint.x] && list.get(hi) >= height[startPoint.y][startPoint.x]) {
                Queue<Point> queue = new LinkedList<>();
                boolean [][] check = new boolean[n][n];

                check[startPoint.y][startPoint.x] = true;
                queue.add(startPoint);


                while (!queue.isEmpty()) {
                    int nowY = queue.peek().y;
                    int nowX = queue.peek().x;
                    queue.poll();

                    for(int i=0; i<8; i++) {
                        if(nowY + dy[i] >= 0 && nowY + dy[i] < n && nowX + dx[i] >= 0 && nowX + dx[i] < n) {
                            if(!check[nowY + dy[i]][nowX + dx[i]]
                                    && height[nowY + dy[i]][nowX + dx[i]] >= list.get(lo)
                                    && height[nowY + dy[i]][nowX + dx[i]] <= list.get(hi)) {
                                check[nowY + dy[i]][nowX + dx[i]] = true;
                                if(map[nowY + dy[i]][nowX + dx[i]] == 2) cntHouse++;
                                queue.add(new Point(nowY + dy[i], nowX + dx[i]));
                            }
                        }
                    }
                }
            }

            if(cntHouse==K) {
                minAns = Math.min(minAns, list.get(hi) - list.get(lo));
                lo++;
            } else {
                hi++;
            }
        }

        System.out.println(minAns);
    }
}
