package psjava.baekjoon;

import java.io.*;
import java.util.*;

/**
 * 2022.04.24 - 백준 - #2346 풍선 터뜨리기
 *
 * Deque 이용
 */
public class Q2346 {

    public static void main(String[] args) throws IOException {

        int N = Integer.parseInt(ps.br.readLine());
        int[] in = Arrays.stream(ps.br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Deque<Item> q = new ArrayDeque<>();
        for (int i = 1; i < N; i++) {
            q.add(new Item(i + 1, in[i]));
        }

        // 1번부터 시작
        ps.sb.append("1 ");
        int index = in[0];
        while (!q.isEmpty()) {
            Item next;
            if (index > 0) {
                // 양수인 경우
                for (int i = 1; i < index; i++) {
                    // 앞의 원소들을 그대로 맨 뒤로 보냄
                    q.add(q.poll());
                }

                // 맨 앞의 원소가 index번 원소
                next = q.poll();
            } else {
                // 음수인 경우
                for (int i = 1; i < -index; i++) {
                    // 뒤의 원소들을 그대로 맨 앞으로 보냄
                    q.addFirst(q.pollLast());
                }

                // 맨 뒤의 원소가 index번 원소
                next = q.pollLast();
            }

            // 제거한 원소의 data가 새로운 index
            index = next.data;

            // 제거한 원소의 원래 번호 출력
            ps.sb.append(next.index).append(" ");
        }
        ps.sb.append("\n");
        ps.close();
    }

    static class Item {
        int index;
        int data;

        Item(int index, int data) {
            this.index = index;
            this.data = data;
        }
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
