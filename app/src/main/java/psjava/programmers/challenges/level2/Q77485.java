package psjava.programmers.challenges.level2;

import java.util.*;

/**
 * 2022.06.07 - 프로그래머스 - #77485 행렬 테두리 회전하기
 * <p>
 * LEVEL 2
 * <p>
 * 2021 Dev-Matching: 웹 백엔드 개발자(상반기)
 * <p>
 * 알고리즘
 *
 * @see <a href="https://programmers.co.kr/learn/courses/30/lessons/77485">https://programmers.co.kr/learn/courses/30/lessons/77485</a>
 */
public class Q77485 {

    int[][] board;
    int ROWS;
    int COLS;

    public int[] solution(int rows, int columns, int[][] queries) {
        ROWS = rows;
        COLS = columns;

        board = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                // curRow * col + 1부터 시작
                board[i][j] = i * (columns) + (j + 1);
            }
        }

        List<Integer> answer = new ArrayList<>();
        for (int[] query : queries) {
            answer.add(rotate(query));
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }

    /**
     * 행렬 테두리 1회 회전 (상->좌->하->우 방향 순으로 진행)
     * <p>
     * 움직인 행렬의 값 중 최소값 반환
     */
    int rotate(int[] query) {
        int r1 = query[0] - 1;
        int c1 = query[1] - 1;
        int r2 = query[2] - 1;
        int c2 = query[3] - 1;

        int temp = board[r1][c1];
        int min = temp;

        // UP
        for (int i = r1; i < r2; i++) {
            board[i][c1] = board[i + 1][c1];

            min = Math.min(min, board[i][c1]);
        }
        // LEFT
        for (int i = c1; i < c2; i++) {
            board[r2][i] = board[r2][i + 1];

            min = Math.min(min, board[r2][i]);
        }
        // DOWN
        for (int i = r2; i > r1; i--) {
            board[i][c2] = board[i - 1][c2];

            min = Math.min(min, board[i][c2]);
        }
        // RIGHT
        for (int i = c2; i > c1; i--) {
            board[r1][i] = board[r1][i - 1];

            min = Math.min(min, board[r1][i]);
        }
        board[r1][c1 + 1] = temp;

        return min;
    }

    boolean isDiff(int curX, int curY) {
        return board[curX][curY] != curX * (COLS) + (curY + 1);
    }
}
