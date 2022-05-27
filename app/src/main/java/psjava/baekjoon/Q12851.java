package psjava.baekjoon;

import java.io.*;
import java.util.*;

/**
 * 2022.05.19 - 백준 - #12851 숨바꼭질 2
 * <p>
 * GOLD 5
 * <p>
 * BFS
 * <p>
 * BFS를 구현하면 Queue에 넣자마자 최적화를 위해 바로 방문 처리를 한다. 넣었다 뺐다를 반복하지 않도록 하기 위해서이다.
 * 하지만 이 문제는 가능한 모든 개수를 세야하기 때문에 그렇게 구현하면 안된다.
 *
 * @see <a href="https://www.acmicpc.net/problem/12851">https://www.acmicpc.net/problem/12851</a>
 */
public class Q12851 {

    static int min;
    static int count;

    public static void main(String[] args) throws IOException {
        ps.nextLine();
        int N = ps.stoi(ps.st.nextToken());
        int K = ps.stoi(ps.st.nextToken());

        min = Integer.MAX_VALUE;
        count = 1;

        if (N > K) {
            // 수빈이보다 동생이 앞에 있으면
            // 무조건 뒤로 -1 만큼씩밖에 갈 수 없음
            ps.sb.append(N - K).append("\n");
            ps.sb.append(1).append("\n");
        } else {
            // N -> K로 가는 가장 빠른 시간
            // N -> K로 가장 빠른 시간으로 찾는 방법 개수
            bfs(N, K);

            ps.sb.append(min).append("\n");
            ps.sb.append(count).append("\n");
        }
        ps.close();
    }

    static void bfs(int a, int b) {
        Queue<Pair> q = new LinkedList<>();
        boolean[] visited = new boolean[100_001];

        q.offer(new Pair(a, 0));
        visited[a] = true;

        while (!q.isEmpty()) {
            Pair p = q.poll();
            int pos = p.pos;
            int time = p.time;
            visited[pos] = true;

            if (pos == b) {
                // 수빈이가 동생을 잡은 경우
                if (min > time) {
                    min = time;
                } else if (min == time) {
                    count++;
                }
                continue;
            }

            int p1 = pos - 1;
            int p2 = pos + 1;
            int p3 = pos * 2;

            // 이동할 수 있는 경우에만 queue에 넣고 방문 처리
            if (p1 >= 0 && !visited[p1]) {
                q.offer(new Pair(p1, time + 1));
            }
            if (p2 <= 100_000 && !visited[p2]) {
                q.offer(new Pair(p2, time + 1));
            }
            if (p3 <= 100_000 && !visited[p3]) {
                q.offer(new Pair(p3, time + 1));
            }
        }
    }


    static class Pair {
        int pos, time;

        public Pair(int pos, int time) {
            this.pos = pos;
            this.time = time;
        }
    }

    private static class ps {

        public static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        public static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        public static final StringBuilder sb = new StringBuilder();
        public static StringTokenizer st = null;

        public static void nextLine() throws IOException {
            st = new StringTokenizer(br.readLine());
        }

        public static int stoi(String s) {
            return Integer.parseInt(s);
        }

        public static void close() throws IOException {
            bw.write(sb.toString());
            br.close();
            bw.close();
        }
    }
}
