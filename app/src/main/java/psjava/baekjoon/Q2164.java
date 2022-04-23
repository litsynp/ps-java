package psjava.baekjoon;

import java.io.*;
import java.util.*;
import java.util.stream.*;

/**
 * 2022.04.24 - 백준 - #2164 카드2
 */
public class Q2164 {

    public static void main(String[] args) throws IOException {

        int N = Integer.parseInt(ps.br.readLine());
        Queue<Integer> q = IntStream.rangeClosed(1, N).boxed().collect(Collectors.toCollection(LinkedList::new));
        boolean flip = false;

        int val = 0;
        while (!q.isEmpty()) {
            val = q.poll();

            if (flip) {
                q.add(val);
            }
            flip = !flip;
        }
        ps.sb.append(val).append("\n");
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
