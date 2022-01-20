package com.algorithm.DAY03.P9202;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static Node trie;
    static int sum = 0;
    static boolean[][] check;
    static StringBuilder sb;
    static char[][] boggle;
    static String maxStr;
    static int findWordCnt = 0;

    static int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};
    static int[] score = {0, 0, 1, 1, 2, 3, 5, 11};


    static class Node {
        Node [] children = new Node[26];
        boolean isEnd;
        boolean isHit;

        void clearHit() {
            isHit = false;
            for(int i=0; i<children.length; i++) {
                Node child = children[i];
                if(child!=null) {
                    child.clearHit();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int w = Integer.parseInt(st.nextToken());

        trie = new Node();

        sb = new StringBuilder();
        boggle = new char[4][4];
        check = new boolean[4][4];

        for(int i=0; i<w; i++) {
            st = new StringTokenizer(br.readLine());
            String word = st.nextToken();

            Node node = trie;
            for(int j=0; j<word.length(); j++) {
                if(node.children[word.charAt(j) - 'A'] == null){
                    node.children[word.charAt(j) - 'A'] = new Node();
                }
                node = node.children[word.charAt(j) - 'A'];
            }
            node.isEnd = true;
        }

        br.readLine();
        st = new StringTokenizer(br.readLine());
        int b = Integer.parseInt(st.nextToken());

        for(int i=0; i<b; i++) {
            sum = 0;
            maxStr = "";
            findWordCnt = 0;
            trie.clearHit();
            for(int j=0; j<4; j++) {
                st = new StringTokenizer(br.readLine());
                String temp = st.nextToken();
                for(int k=0; k<4; k++) {
                    boggle[j][k] = temp.charAt(k);
                }
            }
            if(i!=b-1) br.readLine();

            for(int j=0; j<4; j++) {
                for(int k=0; k<4; k++) {
                    //root 가 해당 child를 가져야 출발 가능 (hasChild)
                    if(trie.children[boggle[j][k] - 'A']!=null)
                        dfs(j, k, 1, trie.children[boggle[j][k] - 'A']);
                }
            }

            System.out.println(sum + " " + maxStr + " " + findWordCnt);
        }
    }

    static void dfs(int y, int x, int length, Node node) {

        check[y][x] = true;
        sb.append(boggle[y][x]);

        if(node.isEnd && !node.isHit) {
            node.isHit = true;
            sum += score[length-1];

            String word = sb.toString();
            if(maxStr.length() < word.length()) {
                maxStr = word;
            } else if(maxStr.length() == word.length()) {
                if(maxStr.compareTo(word) > 0)
                    maxStr = word;
            }

            findWordCnt++;
        }

        for(int i=0; i<8; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if(ny >=0 && ny < 4 && nx >=0 && nx < 4) {
                if(!check[ny][nx] && node.children[boggle[ny][nx] - 'A']!=null) {
                    dfs(ny, nx, length+1, node.children[boggle[ny][nx] - 'A']);
                }
            }
        }

        check[y][x] = false;
        sb.deleteCharAt(length-1);

    }

}
