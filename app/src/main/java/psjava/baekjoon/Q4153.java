package psjava.baekjoon;

import java.io.*;
import java.util.*;

/**
 * 2022.03.10 - 백준 - #4153 직각삼각형
 */
public class Q4153 {

  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

      while (true) {
        st = new StringTokenizer(br.readLine(), " ");

        int[] arr = {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
            Integer.parseInt(st.nextToken())};

        if (arr[0] == 0 && arr[1] == 0 && arr[2] == 0) {
          break;
        }

        // 오름차순 정렬
        Arrays.sort(arr);

        // a^2 + b^2 = c^2 확인
        if (Math.pow(arr[2], 2) == Math.pow(arr[0], 2) + Math.pow(arr[1], 2)) {
          bw.write("right\n");
        } else {
          bw.write("wrong\n");
        }
      }

      bw.flush();
    }
  }
}
