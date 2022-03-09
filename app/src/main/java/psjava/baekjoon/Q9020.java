package psjava.baekjoon;

import java.io.*;

/**
 * 2022.03.09 - 백준 - #9020 골드바흐의 추측
 */
public class Q9020 {

  // 에라토스테네스의 체 -- false면 소수
  static boolean sieve[] = new boolean[10000];

  public static void main(String[] args) throws IOException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

      // 체로 소수를 거른다
      sift();

      int T = Integer.parseInt(br.readLine());

      for (int i = 0; i < T; i++) {
        int n = Integer.parseInt(br.readLine());
        boolean isFound = false;

        for (int j = n / 2; j > 0; j--) {
          for (int k = n / 2; k < n; k++) {
            // j, k가 소수일 경우에만 실행
            // 이전에 찾은 a, b 조합보다 차이가 작다면 새로운 a, b 갱신
            if (!sieve[j] && !sieve[k] && j + k == n) {
              bw.write(j + " " + k + "\n");
              isFound = true;
              break;
            }
          }

          if (isFound) {
            break;
          }
        }
      }

      bw.flush();
    }
  }

  static void sift() {
    sieve[0] = sieve[1] = true;

    for (int i = 2; i < sieve.length; i++) {
      // 소수의 배수는 소수가 아니다
      for (int j = 2; i * j < sieve.length; j++) {
        sieve[i * j] = true;
      }
    }
  }
}
