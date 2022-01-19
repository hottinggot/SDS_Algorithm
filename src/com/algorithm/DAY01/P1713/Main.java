package com.algorithm.DAY01.P1713;

import java.util.*;

public class Main {

    static class Student {
        int recommendCnt;
        int time;
        int studentId;

        Student (int recommendCnt, int time, int studentId) {
            this.recommendCnt = recommendCnt;
            this.time = time;
            this.studentId = studentId;
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int k = scanner.nextInt();

        List<Student> list = new ArrayList<>();

        for(int i=0; i<k; i++) {
            int studentId = scanner.nextInt();

            boolean flag = false;

            for(int j=0; j<list.size(); j++) {
                if(list.get(j).studentId == studentId) {
                    list.get(j).recommendCnt += 1;
                    flag = true;
                    break;
                }
            }
            if(!flag) {
                if(list.size() < n) {
                    list.add(new Student(1, i, studentId));
                } else {

                    Collections.sort(list, new Comparator<Student>() {
                        @Override
                        public int compare(Student o1, Student o2) {
                            if(o1.recommendCnt == o2.recommendCnt) {
                                return o2.time - o1.time;
                            } else {
                                return o2.recommendCnt - o1.recommendCnt;
                            }
                        }
                    });

                    list.remove(n-1);
                    list.add(new Student(1, i, studentId));

                }
            }

        }

        Collections.sort(list, new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.studentId - o2.studentId;
            }
        });

        for(int i=0; i<list.size(); i++) {
            System.out.print(list.get(i).studentId + " ");
        }

    }
}
