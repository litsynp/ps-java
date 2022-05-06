package psjava.programmers.challenges.level3;

/**
 * 2022.05.06 - 프로그래머스 - #92344 파괴되지 않은 건물
 *
 * 누적합
 *
 * REF: https://jangcenter.tistory.com/121
 */
public class Q92344 {

    public int solution(int[][] board, int[][] skill) {

        final int N = board.length;
        final int M = board[0].length;

        // 누적합 배열 생성
        int[][] accMap = new int[N + 1][M + 1];
        for (int[] s : skill) {
            int type = s[0];
            int r1 = s[1];
            int c1 = s[2];
            int r2 = s[3];
            int c2 = s[4];
            int degree = type == 1 ? -s[5] : s[5];

            accMap[r1][c1] += degree;
            accMap[r1][c2 + 1] += -degree;
            accMap[r2 + 1][c1] += -degree;
            accMap[r2 + 1][c2 + 1] += degree;
        }

        // 상하
        for (int r = 1; r < N; r++) {
            for (int c = 0; c < M; c++) {
                accMap[r][c] += accMap[r - 1][c];
            }
        }

        // 좌우
        for (int c = 1; c < M; c++) {
            for (int r = 0; r < N; r++) {
                accMap[r][c] += accMap[r][c - 1];
            }
        }

        // 결과 확인
        int answer = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                if (board[r][c] + accMap[r][c] > 0) {
                    answer++;
                }
            }
        }

        return answer;
    }
}
