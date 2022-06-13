package psjava.programmers.challenges.level3;

import java.util.*;

/**
 * 2022.06.11 - 프로그래머스 - #60061 기둥과 보 설치
 * <p>
 * LEVEL 3
 * <p>
 * 2020 KAKAO BLIND RECRUITMENT
 * <p>
 * 구현
 *
 * @see <a href="https://velog.io/@hyeon930/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EA%B8%B0%EB%91%A5%EA%B3%BC-%EB%B3%B4-%EC%84%A4%EC%B9%98-Java">https://velog.io/@hyeon930/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EA%B8%B0%EB%91%A5%EA%B3%BC-%EB%B3%B4-%EC%84%A4%EC%B9%98-Java</a>
 * @see <a href="https://programmers.co.kr/learn/courses/30/lessons/60061">https://programmers.co.kr/learn/courses/30/lessons/60061</a>
 */
public class Q60061 {

    boolean[][] columns;
    boolean[][] beams;

    static final int COLUMN_TYPE = 0;
    static final int BEAM_TYPE = 1;

    static final int DESTROY_TYPE = 0;
    static final int PLACE_TYPE = 1;

    public int[][] solution(int n, int[][] build_frame) {
        // init
        columns = new boolean[n + 3][n + 3];
        beams = new boolean[n + 3][n + 3];

        // Insert queries
        for (int[] query : build_frame) {
            int x = query[0] + 1;
            int y = query[1] + 1;
            int structureType = query[2];  // column/beam
            int queryType = query[3];  // delete/put

            if (queryType == PLACE_TYPE) {
                if (structureType == COLUMN_TYPE && canPlaceColumnAt(x, y)) {
                    columns[x][y] = true;
                } else if (structureType == BEAM_TYPE && canPlaceBeamAt(x, y)) {
                    beams[x][y] = true;
                }
            } else if (queryType == DESTROY_TYPE) {
                if (structureType == COLUMN_TYPE) {
                    columns[x][y] = false;
                } else if (structureType == BEAM_TYPE) {
                    beams[x][y] = false;
                }

                // 삭제가 가능한지 전체 탐색
                if (!canDestroy(n)) {
                    // 삭제할 수 없다면 복구
                    if (structureType == COLUMN_TYPE) {
                        columns[x][y] = true;
                    } else {
                        beams[x][y] = true;
                    }
                }
            }
        }

        List<int[]> answerList = new ArrayList<>();
        for (int i = 1; i <= n + 1; i++) {
            for (int j = 1; j <= n + 1; j++) {
                // 기둥 먼저
                if (columns[i][j]) {
                    answerList.add(new int[]{i - 1, j - 1, COLUMN_TYPE});
                }
                if (beams[i][j]) {
                    answerList.add(new int[]{i - 1, j - 1, BEAM_TYPE});
                }
            }
        }

        return answerList.toArray(int[][]::new);
    }

    private boolean canPlaceColumnAt(int x, int y) {
        // 기둥은 바닥 위에 있거나 보의 한쪽 끝 부분 위에 있거나, 또는 다른 기둥 위에 있어야 합니다.
        return y == 1 || columns[x][y - 1] || beams[x][y] || beams[x - 1][y];
    }

    private boolean canPlaceBeamAt(int x, int y) {
        // 보는 한쪽 끝 부분이 기둥 위에 있거나, 또는 양쪽 끝 부분이 다른 보와 동시에 연결되어 있어야 합니다.
        return columns[x][y - 1] || columns[x + 1][y - 1] || (beams[x - 1][y] && beams[x + 1][y]);
    }

    /**
     * 삭제 후에 실행
     */
    private boolean canDestroy(int n) {
        for (int i = 1; i <= n + 1; i++) {
            for (int j = 1; j <= n + 1; j++) {
                if (columns[i][j] && !canPlaceColumnAt(i, j)) {
                    return false;
                }
                if (beams[i][j] && !canPlaceBeamAt(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }
}
