package psjava.baekjoon;

import java.io.*;
import java.util.*;

/**
 * 2022.04.24 - 백준 - #1874 스택 수열
 */
public class Q1874 {

    public static void main(String[] args) throws IOException {

        int n = Integer.parseInt(ps.br.readLine());
        Stack<Integer> stack = new Stack<>();

        int top = 0;
        for (int i = 0; i < n; i++) {
            int value = Integer.parseInt(ps.br.readLine());

            if (value > top) {
                // 입력값이 top보다 큰 경우
                for (int j = top + 1; j <= value; j++) {
                    // 스택 top이 입력값이 되도록 (top + 1) ~ 입력값을 push
                    stack.push(j);
                    ps.sb.append("+\n");
                }
                top = value;
            } else if (stack.peek() != value) {
                // 입력값이 top보다 작은 경우
                // 스택 맨 위의 원소가 입력값이 아닌 경우
                ps.sb.setLength(0);
                ps.sb.append("NO").append("\n");
                break;
            }

            // 입력값이 top과 같은 경우 pop
            stack.pop();
            ps.sb.append("-\n");
        }

        ps.close();
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

        public static int[] getIntInputs() throws IOException {
            return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
    }
}
