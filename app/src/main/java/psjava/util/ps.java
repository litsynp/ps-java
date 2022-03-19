package psjava.util;

import java.io.*;
import java.util.*;

/**
 * 문제풀이에서 I/O를 담당하는 클래스
 */
public class ps {

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

  public static int[] getIntInputs() throws IOException {
    return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
  }
}
