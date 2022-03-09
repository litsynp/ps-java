package psjava.baekjoon;

import java.io.*;
import java.util.*;

/**
 * 2022.03.09 - 백준 - #3009 네 번째 점
 */
public class Q3009 {

  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

      int[][] points = {{0, 0}, {0, 0}, {0, 0},};

      for (int i = 0; i < 3; i++) {
        st = new StringTokenizer(br.readLine(), " ");
        points[i][0] = Integer.parseInt(st.nextToken());
        points[i][1] = Integer.parseInt(st.nextToken());
      }

      // x에 같은 것이 2개, y에 같은 것이 2개 나와야 한다.
      int x, y;

      if (points[0][0] == points[1][0]) {
        // p1, p2의 x가 같으면 p3의 x 좌표가 4번째 점의 x 좌표
        x = points[2][0];
      } else {
        if (points[1][0] == points[2][0]) {
          // p2, p3의 x가 같으면 p1의 x 좌표가 4번째 점의 y 좌표
          x = points[0][0];
        } else {
          // p1, p3의 x가 같으면 p2의 x 좌표가 4번째 점의 y 좌표
          x = points[1][0];
        }
      }

      if (points[0][1] == points[1][1]) {
        // p1, p2의 y가 같으면 p3의 y 좌표가 4번째 점의 y 좌표
        y = points[2][1];
      } else {
        if (points[1][1] == points[2][1]) {
          // p2, p3의 y가 같으면 p1의 y 좌표가 4번째 점의 y 좌표
          y = points[0][1];
        } else {
          // p1, p3의 y가 같으면 p2의 y 좌표가 4번째 점의 y 좌표
          y = points[1][1];
        }
      }

      bw.write(x + " " + y + "\n");
      bw.flush();
    }
  }
}
