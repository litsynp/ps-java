package psjava.baekjoon;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 2022.03.09 - 백준 - #1929 소수 구하기
 */
public class Q1929 {

  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
      st = new StringTokenizer(br.readLine(), " ");
      int M = Integer.parseInt(st.nextToken());
      int N = Integer.parseInt(st.nextToken());

      // 에라토스테네스의 체 -- false로 남은 것만 소수
      boolean[] arr = new boolean[N + 1];
      arr[0] = true;
      arr[1] = true;

      for (int i = 2; i < N + 1; i++) {
        // 방문한 적 없는 i가 소수라면, 그 배수는 모두 소수가 아니다.
        for (int j = 2; i * j < N + 1; j++) {
          arr[i * j] = true;
        }
      }

      // M <= x <= N에서 소수인 x를 출력
      for (int i = M; i < N + 1; i++) {
        if (!arr[i]) {
          bw.write(i + "\n");
        }
      }
      bw.flush();
    }
  }
}
