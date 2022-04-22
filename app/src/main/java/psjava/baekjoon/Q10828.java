package psjava.baekjoon;

import java.io.*;
import java.util.*;

/**
 * 2022.04.22 - 백준 - #10828 스택
 */
public class Q10828 {

    public static void main(String[] args) throws IOException {
        ps.init();

        Stack<Integer> stack = new Stack<>();
        int N = Integer.parseInt(ps.br.readLine());
        for (int i = 0; i < N; i++) {
            String[] in = ps.br.readLine().split(" ");
            if (in.length > 1) {
                stack.push(Integer.parseInt(in[1]));
            } else {
                if (in[0].equals("pop")) {
                    ps.sb.append(stack.empty() ? -1 : stack.pop()).append("\n");
                } else if (in[0].equals("size")) {
                    ps.sb.append(stack.size()).append("\n");
                } else if (in[0].equals("empty")) {
                    ps.sb.append(stack.empty() ? 1 : 0).append("\n");
                } else {
                    ps.sb.append(stack.empty() ? -1 : stack.peek()).append("\n");
                }
            }
        }

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
