package psjava.baekjoon;

import java.io.*;
import java.util.*;

/**
 * 2022.03.10 - 백준 - #1002 터렛
 */
public class Q1002 {

  static StringTokenizer st;

  public static void main(String[] args) throws IOException {

    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

      int T = Integer.parseInt(br.readLine());
      for (int i = 0; i < T; i++) {
        st = new StringTokenizer(br.readLine(), " ");
        int x1 = Integer.parseInt(st.nextToken());
        int y1 = Integer.parseInt(st.nextToken());
        int r1 = Integer.parseInt(st.nextToken());

        int x2 = Integer.parseInt(st.nextToken());
        int y2 = Integer.parseInt(st.nextToken());
        int r2 = Integer.parseInt(st.nextToken());

        if (x1 == x2 && y1 == y2 && r1 == r2) {
          // d = 0 이면서 r1 == r2 : 동일한 원 -> 무한대
          bw.write("-1\n");
          continue;
        }

        // 두 점의 중심 거리
        double dist = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
        int diff = Math.abs(r1 - r2);
        int sum = r1 + r2;

        if (diff < dist && dist < sum) {
          // 차 < d < 합 : 두 점에서 만난다.
          bw.write("2\n");
        } else if (dist == diff || dist == sum) {
          // 합 = d OR 차 = d : 한 점에서 만난다.
          bw.write("1\n");
        } else {
          // 만나지 않는다.
          bw.write("0\n");
        }
      }

      bw.flush();
    }
  }
}
