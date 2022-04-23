package psjava.baekjoon;

import java.io.*;
import java.util.*;

/**
 * 2022.04.24 - 백준 - #1935 후위 표기식2
 */
public class Q1935 {

    public static void main(String[] args) throws IOException {

        int N = Integer.parseInt(ps.br.readLine());

        char[] ex = ps.br.readLine().toCharArray();
        int[] op = new int[N];

        for (int i = 0; i < N; i++) {
            op[i] = Integer.parseInt(ps.br.readLine());
        }

        Stack<Double> stack = new Stack<>();
        for (char e : ex) {
            double val1, val2;
            switch (e) {
                case '+':
                    stack.push(stack.pop() + stack.pop());
                    break;
                case '-':
                    val2 = stack.pop();
                    val1 = stack.pop();
                    stack.push(val1 - val2);
                    break;
                case '*':
                    stack.push(stack.pop() * stack.pop());
                    break;
                case '/':
                    val2 = stack.pop();
                    val1 = stack.pop();
                    stack.push(val1 / val2);
                    break;
                default:
                    int idx = e - 'A';
                    stack.push((double) op[idx]);
            }
        }

        ps.sb.append(String.format("%.2f", stack.pop())).append("\n");
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
