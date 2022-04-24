package psjava.baekjoon;

import java.io.*;
import java.util.*;

/**
 * 2022.04.24 - 백준 - #2504 괄호의 값
 *
 * Stack + 분배 법칙
 * 괄호가 닫힐 때 괄호에 정해진 최종 값을 더하는 방식
 */
public class Q2504 {

    public static void main(String[] args) throws IOException {

        char[] in = ps.br.readLine().toCharArray();
        Stack<Character> stack = new Stack<>();

        int answer = 0;
        int cnt = 1;
        for (int i = 0; i < in.length; i++) {
            char cur = in[i];
            if (cur == '(') {
                stack.push(cur);
                cnt *= 2;
            } else if (cur == '[') {
                stack.push(cur);
                cnt *= 3;
            } else {
                if (cur == ')') {
                    if (stack.isEmpty() || stack.peek() != '(') {
                        // 비정상적인 괄호
                        answer = 0;
                        break;
                    }
                    if (in[i - 1] == '(') {
                        // () 라면
                        answer += cnt;
                    }
                    // 분배 법칙
                    stack.pop();
                    cnt /= 2;
                } else if (cur == ']') {
                    if (stack.isEmpty() || stack.peek() != '[') {
                        // 비정상적인 괄호
                        answer = 0;
                        break;
                    }
                    if (in[i - 1] == '[') {
                        // [] 라면
                        answer += cnt;
                    }
                    // 분배 법칙
                    stack.pop();
                    cnt /= 3;
                } else {
                    answer = 0;
                    break;
                }
            }
        }

        if (!stack.isEmpty()) {
            // 스택에 원소가 남아있다면 비정상
            answer = 0;
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
