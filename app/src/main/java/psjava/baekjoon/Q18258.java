package psjava.baekjoon;

import java.io.*;
import java.util.*;

/**
 * 2022.04.22 - 백준 - #18258 큐 2
 */
public class Q18258 {

    public static void main(String[] args) throws IOException {
        ps.init();

        Deque<Integer> q = new LinkedList<>();
        int N = Integer.parseInt(ps.br.readLine());
        for (int i = 0; i < N; i++) {
            String[] in = ps.br.readLine().split(" ");
            if (in.length > 1) {
                q.add(Integer.parseInt(in[1]));
            } else {
                if (in[0].equals("pop")) {
                    ps.sb.append(q.isEmpty() ? -1 : q.pollFirst()).append("\n");
                } else if (in[0].equals("size")) {
                    ps.sb.append(q.size()).append("\n");
                } else if (in[0].equals("empty")) {
                    ps.sb.append(q.isEmpty() ? 1 : 0).append("\n");
                } else if (in[0].equals("front")) {
                    ps.sb.append(q.isEmpty() ? -1 : q.peekFirst()).append("\n");
                } else {
                    ps.sb.append(q.isEmpty() ? -1 : q.peekLast()).append("\n");
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
