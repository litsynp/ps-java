package psjava.baekjoon;

import java.io.*;
import java.util.*;

/**
 * 2022.04.24 - 백준 - #2493 탑
 */
public class Q2493 {

    public static void main(String[] args) throws IOException {

        int N = Integer.parseInt(ps.br.readLine());
        int[] arr = Arrays.stream(ps.br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Stack<int[]> stack = new Stack<>();

        // 왼쪽부터 오른쪽으로 탐색
        for (int i = 0; i < N; i++) {
            int cur = arr[i];

            while (!stack.isEmpty()) {
                if (stack.peek()[1] >= cur) {
                    // 닿았다면
                    ps.sb.append(stack.peek()[0]).append(" ");
                    break;
                }

                // 조회 중인 탑이 현재 탑보다 낮다면 이후에도 다른 탑이 레이저에 닿을 수 없으므로 pop
                // 즉, stack은 내림차순으로 정렬 (top이 가장 낮고, bottom이 가장 높음)
                stack.pop();
            }

            if (stack.isEmpty()) {
                // 현재 탑의 레이저가 닿을 탑이 왼쪽에 없다면
                ps.sb.append("0 ");
            }

            // 현재 탑을 push
            stack.push(new int[] { i + 1, cur });
        }

        ps.sb.append("\n");
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
    }
}
