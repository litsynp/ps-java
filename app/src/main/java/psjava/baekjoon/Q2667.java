package psjava.baekjoon;

import java.io.*;
import java.util.*;

/**
 * 2022.03.16 - 백준 - #2667 단지번호붙이기
 */
public class Q2667 {

  static int N;
  static int[][] map;
  static boolean[][] visited;
  static int[] dx = {-1, 1, 0, 0};
  static int[] dy = {0, 0, -1, 1};

  static int[] houses;
  static int unitNum = 0;

  public static void main(String[] args) throws IOException {
    ps.init();

    N = Integer.parseInt(ps.br.readLine());
    map = new int[N][N];
    visited = new boolean[N][N];
    houses = new int[N * N];

    for (int i = 0; i < N; i++) {
      map[i] = Arrays.stream(ps.br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
    }

    int ans = 0;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        // 존재하는 방문한 적 없는 집에 대해서 DFS 실행
        if (map[i][j] == 1 && !visited[i][j]) {
          // 새로운 단지 번호 부여
          unitNum++;

          // 주변의 visited는 DFS가 실행되며 true가 대입되므로, 다음 반복문에서 visited는 true
          dfs(i, j);
        }
      }
    }

    // 단지 내 집의 수 오름차순 정렬
    Arrays.sort(houses);

    // 총 단지수
    ps.sb.append(unitNum).append("\n");

    // 각 단지 내 집의 수
    for (int i = 0; i < houses.length; i++) {
      if (houses[i] != 0) {
        ps.sb.append(houses[i]).append("\n");
      }
    }
    ps.close();
  }

  static void dfs(int x, int y) {
    visited[x][y] = true;
    houses[unitNum]++;

    for (int i = 0; i < 4; i++) {
      int nx = x + dx[i];
      int ny = y + dy[i];

      // 주어진 좌표가 boundary를 벗어나지 않는다면,
      // 집이 존재하며, 방문한 적 없는 집일 경우, DFS 실행
      if (nx >= 0 && nx < N && ny >= 0 && ny < N && map[nx][ny] == 1 && !visited[nx][ny]) {
        dfs(nx, ny);
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
