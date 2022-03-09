package psjava.baekjoon;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 2022.03.09 - 백준 - #2581 소수
 */
public class Q2581 {

  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

      int M = Integer.parseInt(br.readLine());
      int N = Integer.parseInt(br.readLine());

      int min = 99999;
      int sum = 0;
      for (int i = M; i <= N; i++) {
        if (isPrime(i)) {
          sum += i;

          if (min > 10000) {
            min = i;
          }
        }
      }

      if (min > 10000) {
        bw.write("-1\n");
      } else {
        bw.write(sum + "\n");
        bw.write(min + "\n");
      }
      bw.flush();
    }
  }

  static boolean isPrime(int n) {
    if (n < 2) {
      return false;
    }

    for (int i = 2; i < n; i++) {
      if (n % i == 0) {
        return false;
      }
    }
    return true;
  }
}
