package psjava.algorithm.graph;

import java.io.*;
import java.util.*;

import psjava.util.ps;

/**
 * Dijkstra's Algorithm (다익스트라 알고리즘)
 *
 * - A에서 B로 가는 최단 경로를 구하는 알고리즘.
 *
 * - 시간 복잡도는 O(|E| log |V|).
 *
 * - A -> V -> B 를 구하고 싶다면, Dijkstra를 여러 번 실행하면 된다.
 *
 * Q1753, Q1504, Q9370 참고
 */
public class Dijkstra {

  static int N, E;
  static List<List<Node>> graph = new ArrayList<>();
  static int[] dist;
  static final int MAX_VAL = (int) 1e9;

  public static void main(String[] args) throws IOException {
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

    int result = dijkstra(1, N);
    ps.sb.append(result >= MAX_VAL ? -1 : result).append("\n");

    ps.close();
  }

  static int dijkstra(int start, int end) {
    PriorityQueue<Node> pq = new PriorityQueue<>();
    pq.offer(new Node(start, 0));

    dist = new int[N + 1];
    Arrays.fill(dist, MAX_VAL);
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
}
