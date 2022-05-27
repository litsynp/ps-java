package psjava.util;

import java.io.*;
import java.util.*;

/**
 * 문제풀이에서 I/O를 담당하는 클래스
 */
public class ps {

    public static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static final StringBuilder sb = new StringBuilder();
    public static StringTokenizer st = null;

    public static void nextLine() throws IOException {
        st = new StringTokenizer(br.readLine());
    }

    public static int stoi(String s) {
        return Integer.parseInt(s);
    }

    public static void close() throws IOException {
        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}
