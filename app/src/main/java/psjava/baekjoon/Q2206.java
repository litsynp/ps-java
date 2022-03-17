package psjava.baekjoon;

import java.io.*;
import java.util.*;

/**
 * 2022.03.17 - 백준 - #2206 벽 부수고 이동하기
 */
public class Q2206 {

  static int N, M;
  static int[][] map;
  static int[][] visited;
  static int ans;

  static int[] dx = {-1, 1, 0, 0};
  static int[] dy = {0, 0, -1, 1};

  public static void main(String[] args) throws IOException {
    ps.init();

    {
      int[] in = Arrays.stream(ps.br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
      N = in[0];
      M = in[1];
      map = new int[N][M];
      visited = new int[N][M];

      for (int[] row : visited) {
        Arrays.fill(row, Integer.MAX_VALUE);
      }
    }

    for (int i = 0; i < N; i++) {
      map[i] = Arrays.stream(ps.br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
    }

    bfs();

    // 결과값 출력
    if (visited[N - 1][M - 1] == Integer.MAX_VALUE) {
      ans = -1;
    }
    ps.sb.append(ans).append("\n");
    ps.close();
  }

  static class Point {
    int x, y, moves, crashes;

    Point(int x, int y, int moves, int crashes) {
      this.x = x;
      this.y = y;
      this.moves = moves;
      this.crashes = crashes;
    }
  }

  static void bfs() {
    Queue<Point> q = new LinkedList<>();
    q.offer(new Point(0, 0, 1, 0));
    visited[0][0] = 0;

    while (!q.isEmpty()) {
      Point p = q.poll();

      // 도착했는지 확인
      if (p.x == N - 1 && p.y == M - 1) {
        ans = p.moves;
        return;
      }

      // 사방 확인
      for (int i = 0; i < 4; i++) {
        int nx = p.x + dx[i];
        int ny = p.y + dy[i];

        // 경계 밖에 있는지 확인
        if (!isInside(nx, ny)) {
          continue;
        }

        if (p.crashes >= visited[nx][ny]) {
          // 여태까지의 경로의 crash 횟수가
          // 다음 정점의 crash 횟수보다 큰 경우,
          // 해당 경로는 유효하지 않음.
          continue;
        }

        // 유효한 경로인지 확인
        if (map[nx][ny] == 0) {
          // 벽으로 막히지 않은 경우,
          // 이동 횟수만 증가시키고 queue에 넣는다.
          visited[nx][ny] = p.crashes;
          q.offer(new Point(nx, ny, p.moves + 1, p.crashes));
        } else {
          // 벽으로 막힌 경우,
          // 해당 경로에서 한번도 벽을 부순 적이 없다면 (p.crash == 0)
          // 이동 횟수와 충돌 횟수를 둘 다 증가시키고 queue에 넣는다.
          if (p.crashes == 0) {
            visited[nx][ny] = p.crashes + 1;
            q.offer(new Point(nx, ny, p.moves + 1, p.crashes + 1));
          }
        }
      }
    }
  }

  static boolean isInside(int x, int y) {
    return x >= 0 && x < N && y >= 0 && y < M;
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
