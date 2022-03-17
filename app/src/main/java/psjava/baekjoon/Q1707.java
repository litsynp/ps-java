package psjava.baekjoon;

import java.io.*;
import java.util.*;

/**
 * 2022.03.17 - 백준 - #1707 이분 그래프
 */
public class Q1707 {

  static int V, E;
  static List<List<Integer>> map;
  static int[] group;
  static String ans;

  static int BLUE = -1, NONE = 0, RED = 1;

  public static void main(String[] args) throws IOException {
    ps.init();

    int K = Integer.parseInt(ps.br.readLine());

    for (int k = 0; k < K; k++) {
      int[] in = Arrays.stream(ps.br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
      V = in[0];
      E = in[1];
      map = new ArrayList<>();
      group = new int[V + 1];
      ans = "YES";

      for (int v = 0; v < V + 1; v++) {
        map.add(new ArrayList<>());
      }

      for (int e = 0; e < E; e++) {
        int[] in2 =
            Arrays.stream(ps.br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int u = in2[0];
        int v = in2[1];
        map.get(u).add(v);
        map.get(v).add(u);
      }

      for (int i = 1; i < V + 1; i++) {
        if (group[i] == NONE) {
          if (!bfs(i)) {
            break;
          }
        }
      }

      ps.sb.append(ans).append("\n");
    }

    ps.close();
  }

  static boolean bfs(int start) {
    Queue<Integer> q = new LinkedList<>();
    group[start] = RED;
    q.offer(start);

    while (!q.isEmpty()) {
      int v = q.poll();

      for (int i = 0; i < map.get(v).size(); i++) {
        int temp = map.get(v).get(i);

        // 색칠이 되어있지 않은 경우, 현재 노드와 다른 색으로 색칠
        if (group[temp] == 0) {
          group[temp] = -1 * group[v];
          q.offer(temp);
        } else {
          if (group[temp] == group[v]) {
            // 색깔이 같으면
            ans = "NO";
            return false;
          }
        }
      }
    }

    return true;
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
