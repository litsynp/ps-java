package psjava.thiscotest;

import java.io.*;
import java.util.*;

/**
 * 2022.03.05 - 이코테 - 예제 4-1 상하좌우
 */
public class Q4_1 {

  public static StringBuilder sb = new StringBuilder();
  public static StringTokenizer st;
  public static int x = 1;
  public static int y = 1;
  public static int[] dx = {-1, 1, 0, 0};
  public static int[] dy = {0, 0, -1, 1};

  public static void main(String[] args) throws IOException {

    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      int N = Integer.parseInt(br.readLine());
      st = new StringTokenizer(br.readLine(), " ");

      while (st.hasMoreTokens()) {
        char move = st.nextToken().charAt(0);
        int moveNum;
        if (move == 'U') {
          moveNum = 0;
        } else if (move == 'D') {
          moveNum = 1;
        } else if (move == 'L') {
          moveNum = 2;
        } else {
          moveNum = 3;
        }

        int nx = x + dx[moveNum];
        int ny = y + dy[moveNum];

        if (nx > 0 && nx <= N && ny > 0 && ny <= N) {
          x = nx;
          y = ny;
        }
      }

      System.out.printf("%d %d\n", x, y);
    }
  }
}
