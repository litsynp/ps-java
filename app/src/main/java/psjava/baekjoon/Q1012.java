package psjava.baekjoon;

import java.io.*;
import java.util.*;

/**
 * 2022.03.16 - 백준 - #1012 유기농 배추
 */
public class Q1012 {

  static int T, N, M, K;
  static int[][] map;
  static int[] dx = {-1, 1, 0, 0};
  static int[] dy = {0, 0, -1, 1};
  static int ans;

  public static void main(String[] args) throws IOException {
    ps.init();

    T = Integer.parseInt(ps.br.readLine());

    // T개의 테스트 케이스
    for (int i = 0; i < T; i++) {
      int[] in = Arrays.stream(ps.br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
      M = in[0]; // 가로길이
      N = in[1]; // 세로길이
      K = in[2]; // 배추가 심어져 있는 위치의 개수
      map = new int[N][M];

      for (int j = 0; j < K; j++) {
        int[] in2 =
            Arrays.stream(ps.br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int x = in2[0];
        int y = in2[1];
        map[y][x] = 1;
      }

      int ans = 0;
      for (int y = 0; y < N; y++) {
        for (int x = 0; x < M; x++) {
          if (dfs(y, x)) {
            ans++;
          }
        }
      }

      // 정답 출력
      ps.sb.append(ans).append("\n");
    }

    ps.close();
  }

  static boolean dfs(int y, int x) {
    if (x >= 0 && x < M && y >= 0 && y < N && map[y][x] == 1) {
      // 방문 처리
      map[y][x] = 0;

      for (int i = 0; i < 4; i++) {
        int nx = x + dx[i];
        int ny = y + dy[i];
        dfs(ny, nx);
      }
      return true;
    }

    return false;
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
