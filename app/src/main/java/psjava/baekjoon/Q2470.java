package psjava.baekjoon;

import java.io.*;
import java.util.*;

/**
 * 2022.04.11 - 백준 - #2470 두 용액
 */
public class Q2470 {

    public static void main(String[] args) throws IOException {
        ps.init();

        int n = Integer.parseInt(ps.br.readLine());
        int[] arr = Arrays.stream(ps.br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();

        int start = 0;
        int end = n - 1;
        int minSum = Integer.MAX_VALUE;

        // pairs
        int p1 = 0;
        int p2 = 0;

        while (start < end) {
            int sum = arr[start] + arr[end];
            int temp = Math.abs(sum);
            if (temp < minSum) {
                p1 = arr[start];
                p2 = arr[end];
                minSum = temp;
            }

            if (sum > 0) {
                end--;
            } else {
                start++;
            }
        }
        ps.sb.append(p1).append(" ").append(p2).append("\n");

        ps.close();
    }

    static class ps {

        public static BufferedReader br;
        public static BufferedWriter bw;
        public static StringBuilder sb;

        public static void init() {
            br = new BufferedReader(new InputStreamReader(System.in));
            bw = new BufferedWriter(new OutputStreamWriter(System.out));
            sb = new StringBuilder();
        }

        public static void close() throws IOException {
            bw.write(sb.toString());
            br.close();
            bw.close();
        }

        public static int[] getIntInputs() throws IOException {
            return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
    }
}
