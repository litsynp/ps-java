package psjava.baekjoon;

import java.io.*;
import java.util.*;

/**
 * 2022.04.24 - 백준 - #10799 쇠막대기
 */
public class Q10799 {

    public static void main(String[] args) throws IOException {

        char[] in = ps.br.readLine().toCharArray();
        int answer = 0;
        int open = 0;

        for (int i = 0; i < in.length; i++) {
            if (in[i] == '(') {
                // either laser start or bar start
                open++;
            } else {
                open--;

                if (in[i - 1] == '(' && in[i] == ')') {
                    // laser end
                    answer += open;
                } else {
                    // bar end
                    answer++;
                }
            }
        }
        ps.sb.append(answer).append("\n");
        ps.close();
    }

    static class ps {

        public static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        public static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        public static final StringBuilder sb = new StringBuilder();

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
