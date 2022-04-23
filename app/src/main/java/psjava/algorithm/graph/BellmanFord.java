package psjava.algorithm.graph;

import java.io.*;
import java.util.*;

import psjava.util.ps;

/**
 * Bellman-Ford Algorithm (벨만-포드 알고리즘)
 *
 * 음수 간선이 포함된 상황에서의 최단 거리 문제를 풀 때 사용한다.
 *
 * 또한, 음수 간선의 순환을 탐지할 수 있다.
 *
 * 시간 복잡도는 O(VE).
 *
 * - Dijkstra's algorithm과 달리 음수의 경로값에도 사용 가능하다.
 *
 * - 음의 값이 누적되는 음수 사이클이 존재하면 의미 없는 값을 반환한다.
 *
 * d[T] <= d[S] + w(S, T)
 *
 * - d: 시작점에서 해당 정점의 거리
 *
 * - T: 해당 간선이 도달하고자 하는 정점
 *
 * - S: 해당 간선의 시작점
 *
 * - w: 해당 간선의 가중치
 *
 * [음수 사이클 판단]
 *
 * - e.g.) 정점의 개수 = 4 : 최단 경로는 최대 3의 크기를 가진다.
 *
 * - 정점의 개수가 N인 경우, 최단 경로의 크기는 최대 |N| - 1이 된다.
 *
 * - 즉, 사이클을 순환하여, 최단 경로 크기가 커지는 것을 제한한다.
 *
 * https://loosie.tistory.com/162
 *
 * https://sorjfkrh5078.tistory.com/30
 *
 * * 예시는 BJ Q11657.
 */
public class BellmanFord {

  static int N, E;
  static List<Edge> graph;
  static long[] dist; // long으로 안하면 음수 사이클 발생시 underflow 때문에 출력 오류 날 수 있음
  static final int INF = Integer.MAX_VALUE;

  public static void main(String[] args) throws IOException {
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

      // graph에 모든 간선 등록
      graph.add(new Edge(u, v, w));
    }

    // 벨만 포드 알고리즘 실행 (true: 음수 사이클 존재, false: 음수 사이클 존재 X)
    if (bellmanFord(1)) {
      ps.sb.append(-1).append("\n");
    } else {
      // 1번 노드를 제외한 다른 모든 노드로 가기 위한 최단 거리 출력
      for (int i = 2; i < N + 1; i++) {
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

    // 정점의 수만큼 반복 (음수 간선 순환 체크 안하려면 N-1 번 반복)
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
}
