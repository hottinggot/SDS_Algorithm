package com.algorithm.DAY04.P3955;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());

            EGResult result = extendedGcd(a, b);

            long d = result.r;
            if(d!=1) {
                System.out.println("IMPOSSIBLE");
                continue;
            }

            // x0 = s * c / d
            long x0 = result.s;

            // y0 = t * c / d;
            long y0 = result.t;

            // k의 범위
            // x < 0
            // x0 + b*k < 0
            // k < -x0 / b

            // 0 < y <= 1e9
            // 0 < y0 - A * k <=1e9
            // -y0 < -A * k <= 1e9 - y0
            // ( y0 - 1e9 ) / A <= k < y0 / A

            // 결론적인 k의 범위는 k < -x0 / b && ( y0 - 1e9 ) / A <= k < y0 / A

            long kStart = (long) Math.ceil((y0 - 1e9) / (double) a);

            long kEndFromY = (long) Math.ceil((double) y0 / (double) a) - 1;
            long kEndFromX = (long) Math.ceil((double) -x0 / (double) b) - 1;

            long kEnd = Math.min(kEndFromX, kEndFromY);

            long k = 0;
            if(kStart <= kEnd) {
                k = kEnd;
            } else {
                System.out.println("IMPOSSIBLE");
                continue;
            }

            // x = x0 + b / d * k
            long x = x0 + b*k;

            // y = y0 - a / d * k
            long y = y0 - a*k;

            System.out.println(y);
        }
    }

    static EGResult extendedGcd(long a, long b) {
        long s0 = 1, t0 = 0, r0 = a;
        long s1 = 0, t1 = 1, r1 = b;

        long temp;

        while(r1 != 0) {
            long q = r0 / r1;

            temp = r0 - q * r1;
            r0 = r1;
            r1 = temp;

            temp = s0 - q*s1;
            s0 = s1;
            s1 = temp;

            temp = t0 - q*t1;
            t0 = t1;
            t1 = temp;
        }

        return new EGResult(s0, t0, r0);
    }
}

class EGResult {
    long s;
    long t;
    long r;

    public EGResult(long s, long t, long r) {
        this.s = s;
        this.t = t;
        this.r = r;
    }
}
