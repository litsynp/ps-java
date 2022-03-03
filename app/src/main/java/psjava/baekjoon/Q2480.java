package psjava.baekjoon;

import java.io.*;
import java.util.*;

/**
 * 2022.03.04 - 백준 - #2480 주사위 세개
 */
public class Q2480 {

  public static void main(String[] args) throws IOException {

    StringTokenizer st;

    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
      st = new StringTokenizer(br.readLine(), " ");
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());

      int score = 0;
      if (a == b && b == c) {
        score = 10_000 + a * 1_000;
      } else if (a == b || a == c) {
        score = 1_000 + a * 100;
      } else if (b == c) {
        score = 1_000 + b * 100;
      } else {
        score = Math.max(Math.max(a, b), c) * 100;
      }
      System.out.println(score);
    }
  }
}
