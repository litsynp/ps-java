package psjava.baekjoon;

import java.io.*;
import java.util.*;

/**
 * 2022.04.24 - 백준 - #10866 덱
 */
public class Q10866 {

    public static void main(String[] args) throws IOException {

        int N = Integer.parseInt(ps.br.readLine());
        Deque<Integer> q = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            String[] in = ps.br.readLine().split(" ");
            switch (in[0]) {
                case "push_front":
                    q.addFirst(Integer.parseInt(in[1]));
                    break;
                case "push_back":
                    q.addLast(Integer.parseInt(in[1]));
                    break;
                case "pop_front":
                    ps.sb.append(q.isEmpty() ? -1 : q.pollFirst()).append("\n");
                    break;
                case "pop_back":
                    ps.sb.append(q.isEmpty() ? -1 : q.pollLast()).append("\n");
                    break;
                case "size":
                    ps.sb.append(q.size()).append("\n");
                    break;
                case "empty":
                    ps.sb.append(q.isEmpty() ? 1 : 0).append("\n");
                    break;
                case "front":
                    ps.sb.append(q.isEmpty() ? -1 : q.getFirst()).append("\n");
                    break;
                case "back":
                default:
                    ps.sb.append(q.isEmpty() ? -1 : q.getLast()).append("\n");
                    break;
            }
        }

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
