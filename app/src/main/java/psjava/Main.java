package psjava;

import java.io.*;
import java.util.*;

/**
 * yyyy.MM.dd - Site - #NNNN Title
 */
public class Main {

  public static void main(String[] args) throws IOException {
    ps.init();

    ps.close();
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
