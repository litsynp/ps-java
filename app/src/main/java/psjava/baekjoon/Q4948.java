package psjava.baekjoon;

import java.io.*;
import java.util.*;

/**
 * 2022.03.09 - 백준 - #4948 베르트랑 공준
 */
public class Q4948 {

  // 에라토스테네스의 체 -- false면 소수
  static boolean sieve[] = new boolean[123456 * 2 + 1];

  public static void main(String[] args) throws IOException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

      // 체로 소수를 거른다
      sift();

      while (true) {
        int n = Integer.parseInt(br.readLine());

        if (n == 0) {
          break;
        }

        int cnt = 0;
        // n보다 크고, 2n보다 작거나 같은 소수의 개수 출력
        for (int i = n + 1; i <= n * 2; i++) {
          if (!sieve[i]) {
            cnt += 1;
          }
        }
        bw.write(cnt + "\n");
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
