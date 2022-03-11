package psjava.baekjoon;

import java.io.*;
import java.util.*;

/**
 * 2022.03.11 - 백준 - #11053 가장 긴 증가하는 부분 수열
 */
public class Q11053 {

  // 가장 긴 증가하는 부분 수열의 길이
  static Integer[] dp;
  static int[] seq;

  public static void main(String[] args) throws IOException {
    ps.init();

    int N = Integer.parseInt(ps.br.readLine());
    seq = Arrays.stream(ps.br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    dp = new Integer[N];

    // 0 ~ N-1 까지 모든 부분수열 탐색
    for (int i = 0; i < N; i++) {
      recur(i);
    }

    int max = dp[0];

    // 최댓값 찾기
    for (int i = 1; i < N; i++) {
      max = Math.max(max, dp[i]);
    }

    ps.bw.write(ps.sb.append(max).append("\n").toString());
    ps.close();
  }

  static int recur(int n) {
    if (dp[n] == null) {
      dp[n] = 1;

      // N 이전 노드와의 비교
      for (int i = n - 1; i >= 0; i--) {
        // 이전 노드 중 seq[n]의 값보다 작은 것을 발견할 경우
        if (seq[i] < seq[n]) {
          dp[n] = Math.max(dp[n], recur(i) + 1);
        }
      }
    }

    return dp[n];
  }

  static class ps {

    public static BufferedReader br;
    public static BufferedWriter bw;
    public static StringBuilder sb = new StringBuilder();

    static void init() {
      br = new BufferedReader(new InputStreamReader(System.in));
      bw = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    static void close() throws IOException {
      br.close();
      bw.close();
    }
  }
}
