package psjava.baekjoon;

import java.io.*;
import java.util.*;

/**
 * 2022.03.15 - 백준 - #1260 DFS와 BFS
 */
public class Q1260 {

  static int N;
  static int M;
  static int V;
  static int[][] map;
  static boolean[] visited;

  public static void main(String[] args) throws IOException {
    ps.init();

    int[] in = Arrays.stream(ps.br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    N = in[0];
    M = in[1];
    V = in[2];
    map = new int[N + 1][N + 1];

    // 초기 경로 세팅
    for (int i = 0; i < M; i++) {
      int[] in2 = Arrays.stream(ps.br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
      int a = in2[0];
      int b = in2[1];

      // 인접 행렬 방식
      map[a][b] = 1;
      map[b][a] = 1;
    }

    visited = new boolean[N + 1];
    dfs(V);

    ps.sb.append("\n");

    visited = new boolean[N + 1];
    bfs(V);

    ps.sb.append("\n");
    ps.close();
  }

  static void dfs(int v) {
    visited[v] = true;
    ps.sb.append(v).append(" ");

    for (int i = 1; i < map.length; i++) {
      // 방문하지 않은 정점이 있는 경우
      if (map[v][i] == 1 && visited[i] == false) {
        dfs(i);
      }
    }
  }

  static void bfs(int start) {
    visited[start] = true;
    ps.sb.append(start).append(" ");

    Queue<Integer> q = new LinkedList<>();
    q.add(start);

    while (!q.isEmpty()) {
      int v = q.peek();
      q.poll();

      for (int i = 1; i < map.length; i++) {
        // 방문하지 않은 정점이 있는 경우
        if (map[v][i] == 1 && visited[i] == false) {
          q.add(i);
          visited[i] = true;
          ps.sb.append(i).append(" ");
        }
      }
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
  }
}
