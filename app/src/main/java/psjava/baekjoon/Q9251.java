package psjava.baekjoon;

import java.io.*;
import java.util.*;

/**
 * 2022.03.14 - 백준 - #9251 LCS
 */
public class Q9251 {

  static int[][] lcs;

  public static void main(String[] args) throws IOException {
    ps.init();

    String s1 = ps.br.readLine().strip();
    String s2 = ps.br.readLine().strip();
    lcs = new int[s1.length() + 1][s2.length() + 1];

    calcLcs(s1, s2);
    int ans = lcs[s1.length()][s2.length()];

    ps.bw.write(ps.sb.append(ans).append("\n").toString());

    ps.close();
  }

  /**
   * 문자열 A와 문자열 B의 공통 부분 수열 (LCS, Longest Common Subsequence)의 길이를 반환한다.
   *
   * 결과적으로 LCS 배열 마지막에는 LCS의 길이를 볼 수 있다.
   *
   * @param s1 문자열 A
   * @param s2 문자열 B
   */
  public static void calcLcs(final String s1, final String s2) {

    for (int i = 1; i <= s1.length(); i++) {
      for (int j = 1; j <= s2.length(); j++) {

        if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
          lcs[i][j] = lcs[i - 1][j - 1] + 1;
        } else {
          lcs[i][j] = Math.max(lcs[i][j - 1], lcs[i - 1][j]);
        }
      }
    }
  }

  static class ps {

    public static BufferedReader br;
    public static BufferedWriter bw;
    public static StringBuilder sb;

    static void init() {
      br = new BufferedReader(new InputStreamReader(System.in));
      bw = new BufferedWriter(new OutputStreamWriter(System.out));
      sb = new StringBuilder();
    }

    static void close() throws IOException {
      br.close();
      bw.close();
    }
  }
}
