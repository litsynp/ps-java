package psjava.baekjoon;

import java.io.*;

/**
 * 2022.03.09 - 백준 - #9663 N-Queen
 */
public class Q9663 {

  static int[] board;
  static int N;
  static int cnt;

  public static void main(String[] args) throws IOException {

    try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
      N = Integer.parseInt(br.readLine());
      board = new int[N];
      nQueen(0);
      bw.write(String.valueOf(cnt));
      bw.flush();
    }
  }

  static boolean checkAvail(int cdx) {
    // 같은 열이거나 대각선 상에 있는지 확인
    for (int i = 0; i < cdx; i++) {
      if (board[cdx] == board[i] || cdx - i == Math.abs(board[cdx] - board[i])) {
        return false;
      }
    }
    return true;
  }

  static void nQueen(int cdx) {
    // 모든 퀸을 놓았을 때
    if (cdx == N) {
      cnt++;
      return;
    }

    // 1부터 N번 열까지 방문하며 퀸을 둘 수 있는 자리에 놓아본다.
    for (int i = 0; i < N; i++) {
      // cdx 행 i 열에 퀸을 놓아본다.
      board[cdx] = i;

      // 그 행에 둔 것에 대해 다음 수가 존재하는지 확인한다.
      if (checkAvail(cdx)) {
        // 존재한다면 다음 행에 퀸을 두는 걸 시도해본다.
        nQueen(cdx + 1);
      }
    }
  }
}
