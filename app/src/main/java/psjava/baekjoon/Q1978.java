package psjava.baekjoon;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 2022.03.09 - 백준 - #1978 소수 찾기
 */
public class Q1978 {

  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

      int N = Integer.parseInt(br.readLine());
      int cnt = 0;

      st = new StringTokenizer(br.readLine(), " ");
      for (int i = 0; i < N; i++) {
        int n = Integer.parseInt(st.nextToken());
        if (isPrime(n)) {
          cnt++;
        }
      }
      bw.write(cnt + "\n");
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
