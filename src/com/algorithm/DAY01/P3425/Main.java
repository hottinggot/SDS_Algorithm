package com.algorithm.DAY01.P3425;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 고스택
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        boolean firstInput = true;

        while(true) {
            int n = 0;
            boolean isCmd = true;
            boolean isN = true;
            List<String> list = new ArrayList<>();
            while (true) {
                String str = "";

                if(isN) {
                    str = br.readLine();
                }

                if(str.equals("QUIT")) {
                    return;
                }

                else if(str.equals("END")) {
                    isCmd = false;
                    continue;
                }

                // 명령어
                if(isCmd) {
                    list.add(str);
                }

                // 숫자
                else {
                    if(isN) {
                        n = Integer.parseInt(str);
                        isN = false;
                    } else {
                        if(!firstInput) System.out.println();
                        for(int i=0; i<n; i++) {
                            st = new StringTokenizer(br.readLine());
                            long v = Long.parseLong(st.nextToken());

                            GoStack goStack = new GoStack();
                            goStack.num(v);
                            boolean isSucceed = true;

                            for(int j=0; j<list.size(); j++) {
                                st = new StringTokenizer(list.get(j));
                                String command = st.nextToken();
                                if(command.equals("NUM")) {
                                    long x = Long.parseLong(st.nextToken());
                                    goStack.num(x);
                                } else if(command.equals("POP")) {
                                    long result = goStack.pop();
                                    if(result==-1) {
                                        isSucceed = false;
                                        break;
                                    }
                                } else if (command.equals("INV")) {
                                    if(!goStack.inv()) {
                                        isSucceed = false;
                                        break;
                                    }
                                } else if (command.equals("DUP")) {
                                    if(!goStack.dup()) {
                                        isSucceed = false;
                                        break;
                                    }
                                } else if(command.equals("SWP")) {
                                    if(!goStack.swp()) {
                                        isSucceed = false;
                                        break;
                                    }
                                } else if(command.equals("ADD")) {
                                    if(!goStack.add()) {
                                        isSucceed = false;
                                        break;
                                    }
                                } else if(command.equals("SUB")) {
                                    if(!goStack.sub()) {
                                        isSucceed = false;
                                        break;
                                    }
                                } else if(command.equals("MUL")) {
                                    if(!goStack.mul()) {
                                        isSucceed = false;
                                        break;
                                    }
                                } else if(command.equals("DIV")) {
                                    if(!goStack.div()) {
                                        isSucceed = false;
                                        break;
                                    }
                                } else if(command.equals("MOD")) {
                                    if(!goStack.mod()) {
                                        isSucceed = false;
                                        break;
                                    }
                                }
                            }
                            if(goStack.list.size() != 1 || !isSucceed) {
                                System.out.println("ERROR");
                            } else {
                                long x = goStack.pop();
                                if(Math.abs(x) > 1e9) System.out.println("ERROR");
                                else System.out.println(x);
                            }
                        }
                        br.readLine();
                        break;
                    }

                }

            }
            firstInput = false;
        }
    }

}

class GoStack {
    List<Long> list;

    public GoStack() {
        list = new ArrayList<>();
    }

    public boolean isEmpty() {
        if(list.size() == 0) return true;
        else return false;
    }

    public boolean canCalculate() {
        if(list.size() >= 2) return true;
        else return false;
    }

    public void num(long x) {
        list.add(x);
    }

    public long pop() {
        if(!isEmpty()) {
            long result = list.get(list.size()-1);
            list.remove(list.size()-1);
            return result;
        } else {
            return -1;
        }
    }

    public boolean inv() {
        if(!isEmpty()) {
            long res = pop();
            num(res * (-1));
            return true;
        }
        return false;
    }

    public boolean dup() {
        if(!isEmpty()) {
            long res = list.get(list.size()-1);
            num(res);
            return true;
        }
        return false;
    }

    public boolean swp() {
        if(canCalculate()) {
            long first = pop();
            long second = pop();
            num(first);
            num(second);
            return true;
        }
        return false;
    }

    public boolean add() {
        if(canCalculate()) {
            long first = pop();
            long second = pop();
            long res = first + second;
            if(Math.abs(res) > 1e9) return false;
            num(res);
            return true;
        }
        return false;
    }

    public boolean sub() {
        if(canCalculate()) {
            long first = pop();
            long second = pop();
            long res = second - first;
            if(Math.abs(res) > 1e9) return false;
            num(res);
            return true;
        }
        return false;
    }

    public boolean mul() {
        if(canCalculate()) {
            long first = pop();
            long second = pop();
            long res = first * second;
            if(Math.abs(res) > 1e9) return false;
            num(res);
            return true;
        }
        return false;
    }

    public boolean div() {
        if(canCalculate()) {
            long first = pop();
            if(first==0) return false;
            long second = pop();
            long res = second/first;
            if(Math.abs(res) > 1e9) return false;

            num(res);
            return true;
        }
        return false;
    }

    public boolean mod() {
        if(canCalculate()) {
            long first = pop();
            if(first==0) return false;
            long second = pop();
            long res = Math.abs(second) % Math.abs(first);
            if(Math.abs(res) > 1e9) return false;
            if(second < 0) {
                res *= -1;
            }

            num(res);
            return true;
        }
        return false;
    }

}

