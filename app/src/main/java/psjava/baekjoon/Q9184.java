package psjava.baekjoon;

import java.io.*;
import java.util.*;

/**
 * 2022.03.10 - 백준 - #9184 신나는 함수 실행
 */
public class Q9184 {

  // memo for Dynamic Programming
  // -50부터 0까지 값이 1이며, 20이 넘으면 w(20, 20, 20)이므로 0부터 20까지만 가능하면 됨
  static int[][][] memo = new int[21][21][21];

  static StringBuilder sb = new StringBuilder();

  public static void main(String[] args) throws IOException {

    // -50부터 0까지는 1의 값
    memo[0][0][0] = 1;

    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

      while (true) {
        int inputs[] =
            Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int a = inputs[0];
        int b = inputs[1];
        int c = inputs[2];

        if (a == -1 && b == -1 && c == -1) {
          break;
        }

        sb.append("w(").append(a).append(", ").append(b).append(", ").append(c).append(") = ")
            .append(w(a, b, c)).append("\n").toString();

        bw.write(sb.toString());
        sb.setLength(0);
      }
      bw.flush();
    }
  }

  static int w(int a, int b, int c) {
    if (a <= 0 || b <= 0 || c <= 0) {
      return 1;
    } else if (a > 20 || b > 20 || c > 20) {
      return w(20, 20, 20);
    } else if (memo[a][b][c] != 0) {
      return memo[a][b][c];
    } else if (a < b && b < c) {
      memo[a][b][c] = w(a, b, c - 1) + w(a, b - 1, c - 1) - w(a, b - 1, c);
      return memo[a][b][c];
    } else {
      memo[a][b][c] =
          w(a - 1, b, c) + w(a - 1, b - 1, c) + w(a - 1, b, c - 1) - w(a - 1, b - 1, c - 1);
      return memo[a][b][c];
    }
  }
}
