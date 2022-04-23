package psjava;

import java.io.*;
import java.util.*;

/**
 * yyyy.MM.dd - Site - #NNNN Title
 */
public class Main {

  public static void main(String[] args) throws IOException {

    ps.close();
  }

  static class ps {

    public static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static final StringBuilder sb = new StringBuilder();

    public static void close() throws IOException {
      bw.write(sb.toString());
      br.close();
      bw.close();
    }

    public static int[] getIntInputs() throws IOException {
      return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
  }
}
