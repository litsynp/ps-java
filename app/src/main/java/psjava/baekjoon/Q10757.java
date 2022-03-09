package psjava.baekjoon;

import java.io.*;
import java.math.BigInteger;
import java.util.StringTokenizer;

/**
 * 2022.03.09 - 백준 - #10757 큰수 A+B
 */
public class Q10757 {

  static StringTokenizer st;

  public static void main(String[] args) throws IOException {

    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

      st = new StringTokenizer(br.readLine(), " ");
      BigInteger A = new BigInteger(st.nextToken());
      BigInteger B = new BigInteger(st.nextToken());

      bw.write(A.add(B) + "\n");
      bw.flush();
    }
  }
}
