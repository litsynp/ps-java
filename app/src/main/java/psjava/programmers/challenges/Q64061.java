package psjava.programmers.challenges;

import java.util.*;

/**
 * 2022.03.22 - 프로그래머스 - #64061 크레인 인형뽑기 게임
 */
public class Q64061 {

    static Stack<Integer> stack = new Stack<>();
    static int[] tops;
    static int answer = 0;

    public static int solution(int[][] board, int[] moves) {
        // board의 각 행의 최상단의 위치를 가져온다
        tops = new int[board[0].length];
        for (int i = 0; i < board[0].length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[j][i] != 0) {
                    tops[i] = j;
                    break;
                }
            }
        }

        // 각 움직임에 대해 인형뽑기 실시
        for (int move : moves) {
            // moves의 i번 행에서 최상단에 위치한 인형을 가져온다
            take(board, move - 1);
        }

        return answer;
    }

    static void take(int[][] board, int move) {
        int row = tops[move];

        // 인형이 더 이상 없다면 뽑지 말 것
        if (row >= board.length) {
            return;
        }

        if (stack.size() > 0 && stack.peek().equals(board[row][move])) {
            stack.pop();
            answer += 2;
        } else {
            stack.push(board[row][move]);
        }

        board[row][move] = 0;
        tops[move]++;
    }
}
