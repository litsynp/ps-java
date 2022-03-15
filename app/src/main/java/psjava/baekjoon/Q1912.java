package psjava.baekjoon;

import java.io.*;
import java.util.*;

/**
 * 2022.03.15 - 백준 - #1912 연속합
 */
public class Q1912 {

  private static int n;
  private static int[] arr;
  private static Integer[] dp;

  public static void main(String[] args) throws IOException {
    ps.init();

    n = Integer.parseInt(ps.br.readLine());
    arr = Arrays.stream(ps.br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    dp = new Integer[n];

    // 첫 번째 초기화 - 양수/음수 상관 없이 1개 이상 선택해야 함
    dp[0] = arr[0];
    int max = arr[0];

    for (int i = 1; i < n; i++) {
      // 이전 DP값 + 현재 숫자 v.s. 현재 숫자 (클 경우 새로운 배열 시작)를 DP에 저장
      dp[i] = Math.max(dp[i - 1] + arr[i], arr[i]);

      // 최댓값 갱신
      max = Math.max(max, dp[i]);
    }

    ps.sb.append(max).append("\n");
    ps.close();
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
