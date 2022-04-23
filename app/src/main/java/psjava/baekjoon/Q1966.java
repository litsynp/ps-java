package psjava.baekjoon;

import java.io.*;
import java.util.*;

/**
 * 2022.04.22 - 백준 - #1966 프린터 큐
 *
 * 우선순위 큐 하나만 사용해선 우선 순위가 같은 원소 때문에 풀 수 없음!
 */
public class Q1966 {

    public static void main(String[] args) throws IOException {

        int T = Integer.parseInt(ps.br.readLine());

        for (int t = 0; t < T; t++) {
            int[] temp = Arrays.stream(ps.br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int N = temp[0];
            int M = temp[1];

            int[] inputs = Arrays.stream(ps.br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            LinkedList<Item> q = new LinkedList<>();

            for (int i = 0; i < N; i++) {
                q.offer(new Item(i, inputs[i]));
            }

            int cnt = 0;
            while (!q.isEmpty()) {
                // 현재 원소
                Item front = q.poll();

                // 현재 원소가 가장 큰지 여부
                boolean isMax = true;

                // 나머지 문서들 중
                for (int i = 0; i < q.size(); i++) {
                    // 현재 문서보다 중요도가 더 높은 문서가 하나라도 있다면
                    if (front.priority < q.get(i).priority) {
                        // 현재 문서를 포함한 i - 1 번째 까지의 문서를 queue의 가장 뒤로 재배치
                        q.offer(front);
                        for (int j = 0; j < i; j++) {
                            q.offer(q.poll());
                        }

                        isMax = false;
                        break;
                    }
                }

                // front가 우선 순위가 가장 높지 않은 경우
                // 다음 반복문에 계속
                if (!isMax) {
                    continue;
                }

                // front가 우선 순위가 가장 큰 문서였다면
                cnt++;
                if (front.num == M) {
                    // M번째 문서라면 출력
                    ps.sb.append(cnt).append("\n");
                    break;
                }
            }
        }

        ps.close();
    }

    static class Item {
        int num, priority;

        Item(int num, int priority) {
            this.num = num;
            this.priority = priority;
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
