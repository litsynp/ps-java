package psjava.algorithm;

import java.io.*;
import psjava.util.ps;

/**
 * 최장 공통 부분 문자열 (LCS, Longest Common Substring)
 *
 * Longest Common Subsequence와 헷갈리지 않을 것! (차이점은 연속 여부이다.)
 *
 * A_BCD_HEF 와 _BCD_EF => Substring: BCD
 *
 * REF: https://mygumi.tistory.com/126
 */
public class Lcsubstr {

  /**
   * 문자열 A와 문자열 B의 공통 부분 문자열 (LCS, Longest Common Substring)의 길이를 반환한다.
   *
   * @param s1 문자열 A
   * @param s2 문자열 B
   * @return 문자열 A와 문자열 B의 공통 부분 문자열 (LCS, Longest Common Substring)의 길이
   */
  public static int getLengthOfLcsubstr(final String s1, final String s2) {

    int[][] lcs = new int[s1.length() + 1][s2.length() + 1];
    int len = 0;

    for (int i = 1; i <= s1.length(); i++) {
      for (int j = 1; j <= s2.length(); j++) {

        if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
          lcs[i][j] = lcs[i - 1][j - 1] + 1;

          if (len < lcs[i][j]) {
            len = lcs[i][j];
          }
        }
      }
    }

    return len;
  }

  public static void main(String[] args) throws IOException {

    String s1 = ps.br.readLine().strip();
    String s2 = ps.br.readLine().strip();

    int ans = getLengthOfLcsubstr(s1, s2);

    ps.bw.write(ps.sb.append(ans).append("\n").toString());

    ps.close();
  }
}
