package psjava.baekjoon;

import java.io.*;
import java.util.*;

/**
 * 2022.03.17 - 백준 - #7562 나이트의 이동
 */
public class Q7562 {

  static int T;
  static int[][] map;
  static boolean[][] visited;
  static int[] dx = {-2, -2, -1, -1, 1, 1, 2, 2};
  static int[] dy = {-1, 1, -2, 2, -2, 2, -1, 1};

  public static void main(String[] args) throws IOException {
    ps.init();

    T = Integer.parseInt(ps.br.readLine());

    // 각 테스트 케이스마다 실행
    for (int t = 0; t < T; t++) {
      int l = Integer.parseInt(ps.br.readLine());

      int[] in = Arrays.stream(ps.br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
      Point src = new Point(in[0], in[1], 0);

      int[] in2 = Arrays.stream(ps.br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
      Point tgt = new Point(in2[0], in2[1], Integer.MAX_VALUE);

      map = new int[l][l];
      visited = new boolean[l][l];
      int result = bfs(l, src, tgt);
      ps.sb.append(result).append("\n");
    }

    ps.close();
  }

  static int bfs(int l, Point src, Point tgt) {
    Queue<Point> q = new LinkedList<>();
    q.offer(src);
    visited[src.x][src.y] = true;

    while (!q.isEmpty()) {
      Point p = q.poll();

      if (p.equals(tgt)) {
        return p.moves;
      }

      for (int i = 0; i < dx.length; i++) {
        int nx = p.x + dx[i];
        int ny = p.y + dy[i];

        if (isInside(nx, ny, l) && !visited[nx][ny]) {
          Point np = new Point(nx, ny, p.moves + 1);
          q.offer(np);
          visited[nx][ny] = true;
        }
      }
    }

    return 0;
  }

  static boolean isInside(int x, int y, int l) {
    return x >= 0 && x < l && y >= 0 && y < l;
  }

  static class Point {
    int x, y, moves;

    Point(int x, int y, int moves) {
      this.x = x;
      this.y = y;
      this.moves = moves;
    }

    public boolean equals(Point p) {
      return this.x == p.x && this.y == p.y;
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
