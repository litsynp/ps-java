package psjava.baekjoon;

import java.io.*;
import java.util.*;

/**
 * 2022.03.10 - 백준 - #2156 포도주 시식
 */
public class Q2156 {

  static int n;
  static int[] gs;
  static Integer[] dp;

  public static void main(String[] args) throws IOException {
    ps.init();

    // - 연속으로 최대 2번까지 마실 수 있다.

    n = Integer.parseInt(ps.br.readLine());
    gs = new int[n + 1];
    dp = new Integer[n + 1];
    for (int i = 1; i < n + 1; i++) {
      gs[i] = Integer.parseInt(ps.br.readLine());
    }

    dp[0] = 0;
    dp[1] = gs[1];

    if (n > 1) {
      dp[2] = gs[1] + gs[2];
    }

    ps.bw.write(ps.sb.append(recur(n)).append("\n").toString());

    ps.close();
  }

  static int recur(int i) {
    if (dp[i] == null) {
      int case1 = recur(i - 2) + gs[i];
      int case2 = recur(i - 3) + gs[i - 1] + gs[i];
      int case3 = recur(i - 1);
      dp[i] = Math.max(case1, Math.max(case2, case3));
    }
    return dp[i];
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
