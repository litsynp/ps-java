package psjava.baekjoon;

import java.io.*;
import java.util.*;

/**
 * 2022.05.26 - 백준 - #4485 녹색 옷 입은 애가 젤다지?
 *
 * GOLD 4
 *
 * 2D Dijkstra
 */
public class Q4485 {

    static int[][] graph;

    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    static final int MAX_VAL = 99999999;

    public static void main(String[] args) throws IOException {
        int num = 1;
        while (true) {
            int n = Integer.parseInt(ps.br.readLine());

            if (n == 0) {
                break;
            }

            graph = new int[n][n];
            for (int i = 0; i < n; i++) {
                graph[i] = Arrays.stream(ps.br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }

            int result = dijkstra(n);

            ps.sb.append("Problem ").append(num).append(": ").append(result)
                    .append("\n");

            num++;
        }

        ps.close();
    }

    static int dijkstra(int n) {
        // 우선순위가 높은 것부터 내림차순
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0, graph[0][0]));

        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], MAX_VAL);
        }
        dist[0][0] = graph[0][0];

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            int x = now.x;
            int y = now.y;

            if (x == n - 1 && y == n - 1) {
                return now.cost;
            }

            if (dist[x][y] < now.cost) {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                    continue;
                }

                int cost = now.cost + graph[nx][ny];

                if (cost < dist[nx][ny]) {
                    dist[nx][ny] = cost;
                    pq.offer(new Node(nx, ny, cost));
                }
            }
        }

        return dist[n - 1][n - 1];
    }

    static class Node implements Comparable<Node> {
        int x, y, cost;

        public Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.cost = dist;
        }

        @Override
        public int compareTo(Node o) {
            // 순서 다르면 오류
            // 오름차순 정렬 (return 값이 양수면 자리바꿈)
            return cost - o.cost;
        }
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
