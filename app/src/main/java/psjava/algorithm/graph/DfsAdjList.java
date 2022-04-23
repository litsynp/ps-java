package psjava.algorithm.graph;

import java.io.*;
import java.util.*;

import psjava.util.*;

/**
 * DFS (깊이 우선 탐색, Depth-First Search) - 인접 리스트 방식
 *
 * 리스트를 이용해 DFS를 실시한다.
 *
 * https://23log.tistory.com/92
 *
 * https://ajdahrdl.tistory.com/98
 */
public class DfsAdjList {

  static int N, M, V;
  static List<List<Integer>> map = new ArrayList<>();
  static boolean[] visited;

  public static void dfs(int v) {
    visited[v] = true;
    ps.sb.append(v).append(" ");

    for (int i = 0; i < map.get(v).size(); i++) {
      int temp = map.get(v).get(i);

      if (!visited[temp]) {
        dfs(temp);
      }
    }
  }

  public static void main(String[] args) throws IOException {

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

    dfs(V);

    ps.sb.append("\n");
    ps.close();
  }
}
