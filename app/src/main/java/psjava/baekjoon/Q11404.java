package psjava.baekjoon;

import java.io.*;
import java.util.*;

/**
 * 2022.03.20 - 백준 - #11404 플로이드
 */
public class Q11404 {

  static int N, M;
  static int[][] graph;
  static final int INF = (int) 1e9;

  public static void main(String[] args) throws IOException {
    ps.init();

    N = Integer.parseInt(ps.br.readLine());
    M = Integer.parseInt(ps.br.readLine());

    graph = new int[N + 1][N + 1];
    for (int[] row : graph) {
      Arrays.fill(row, INF);
    }

    // 자기 자신으로 가는 비용은 0으로 초기화
    for (int i = 1; i <= N; i++) {
      for (int j = 1; j <= N; j++) {
        if (i == j) {
          graph[i][j] = 0;
        }
      }
    }

    for (int i = 0; i < M; i++) {
      int[] in = ps.getIntInputs();
      int a = in[0];
      int b = in[1];
      int c = in[2];

      // 시작 도시와 도착 도시를 연결하는 노선은 하나가 아닐 수 있다.
      // 따라서 여러 번 입력 받았을 경우, 최단 노선을 저장한다.
      graph[a][b] = Math.min(c, graph[a][b]);
    }

    floydWarshall();

    for (int i = 1; i <= N; i++) {
      for (int j = 1; j <= N; j++) {
        if (graph[i][j] == INF) {
          // i -> j가 불가능한 경우 0 출력
          ps.sb.append(0).append(" ");
        } else {
          ps.sb.append(graph[i][j]).append(" ");
        }
      }
      ps.sb.append("\n");
    }

    ps.close();
  }

  private static void floydWarshall() {
    for (int k = 1; k <= N; k++) {
      for (int a = 1; a <= N; a++) {
        for (int b = 1; b <= N; b++) {
          graph[a][b] = Math.min(graph[a][b], graph[a][k] + graph[k][b]);
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

    public static int[] getIntInputs() throws IOException {
      return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
  }
}
