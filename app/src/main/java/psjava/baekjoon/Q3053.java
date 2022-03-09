package psjava.baekjoon;

import java.io.*;

/**
 * 2022.03.10 - 백준 - #3053 택시 기하학
 */
public class Q3053 {

  public static void main(String[] args) throws IOException {

    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

      int R = Integer.parseInt(br.readLine());
      bw.write(Math.pow(R, 2) * Math.PI + "\n");
      bw.write(2 * Math.pow(R, 2) + "\n");
      bw.flush();
    }
  }
}
