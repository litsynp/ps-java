package psjava.baekjoon;

import java.io.*;
import java.util.*;
import java.util.stream.*;

/**
 * 2022.04.23 - 백준 - #1158 요세푸스 문제
 */
public class Q1158 {

    public static void main(String[] args) throws IOException {

        int[] in = Arrays.stream(ps.br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = in[0];
        int K = in[1];

        Queue<Integer> q = IntStream.rangeClosed(1, N)
                .boxed()
                .collect(Collectors.toCollection(LinkedList::new));
        int[] answer = new int[N];
        int curIdx = 0;

        while (!q.isEmpty()) {
            for (int i = 0; i < K - 1; i++) {
                // K번 원소에 도달할 때까지 원소를 뒤로 보낸다
                int val = q.poll();
                q.offer(val);
            }
            // 큐의 맨 앞은 K번 원소가 된다
            answer[curIdx++] = q.poll();
        }

        ps.sb.append("<");
        for (int i = 0; i < N - 1; i++) {
            ps.sb.append(answer[i]).append(", ");
        }
        ps.sb.append(answer[N - 1]).append(">\n");

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
