package psjava.programmers.challenges.level2;

/**
 * 2022.04.28 - 프로그래머스 - #1829 카카오 프렌즈 컬러링북
 */
public class Q1829 {

    static int M, N;
    static int[][] map;
    static boolean[][] check;
    static int curAreaSize = 0; // temp max
    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    public int[] solution(int m, int n, int[][] picture) {
        M = m;
        N = n;
        map = picture;
        check = new boolean[m][n];

        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

        for (int x = 0; x < m; x++) {
            for (int y = 0; y < n; y++) {
                if (map[x][y] != 0 && !check[x][y]) {
                    numberOfArea++;
                    dfs(x, y);
                }

                if (curAreaSize > maxSizeOfOneArea) {
                    maxSizeOfOneArea = curAreaSize;
                }
                curAreaSize = 0;
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }

    // 상하좌우로 이어져 있으면 한 구역
    static void dfs(int x, int y) {
        if (check[x][y]) {
            return;
        }

        check[x][y] = true;
        curAreaSize++;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= M || ny < 0 || ny >= N) {
                continue;
            }

            if (map[x][y] == map[nx][ny] && !check[nx][ny]) {
                dfs(nx, ny);
            }
        }
    }
}
