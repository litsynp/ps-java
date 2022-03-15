package psjava.baekjoon;

import java.io.*;

/**
 * 2022.03.15 - 백준 - #2750 수 정렬하기
 */
public class Q2750 {

  private static int[] arr;
  private static int n;

  public static void main(String[] args) throws IOException {
    ps.init();

    n = Integer.parseInt(ps.br.readLine());
    arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(ps.br.readLine());
    }
    quickSort(arr);
    for (int x : arr) {
      ps.sb.append(x).append("\n");
    }
    ps.close();
  }

  public static void quickSort(int[] a) {
    sort(arr, 0, a.length - 1);
  }

  public static void sort(int[] a, int lo, int hi) {
    if (lo >= hi) {
      return;
    }

    int pivot = partition(a, lo, hi);
    sort(a, lo, pivot - 1);
    sort(a, pivot + 1, hi);
  }

  public static int partition(int[] a, int lo, int hi) {

    int pivot = lo;

    while (lo < hi) {
      while (lo < hi && a[pivot] < a[hi]) {
        hi--;
      }
      while (lo < hi && a[lo] <= a[pivot]) {
        lo++;
      }
      swap(a, lo, hi);
    }
    swap(a, pivot, lo);
    return lo;
  }

  public static void swap(int[] a, int i, int j) {
    int temp = a[i];
    a[i] = a[j];
    a[j] = temp;
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
