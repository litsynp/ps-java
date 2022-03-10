package psjava.baekjoon;

import java.io.*;
import java.util.*;

/**
 * 2022.03.10 - 백준 - #2108 통계학
 */
public class Q2108 {

  static StringTokenizer st;
  static int N;
  static int[] arr;

  public static void main(String[] args) throws IOException {

    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {

      N = Integer.parseInt(br.readLine());
      arr = new int[N];
      for (int i = 0; i < N; i++) {
        arr[i] = Integer.parseInt(br.readLine());
      }
      Arrays.sort(arr);

      bw.write((int) round(getArithmeticMean(arr), 0) + "\n");
      bw.write((int) round(getMedian(arr), 0) + "\n");
      bw.write(getMode(arr) + "\n");
      bw.write(arr[arr.length - 1] - arr[0] + "\n");

      bw.flush();
    }
  }

  /** precision 자리수에서 반올림하는 함수 */
  static double round(double value, int precision) {
    int scale = (int) Math.pow(10, precision);
    return (double) Math.round(value * scale) / scale;
  }

  /** (산술)평균 구하기 */
  static double getArithmeticMean(int[] arr) {
    return ((double) Arrays.stream(arr).sum()) / arr.length;
  }

  /** 중앙값 구하기 */
  static double getMedian(int[] arr) {
    if (arr.length % 2 == 0) {
      // 짝수개일 경우, 두 숫자의 산술평균
      return ((double) arr[arr.length / 2] + (double) arr[(arr.length + 1) / 2]) / 2.0;
    } else {
      // 홀수개일 경우
      return (double) arr[arr.length / 2];
    }
  }

  /** 최빈값, 가장 많이 관측되는 수 구하기 */
  static int getMode(int[] arr) {

    // 배열 요소의 빈도수를 저장
    // -4000 <= x <= 4000이니 빈도수 저장을 위해 8000을 길이로 둠
    int[] cntArr = new int[8001];

    int max = 0;

    for (int i = 0; i < N; i++) {
      // 4000을 더해서 저장
      cntArr[arr[i] + 4000]++;

      // 핵심은 최대빈도수를 미리 저장해두는 것
      if (cntArr[arr[i] + 4000] > max) {
        max = cntArr[arr[i] + 4000];
      }
    }

    // 최빈값의 빈도수
    int maxCnt = 0;

    // 최빈값의 인덱스
    int maxIdx = 0;

    for (int i = 0; i < 8001; i++) {
      // 미리 저장해둔 최대 빈도수와 비교
      if (cntArr[i] == max) {
        maxIdx = i;
        maxCnt++;
        // 최대 빈도수를 미리 저장해두었기 때문에 2번째에서 바로 break
        if (maxCnt == 2) {
          // cntArr은 -4000부터 4000까지 순서대로 배열 구성
          break;
        }
      }
    }

    // 4000을 빼서 반환
    return maxIdx - 4000;
  }
}
