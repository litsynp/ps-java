package psjava.baekjoon;

import java.io.*;
import java.util.*;

/**
 * 2022.03.05 - 백준 - #15649 N과 M (1)
 */
public class Q15649 {

  public static StringBuilder sb = new StringBuilder();
  public static boolean[] visit;
  public static int[] arr;
  public static int n;
  public static int m;

  public static void main(String[] args) throws IOException {

    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      StringTokenizer st = new StringTokenizer(br.readLine(), " ");
      n = Integer.parseInt(st.nextToken());
      m = Integer.parseInt(st.nextToken());

      arr = new int[m];
      visit = new boolean[n];

      dfs(0);
      System.out.println(sb);
    }
  }

  public static void dfs(int depth) {

    // 재귀 깊이가 M과 같아지면 탐색과정에서 담았던 배열을 출력
    if (depth == m) {
      for (int val : arr) {
        sb.append(val).append(" ");
      }
      sb.append("\n");
      return;
    }

    for (int i = 0; i < n; i++) {

      // 방문하지 않은 경우
      if (!visit[i]) {
        visit[i] = true; // 방문 상태로 변경
        arr[depth] = i + 1; // 깊이를 index로 하여 i + 1 저장
        dfs(depth + 1); // 다음 자식 노드 방문을 위해 depth + 1 하여 재귀 호출

        // 자식 노드 방문이 끝나면 방문한 노르를 방문하지 않은 상태로 변경
        visit[i] = false;
      }
    }
  }
}
