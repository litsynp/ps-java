package psjava.baekjoon;

import java.io.*;
import java.util.*;

/**
 * 2022.03.17 - 백준 - #1753 최단경로
 */
public class Q1753 {

  static int V, E, K;
  static List<List<Node>> graph = new ArrayList<>();

  // 최단 거리 테이블
  static int[] dist;

  public static void main(String[] args) throws IOException {
    ps.init();

    {
      int[] in = ps.getIntInputs();
      V = in[0];
      E = in[1];
      K = Integer.parseInt(ps.br.readLine());

      for (int i = 0; i < V + 1; i++) {
        graph.add(new ArrayList<>());
      }

      // 거리 값 최대로 초기화
      dist = new int[V + 1];
      Arrays.fill(dist, Integer.MAX_VALUE);
    }

    for (int e = 0; e < E; e++) {
      int[] in = ps.getIntInputs();
      int u = in[0];
      int v = in[1];
      int w = in[2];

      // 방향 그래프
      graph.get(u).add(new Node(v, w));
    }


    dijkstra(K);

    // 결과 출력
    for (int i = 1; i < V + 1; i++) {
      if (dist[i] == Integer.MAX_VALUE) {
        ps.sb.append("INF\n");
      } else {
        ps.sb.append(dist[i]).append("\n");
      }
    }

    ps.close();
  }

  static void dijkstra(int start) {
    // (최소 힙) 우선순위 큐
    PriorityQueue<Node> pq = new PriorityQueue<>();

    pq.offer(new Node(start, 0));
    dist[start] = 0;

    while (!pq.isEmpty()) {
      // 가장 최단 거리가 짧은 노드에 대한 정보 꺼내기
      Node now = pq.poll();

      // 현재 노드가 이미 처리된 적이 있는 노드라면 (최초 값보다 작다면) 무시
      if (dist[now.id] < now.weight) {
        continue;
      }

      // 현재 노드와 연결된 다른 노드를 확인
      for (Node v : graph.get(now.id)) {
        // 현재 노드로부터 연결된 해당 노드의 거리
        int cost = dist[now.id] + v.weight;

        // 현재 노드를 거쳐서 다른 노드로 이동하는 거리가 더 짧은 경우
        // 즉, 해당 노드의 기존의 이동 거리와의 비교
        if (cost < dist[v.id]) {
          dist[v.id] = cost;
          pq.offer(new Node(v.id, cost));
        }
      }
    }
  }

  static class Node implements Comparable<Node> {
    int id, weight;

    Node(int id, int weight) {
      this.id = id;
      this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
      return this.weight - o.weight;
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
