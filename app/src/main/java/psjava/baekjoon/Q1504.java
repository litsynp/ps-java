package psjava.baekjoon;

import java.io.*;
import java.util.*;

/**
 * 2022.03.19 - 백준 - #1504 특정한 최단 경로
 */
public class Q1504 {

  static int N, E;
  static List<List<Node>> graph = new ArrayList<>();
  static int[] dist;
  static final int MAX = 9999999;

  public static void main(String[] args) throws IOException {
    ps.init();
    {
      int[] in = ps.getIntInputs();
      N = in[0];
      E = in[1];

      for (int i = 0; i < N + 1; i++) {
        graph.add(new ArrayList<>());
      }
    }

    for (int i = 0; i < E; i++) {
      int[] in = ps.getIntInputs();
      int a = in[0];
      int b = in[1];
      int c = in[2];

      // 양방향 이동
      graph.get(a).add(new Node(b, c));
      graph.get(b).add(new Node(a, c));
    }

    int[] in = ps.getIntInputs();
    int v1 = in[0];
    int v2 = in[1];

    int res1 = dijkstra(1, v1) + dijkstra(v1, v2) + dijkstra(v2, N);
    int res2 = dijkstra(1, v2) + dijkstra(v2, v1) + dijkstra(v1, N);

    int result = Math.min(res1, res2);
    ps.sb.append(result >= MAX ? -1 : result).append("\n");

    ps.close();
  }

  static int dijkstra(int start, int end) {
    PriorityQueue<Node> pq = new PriorityQueue<>();
    pq.offer(new Node(start, 0));

    dist = new int[N + 1];
    Arrays.fill(dist, MAX);
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
