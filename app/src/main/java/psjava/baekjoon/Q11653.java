package psjava.baekjoon;

import java.io.*;

/**
 * 2022.03.09 - 백준 - #11653 소인수분해
 */
public class Q11653 {

  public static void main(String[] args) throws IOException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

      int N = Integer.parseInt(br.readLine());

      while (true) {
        // 인수분해
        int factor = factorize(N);
        if (factor == N) {
          break;
        }
        N = N / factor;
        bw.write(factor + "\n");
      }

      // 인수분해된 N이 2 이상일 경우 마지막 인수로 간주
      if (N > 1) {
        bw.write(N + "\n");
      }

      bw.flush();
    }
  }

  static int factorize(int N) {
    for (int i = 2; i <= N / 2; i++) {
      // 인수를 찾으면 인수를 반환
      if (N % i == 0) {
        return i;
      }
    }
    // 아니면 N을 반환
    return N;
  }
}
