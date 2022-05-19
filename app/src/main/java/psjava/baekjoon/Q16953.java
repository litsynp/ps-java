package psjava.baekjoon;

import java.io.*;
import java.util.*;

/**
 * 2022.05.19 - 백준 - #16953 A -> B
 */
public class Q16953 {

    private static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        int[] in = Arrays.stream(ps.br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        long a = in[0];
        long b = in[1];

        dfs(a, b, 1);

        if (answer == Integer.MAX_VALUE) {
            answer = -1;
        }
        ps.sb.append(answer).append("\n");
        ps.close();
    }

    private static void dfs(long n, long target, int curAnswer) {
        if (n == target) {
            answer = Math.min(answer, curAnswer);
        }

        if (n > target) {
            return;
        }

        dfs(n * 2, target, curAnswer + 1);
        dfs(n * 10 + 1, target, curAnswer + 1);
    }

    private static class ps {

        public static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        public static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        public static final StringBuilder sb = new StringBuilder();

        public static void close() throws IOException {
            bw.write(sb.toString());
            br.close();
            bw.close();
        }
    }
}
