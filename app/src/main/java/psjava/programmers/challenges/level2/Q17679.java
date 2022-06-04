package psjava.programmers.challenges.level2;

/**
 * 2022.06.05 - 프로그래머스 - #17679 [1차] 프렌즈4블록
 * <p>
 * LEVEL 2
 * <p>
 * 구현
 * <p>
 * 2018 KAKAO BLIND RECRUITMENT
 *
 * @see <a href="https://programmers.co.kr/learn/courses/30/lessons/17679">https://programmers.co.kr/learn/courses/30/lessons/17679</a>
 * @see <a href="https://velog.io/@hyeon930/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%ED%94%84%EB%A0%8C%EC%A6%884%EB%B8%94%EB%A1%9D-Java">https://velog.io/@hyeon930/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%ED%94%84%EB%A0%8C%EC%A6%884%EB%B8%94%EB%A1%9D-Java</a>
 */
public class Q17679 {

    public int solution(int m, int n, String[] board) {
        char[][] map = new char[m][n];

        for (int i = 0; i < m; i++) {
            map[i] = board[i].toCharArray();
        }

        int answer = 0;
        // m: 세로 길이
        // n: 가로 길이
        while (true) {
            // 삭제할 블록 수
            int count = checkBlock(m, n, map);

            // 삭제한 블록이 없다면 종료
            if (count == 0) {
                break;
            }

            answer += count;

            // 빈 칸이 생겼다면 내려보냄
            dropBlock(m, n, map);
        }

        return answer;
    }

    static int checkBlock(int m, int n, char[][] map) {
        int count = 0;
        boolean[][] checked = new boolean[m][n];

        // 모든 블록에 대해서
        for (int r = 0; r < m - 1; r++) {
            for (int c = 0; c < n - 1; c++) {
                // 비어있으면 지나감
                if (map[r][c] == '.') {
                    continue;
                }

                // 비어있지 않다면 인접한 4칸 검사
                // 인접한 같은 종류의 4 블록이 있다면 check = true
                checkFour(map, checked, r, c);
            }
        }

        // 체크된 블록을 모두 빈 칸으로 만듦
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (checked[r][c]) {
                    // 빈칸이 된 블록 = "삭제할 블록"
                    count++;
                    map[r][c] = '.';
                }
            }
        }

        // 삭제할 블록 개수 반환
        return count;
    }

    static void dropBlock(int m, int n, char[][] map) {
        for (int c = 0; c < n; c++) {
            // 맨 아랫줄부터 좌->우 검사
            for (int r = m - 1; r >= 0; r--) {
                // 비어있는 공간이 있다면
                if (map[r][c] == '.') {
                    // 위로 한칸씩 올라가면서 블록을 찾으면 밑으로 내림
                    for (int nr = r - 1; nr >= 0; nr--) {
                        if (map[nr][c] != '.') {
                            map[r][c] = map[nr][c];
                            map[nr][c] = '.';
                            break;
                        }
                    }
                }
            }
        }
    }

    static void checkFour(char[][] map, boolean[][] checked, int r, int c) {
        // 현재 블록 종류
        char block = map[r][c];

        // 현재 블록을 포함해서 같은 종류의 블럭 4개가 인접한지 체크
        // X: 현재 블록, O: 검사할 인접한 블록
        // XO
        // OO
        // 의 방향으로만 검사
        for (int x = r; x <= r + 1; x++) {
            for (int y = c; y <= c + 1; y++) {
                if (map[x][y] != block) {
                    return;
                }
            }
        }

        // 모두 인접하다면 check
        for (int x = r; x <= r + 1; x++) {
            for (int y = c; y <= c + 1; y++) {
                checked[x][y] = true;
            }
        }
    }
}
