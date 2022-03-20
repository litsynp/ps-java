package psjava.baekjoon;

import java.io.*;
import java.util.*;

/**
 * 2022.03.19 - 백준 - #11657 타임머신
 */
public class Q11657 {

  static int N, E;
  static List<Edge> graph;
  static long[] dist;
  static final int INF = Integer.MAX_VALUE;

  public static void main(String[] args) throws IOException {
    ps.init();
    {
      int[] in = ps.getIntInputs();
      N = in[0];
      E = in[1];
    }

    // 최단거리 테이블 초기화
    dist = new long[N + 1];
    Arrays.fill(dist, INF);

    graph = new ArrayList<>();

    for (int i = 0; i < E; i++) {
      int[] in = ps.getIntInputs();
      int u = in[0];
      int v = in[1];
      int w = in[2];

      graph.add(new Edge(u, v, w));
    }

    // 벨만 포드 알고리즘 실행 (true: 음수 사이클 존재, false: 음수 사이클 존재 X)
    if (bellmanFord(1)) {
      ps.sb.append(-1).append("\n");
    } else {
      // 1번 노드를 제외한 다른 모든 노드로 가기 위한 최단 거리 출력
      for (int i = 2; i <= N; i++) {
        if (dist[i] == INF) {
          ps.sb.append(-1).append("\n");
        } else {
          ps.sb.append(dist[i]).append("\n");
        }
      }
    }

    ps.close();
  }

  public static boolean bellmanFord(int start) {
    dist[start] = 0;

    // 정점의 개수 n번 동안 최단거리 초기화 작업 반복
    for (int i = 1; i <= N; i++) {

      // 매 반복마다 모든 간선 확인
      for (Edge now : graph) {
        if (dist[now.u] == INF) {
          continue;
        }

        // 현재 간선을 거쳐서 다른 노드로 이동하는 거리가 짧은 경우
        long cost = dist[now.u] + now.cost;
        if (dist[now.v] > cost) {
          dist[now.v] = cost;

          // n번째 라운드에서 값이 갱신된다면 음수 순환 존재
          if (i == N) {
            return true;
          }
        }
      }
    }

    return false;
  }

  public static class Edge {
    int u, v, cost;

    Edge(int u, int v, int cost) {
      this.u = u;
      this.v = v;
      this.cost = cost;
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
