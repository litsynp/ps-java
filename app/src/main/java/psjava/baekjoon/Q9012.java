package psjava.baekjoon;

import java.io.*;
import java.util.*;

/**
 * 2022.04.22 - 백준 - #9012 괄호
 */
public class Q9012 {

    public static void main(String[] args) throws IOException {
        ps.init();

        Stack<Character> stack = new Stack<>();

        int T = Integer.parseInt(ps.br.readLine());
        for (int t = 0; t < T; t++) {
            String[] in = ps.br.readLine().split("");
            boolean result = true;

            for (int i = 0; i < in.length; i++) {
                char c = in[i].charAt(0);
                if (c == '(') {
                    stack.push('(');
                } else {
                    if (stack.empty()) {
                        result = false;
                        break;
                    }
                    stack.pop();
                }
            }
            if (!stack.empty()) {
                result = false;
            }
            ps.sb.append(result ? "YES" : "NO").append("\n");
            stack.removeAllElements();
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
