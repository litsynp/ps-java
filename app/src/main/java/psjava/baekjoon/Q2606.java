package psjava.baekjoon;

import java.io.*;
import java.util.*;

/**
 * 22.03.16 - 백준 - #Q2606 바이러스
 */
public class Q2606 {

  static int N, M;
  static ArrayList<ArrayList<Integer>> map = new ArrayList<>();
  static boolean[] visited;

  public static void main(String[] args) throws IOException {
    ps.init();

    N = Integer.parseInt(ps.br.readLine());
    M = Integer.parseInt(ps.br.readLine());
    visited = new boolean[N + 1];

    for (int i = 0; i < N + 1; i++) {
      map.add(new ArrayList<>());
    }

    for (int i = 0; i < M; i++) {
      int[] in = Arrays.stream(ps.br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
      int a = in[0];
      int b = in[1];

      map.get(a).add(b);
      map.get(b).add(a);
    }

    bfs(1);

    // 1번 컴퓨터 제외
    int ans = -1;
    for (int i = 1; i < visited.length; i++) {
      if (visited[i]) {
        ans++;
      }
    }

    ps.sb.append(ans).append("\n");
    ps.close();
  }

  static void bfs(int start) {
    Queue<Integer> q = new LinkedList<>();
    q.offer(start);
    visited[start] = true;

    while (!q.isEmpty()) {
      int v = q.poll();

      for (int i = 0; i < map.get(v).size(); i++) {
        int temp = map.get(v).get(i);

        if (!visited[temp]) {
          q.offer(temp);
          visited[temp] = true;
        }
      }
    }
  }

  static class ps {

    public static BufferedReader br;
    public static BufferedWriter bw;
    public static StringBuilder sb;

    public static void init() {
      br = new BufferedReader(new InputStreamReader(System.in));
      bw = new BufferedWriter(new OutputStreamWriter(System.out));
      sb = new StringBuilder();
    }

    public static void close() throws IOException {
      bw.write(sb.toString());
      br.close();
      bw.close();
    }
  }
}
