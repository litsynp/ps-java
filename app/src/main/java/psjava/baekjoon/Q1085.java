package psjava.baekjoon;

import java.io.*;
import java.util.StringTokenizer;

/**
 * 2022.03.09 - 백준 - #1085 직사각형에서 탈출
 */
public class Q1085 {

  public static void main(String[] args) throws IOException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
      StringTokenizer st = new StringTokenizer(br.readLine(), " ");
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());
      int w = Integer.parseInt(st.nextToken());
      int h = Integer.parseInt(st.nextToken());

      bw.write(Math.min(x, Math.min(w - x, Math.min(y, h - y))) + "\n");
      bw.flush();
    }
  }
}
