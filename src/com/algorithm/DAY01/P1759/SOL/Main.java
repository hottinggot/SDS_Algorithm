package com.algorithm.DAY01.P1759.SOL;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static int l, c;
    static char[] data;
    static List<String> result;
    public static void main(String[] args) {
//        System.setIn(new FileInputStream(""));
        Scanner scanner = new Scanner(System.in);
        l = scanner.nextInt();
        c = scanner.nextInt();

        data = new char[c];
        result = new LinkedList<>();

        for(int i=0; i<c; i++) {
            data[i] = scanner.next().charAt(0);
        }



    }
}
