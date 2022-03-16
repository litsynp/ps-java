package psjava.baekjoon;

import java.io.*;
import java.util.*;

/**
 * 2022.03.17 - 백준 - #1697 숨바꼭질
 */
public class Q1697 {

  static int N, K;
  static int[] check = new int[100001];

  public static void main(String[] args) throws IOException {
    ps.init();

    int[] in = Arrays.stream(ps.br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    N = in[0];
    K = in[1];

    int ans = 0;
    if (N != K) {
      ans = bfs(N);
    }
    ps.sb.append(ans).append("\n");
    ps.close();
  }

  static int bfs(int start) {
    Queue<Integer> q = new LinkedList<>();
    q.offer(start);
    check[start] = 1;

    while (!q.isEmpty()) {
      int x = q.poll();

      for (int i = 0; i < 3; i++) {
        int nx;

        if (i == 0) {
          nx = x + 1;
        } else if (i == 1) {
          nx = x - 1;
        } else {
          nx = 2 * x;
        }

        // 도착했다면 바로 return
        if (nx == K) {
          return check[x];
        }

        // 방문하지 않은 위치라면
        if (nx >= 0 && nx < check.length && check[nx] == 0) {
          q.offer(nx);
          check[nx] = check[x] + 1;
        }
      }
    }

    return 0;
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
