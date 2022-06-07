package psjava.programmers.challenges.level3;

/**
 * 2022.06.07 - 프로그래머스 - #92345 사라지는 발판
 * <p>
 * LEVEL 3
 * <p>
 * 2022 KAKAO BLIND RECRUITMENT
 * <p>
 * 완전 탐색 - DFS
 *
 * @see <a href="https://programmers.co.kr/learn/courses/30/lessons/92345">https://programmers.co.kr/learn/courses/30/lessons/92345</a>
 */
public class Q92345 {

    int[][] board;
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int MAX_X;
    int MAX_Y;


    public int solution(int[][] board, int[] aloc, int[] bloc) {
        this.board = board;
        MAX_X = board.length;
        MAX_Y = board[0].length;

        return dfs(aloc[0], aloc[1], bloc[0], bloc[1]);
    }

    /**
     * 현재 상태에서 둘 다 최적의 플레이를 할 때 남은 이동 횟수
     *
     * @param curx 현재 플레이어의 X 좌표
     * @param cury 현재 플레이어의 Y 좌표
     * @param opx  상대 플레이어의 X 좌표
     * @param opy  현재 플레이어의 Y 좌표
     * @return 짝수면 플레이어가 패배, 홀수면 플레이어가 승리
     */
    int dfs(int curx, int cury, int opx, int opy) {
        if (board[curx][cury] == 0) {
            return 0;
        }
        int result = 0;

        for (int[] dir : dirs) {
            int nx = curx + dir[0];
            int ny = cury + dir[1];

            if (isOut(nx, ny) || board[nx][ny] == 0) {
                continue;
            }

            board[curx][cury] = 0;
            int bMove = dfs(opx, opy, nx, ny) + 1;
            board[curx][cury] = 1;

            if (result % 2 == 0 && bMove % 2 == 1) {
                // 현재 저장된 턴은 패배인데, 새로 계산된 턴은 승리인 경우
                result = bMove;
            } else if (result % 2 == 0 && bMove % 2 == 0) {
                // 현재 저장된 턴과 새로 계산된 턴이 모두 실패인 경우
                // 최대한 늦게 지는 것을 선택
                result = Math.max(result, bMove);
            } else if (result % 2 == 1 && bMove % 2 == 1) {
                // 현재 저장된 턴과 새로 계산된 턴이 모두 승리인 경우
                // 최대한 빨리 이기는 걸 선택
                result = Math.min(result, bMove);
            }
        }
        return result;
    }

    boolean isOut(int x, int y) {
        return x < 0 || x >= MAX_X || y < 0 || y >= MAX_Y;
    }
}
