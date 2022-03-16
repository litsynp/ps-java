package psjava.baekjoon;

import java.io.*;
import java.util.*;

/**
 * 2022.03.16 - 백준 - #7576 토마토
 */
public class Q7576 {

  static int M, N;
  static int[][] map;
  static int[] dx = {-1, 1, 0, 0};
  static int[] dy = {0, 0, -1, 1};
  static Queue<int[]> q = new LinkedList<>();


  public static void main(String[] args) throws IOException {
    ps.init();

    {
      int[] in = Arrays.stream(ps.br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
      M = in[0];
      N = in[1];
      map = new int[N][M];
    }

    for (int i = 0; i < N; i++) {
      int[] in = Arrays.stream(ps.br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
      map[i] = in;

      // 익은 토마토라면 queue에 추가
      for (int j = 0; j < in.length; j++) {
        if (map[i][j] == 1) {
          q.offer(new int[] {i, j});
        }
      }
    }

    int ans = bfs();
    ps.sb.append(ans).append("\n");
    ps.close();
  }

  static int bfs() {
    while (!q.isEmpty()) {
      int[] v = q.poll();
      int x = v[0];
      int y = v[1];

      for (int i = 0; i < 4; i++) {
        int nx = x + dx[i];
        int ny = y + dy[i];

        // 해당 좌표에 안익은 토마토가 존재한다면 전파
        if (nx >= 0 && nx < N && ny >= 0 && ny < M && map[nx][ny] == 0) {
          q.offer(new int[] {nx, ny});
          map[nx][ny] = map[x][y] + 1;
        }
      }
    }

    // 결과 체크
    int days = Integer.MIN_VALUE;
    for (int x = 0; x < N; x++) {
      for (int y = 0; y < M; y++) {
        // 안익은 토마토가 존재한다면
        if (map[x][y] == 0) {
          return -1;
        }

        // 걸린 날짜 최댓값
        days = Math.max(days, map[x][y]);
      }
    }

    // 토마토가 처음부터 모두 익은 상태라면
    if (days == 1) {
      return 0;
    } else {
      return days - 1;
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
