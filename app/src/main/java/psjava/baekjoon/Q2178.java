package psjava.baekjoon;

import java.io.*;
import java.util.*;

/**
 * 2022.03.16 - 백준 - #2178 미로 탐색
 */
public class Q2178 {

  static int N, M;
  static int[][] map;
  static int[] dx = {0, 0, -1, 1};
  static int[] dy = {-1, 1, 0, 0};
  static int ans = 0;

  public static void main(String[] args) throws IOException {
    ps.init();

    {
      int[] in = Arrays.stream(ps.br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
      N = in[0];
      M = in[1];
      map = new int[N][M];
    }

    for (int i = 0; i < N; i++) {
      map[i] = Arrays.stream(ps.br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
    }

    bfs(0, 0);
    ps.sb.append(map[N - 1][M - 1]).append("\n");

    ps.close();
  }

  static void bfs(int startX, int startY) {
    Queue<int[]> q = new LinkedList<>();
    q.offer(new int[] {startX, startY});
    map[startX][startY] = 1;

    while (!q.isEmpty()) {
      int[] v = q.poll();
      int x = v[0];
      int y = v[1];

      for (int i = 0; i < 4; i++) {
        int nx = x + dx[i];
        int ny = y + dy[i];

        if (nx >= 0 && nx < N && ny >= 0 && ny < M && map[nx][ny] == 1) {
          q.offer(new int[] {nx, ny});
          map[nx][ny] = map[x][y] + 1;
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
