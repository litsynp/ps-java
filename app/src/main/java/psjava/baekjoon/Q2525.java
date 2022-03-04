package psjava.baekjoon;

import java.io.*;
import java.util.*;

/**
 * 2022.03.04 : 백준 2525 : 오븐 시계
 */
public class Q2525 {

  public static void main(String[] args) throws IOException {

    StringTokenizer st;

    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      st = new StringTokenizer(br.readLine(), " ");
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(br.readLine());

      b += c;
      int m = b % 60;
      a = a + b / 60;
      a = a >= 24 ? a - 24 : a;

      System.out.printf("%d %d", a, m);
    }
  }
}
