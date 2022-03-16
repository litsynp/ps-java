package psjava.baekjoon;

import java.io.*;
import java.util.*;

/**
 * 2022.03.16 - 백준 - #7569 토마토
 */
public class Q7569 {

  static int M, N, H;
  static int[][][] map;
  static int[] dx = {-1, 1, 0, 0, 0, 0};
  static int[] dy = {0, 0, -1, 1, 0, 0};
  static int[] dz = {0, 0, 0, 0, -1, 1};

  // 익은 토마토의 좌표가 담기는 queue
  static Queue<Point> q = new LinkedList<>();

  public static void main(String[] args) throws IOException {
    ps.init();
    {
      int[] in = Arrays.stream(ps.br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
      M = in[0];
      N = in[1];
      H = in[2];
      map = new int[H][N][M];
    }

    for (int x = 0; x < H; x++) {
      for (int y = 0; y < N; y++) {
        map[x][y] =
            Arrays.stream(ps.br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for (int z = 0; z < M; z++) {
          // 익었다면 queue에 추가
          if (map[x][y][z] == 1) {
            q.offer(new Point(x, y, z));
          }
        }
      }
    }

    int ans = bfs();
    ps.sb.append(ans).append("\n");
    ps.close();
  }

  static int bfs() {
    while (!q.isEmpty()) {
      Point p = q.poll();

      for (int i = 0; i < 6; i++) {
        int nx = p.x + dx[i];
        int ny = p.y + dy[i];
        int nz = p.z + dz[i];

        // 범위를 벗어나면 넘어감
        if (nx < 0 || nx >= H || ny < 0 || ny >= N || nz < 0 || nz >= M) {
          continue;
        }

        // 안익었다면 전파
        if (map[nx][ny][nz] == 0) {
          q.offer(new Point(nx, ny, nz));
          map[nx][ny][nz] = map[p.x][p.y][p.z] + 1;
        }
      }
    }

    // 총 날짜수 계산
    int days = Integer.MIN_VALUE;
    for (int x = 0; x < H; x++) {
      for (int y = 0; y < N; y++) {
        for (int z = 0; z < M; z++) {
          // 안익은 토마토가 있다면
          if (map[x][y][z] == 0) {
            return -1;
          }

          days = Math.max(days, map[x][y][z]);
        }
      }
    }

    // 처음부터 모두 익은 상태라면
    if (days == 1) {
      return 0;
    }

    return days - 1;
  }

  static class Point {
    int x, y, z;

    public Point(int x, int y, int z) {
      this.x = x;
      this.y = y;
      this.z = z;
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
