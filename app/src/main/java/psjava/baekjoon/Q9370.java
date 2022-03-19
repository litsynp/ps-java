package psjava.baekjoon;

import java.io.*;
import java.util.*;

/**
 * 2022.03.19 - 백준 - #9370 미확인 도착지
 */
public class Q9370 {

  static int T, n, m, t, s, g, h;
  static List<List<Node>> graph;;
  static int[] dist;
  static final int INF = (int) 1e9;
  static List<Integer> x;

  public static void main(String[] args) throws IOException {
    ps.init();

    T = Integer.parseInt(ps.br.readLine());

    for (int i = 0; i < T; i++) {
      {
        // 교차로, 도로, 목적지 후보의 개수
        int[] in = ps.getIntInputs();
        n = in[0];
        m = in[1];
        t = in[2];
      }

      {
        int[] in = ps.getIntInputs();
        s = in[0]; // 예술가들의 출발지

        // g와 h '교차로' 사이에 있는 도로를 지나갔다
        g = in[1];
        h = in[2];
      }

      // 그래프 초기화
      graph = new ArrayList<>();
      for (int j = 0; j <= n; j++) {
        graph.add(new ArrayList<>());
      }
      dist = new int[n + 1];

      for (int j = 0; j < m; j++) {
        int[] in = ps.getIntInputs();
        int a = in[0];
        int b = in[1];
        int d = in[2];

        // 양방향 도로
        graph.get(a).add(new Node(b, d));
        graph.get(b).add(new Node(a, d));
      }

      // 목적지 후보
      x = new ArrayList<>();
      for (int j = 0; j < t; j++) {
        x.add(Integer.parseInt(ps.br.readLine()));
      }

      // s -> (g <-> h) -> t 중 하나
      // 즉, 후보군 x_1, x_2, ..., x_t 마다 dijkstra(h, x_t)
      // dijkstra(s, g) + dijkstra(h, x_t)
      // dijkstra(s, h) + dijkstra(h, x_t)
      // 가능한 경우가 하나라도 있으면, break 후 그 목적지를 ans에 추가
      PriorityQueue<Integer> pq = new PriorityQueue<>();
      for (int n : x) {
        long res1 = dijkstra(s, g) + dijkstra(g, h) + dijkstra(h, n);
        long res2 = dijkstra(s, h) + dijkstra(h, g) + dijkstra(g, n);
        long res3 = dijkstra(s, n);

        if (Math.min(res1, res2) == res3) {
          pq.offer(n);
        }
      }

      // 테스트 케이스 T에 대한 결과 출력
      while (!pq.isEmpty()) {
        ps.sb.append(pq.poll()).append(" ");
      }
      ps.sb.append("\n");
    }

    ps.close();
  }

  static int dijkstra(int start, int end) {
    PriorityQueue<Node> pq = new PriorityQueue<>();
    pq.offer(new Node(start, 0));

    Arrays.fill(dist, INF);
    dist[start] = 0;

    while (!pq.isEmpty()) {
      Node now = pq.poll();

      if (dist[now.id] < now.weight) {
        continue;
      }

      for (Node v : graph.get(now.id)) {
        int cost = dist[now.id] + v.weight;

        if (cost < dist[v.id]) {
          dist[v.id] = cost;
          pq.offer(new Node(v.id, cost));
        }
      }
    }

    return dist[end];
  }

  static class Node implements Comparable<Node> {
    int id, weight;

    Node(int id, int weight) {
      this.id = id;
      this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
      return weight - o.weight;
    }
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
