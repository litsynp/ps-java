package psjava.baekjoon;

import java.io.*;

/**
 * 2022.03.10 - 백준 - #10844 쉬운 계단 수
 */
public class Q10844 {

  static StringBuilder sb = new StringBuilder();

  // 결과가 없을 경우를 대비해 long이 아닌 Long 타입으로 선언 (null init)
  static Long[][] dp;
  static int N;
  static final long MOD = 1_000_000_000;

  public static void main(String[] args) throws IOException {

    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

      N = Integer.parseInt(br.readLine());

      // [n][d] => (N=n, d=각 자리값 (0-9))
      // e.g., d___ (N=4)
      dp = new Long[N + 1][10];

      // N=1 결과값 저장 (1 ~ 9)
      for (int i = 0; i < 10; i++) {
        dp[1][i] = 1L;
      }

      long ans = 0;

      for (int i = 1; i < 10; i++) {
        ans += recur(N, i);
      }

      String out = sb.append(ans % MOD).append("\n").toString();
      bw.write(out);
      bw.flush();
    }
  }

  static long recur(int digit, int val) {
    // N=1인 경우
    if (digit == 1) {
      return dp[digit][val];
    }

    // DP값이 존재하지 않는 경우
    if (dp[digit][val] == null) {
      if (val == 0) {
        // 자릿값이 0인 경우, 다음 자릿값은 1이 되어야 한다.
        dp[digit][val] = recur(digit - 1, 1);
      } else if (val == 9) {
        // 자릿값이 9인 경우, 다음 자릿값은 8이 되어야 한다.
        dp[digit][val] = recur(digit - 1, 8);
      } else {
        // 그 외의 경우
        dp[digit][val] = recur(digit - 1, val - 1) + recur(digit - 1, val + 1);
      }
    }

    // 저장한 DP값 반환
    return dp[digit][val] % MOD;
  }
}
