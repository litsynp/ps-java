package psjava.baekjoon;

import java.io.*;
import java.util.*;

/**
 * 2022.04.11 - 백준 - #3273 두 수의 합
 */
public class Q3273 {

    public static void main(String[] args) throws IOException {
        ps.init();

        int n = Integer.parseInt(ps.br.readLine());
        int[] arr = Arrays.stream(ps.br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();
        int x = Integer.parseInt(ps.br.readLine());

        int answer = 0;
        int start = 0;
        int end = n - 1;
        while (start < end) {
            int sum = arr[start] + arr[end];
            if (sum == x) {
                answer++;
            }

            if (sum <= x) {
                start++;
            } else {
                end--;
            }
        }

        ps.sb.append(answer).append("\n");
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
