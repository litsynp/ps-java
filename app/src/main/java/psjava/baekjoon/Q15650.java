package psjava.baekjoon;

import java.io.*;
import java.util.*;

/**
 * 2022.03.05 - 백준 - #15650 N과 M (2)
 */
public class Q15650 {

  public static StringBuilder sb = new StringBuilder();
  public static StringTokenizer st;
  public static int[] arr;
  public static boolean[] visited;
  public static int N;
  public static int M;

  public static void main(String[] args) throws IOException {

    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      st = new StringTokenizer(br.readLine(), " ");
      N = Integer.parseInt(st.nextToken());
      M = Integer.parseInt(st.nextToken());

      arr = new int[M];
      visited = new boolean[N];

      dfs(0);
      System.out.println(sb);
    }
  }

  public static void dfs(int depth) {

    // 깊이가 M이면 출력
    if (depth == M) {
      for (int val : arr) {
        sb.append(val).append(" ");
      }
      sb.append("\n");
      return;
    }

    for (int i = 0; i < N; i++) {
      if (!visited[i]) {
        visited[i] = true;
        arr[depth] = i + 1;

        if (depth > 0) {
          if (arr[depth - 1] > arr[depth]) {
            visited[i] = false;
            continue;
          }
        }

        dfs(depth + 1);
        visited[i] = false;
      }
    }
  }
}
