package psjava.algorithms;

import java.io.*;
import psjava.util.ps;

/**
 * 최장 공통 부분 수열 (LCS, Longest Common Subsequence)
 *
 * LCS 또한 LIS와 같이 DP(동적 계획법)을 기반으로 한다.
 *
 * Longest Common Substring와 헷갈리지 않을 것! (차이점은 연속 여부이다.)
 *
 * A_BCD_H_EF_ 와 _BCDEF_ => Subsequence: BCDEF
 *
 * REF: https://mygumi.tistory.com/126
 */
public class Lcs {

  static int[][] lcs;

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

  public static void main(String[] args) throws IOException {
    ps.init();

    String s1 = ps.br.readLine().strip();
    String s2 = ps.br.readLine().strip();
    lcs = new int[s1.length() + 1][s2.length() + 1];

    calcLcs(s1, s2);

    ps.bw.write(ps.sb.append(lcs[s1.length()][s2.length()]).append("\n").toString());

    ps.close();
  }
}
