package psjava.algorithms.graph;

import java.io.*;
import java.util.*;

import psjava.util.*;

/**
 * BFS (너비 우선 탐색, Breadth-First Search) - 인접 리스트 방식
 *
 * 리스트를 이용해 BFS를 실시한다.
 *
 * https://23log.tistory.com/92
 *
 * https://ajdahrdl.tistory.com/98
 */
public class BfsAdjList {

  static int N, M, V;
  static List<List<Integer>> map = new ArrayList<>();
  static boolean[] visited;

  public static void bfs(int start) {
    Queue<Integer> q = new LinkedList<>();

    q.offer(start);
    visited[start] = true;
    ps.sb.append(start).append(" ");

    while (!q.isEmpty()) {
      int v = q.poll();

      for (int i = 0; i < map.get(v).size(); i++) {
        int temp = map.get(v).get(i);

        if (!visited[temp]) {
          visited[temp] = true;
          q.offer(temp);
          ps.sb.append(temp).append(" ");
        }
      }
    }
  }

  public static void main(String[] args) throws IOException {
    ps.init();

    int[] in = Arrays.stream(ps.br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    N = in[0];
    M = in[1];
    V = in[2];
    visited = new boolean[N + 1];

    // 노드의 개수 N + 1개만큼 인접 리스트 초기화
    for (int i = 0; i < N + 1; i++) {
      map.add(new ArrayList<>());
    }

    // 초기 경로 세팅
    for (int i = 0; i < M; i++) {
      int[] in2 = Arrays.stream(ps.br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
      int a = in2[0];
      int b = in2[1];

      // 양방향 삽입
      map.get(a).add(b);
      map.get(a).add(a);
    }

    bfs(V);

    ps.sb.append("\n");
    ps.close();
  }
}
