package psjava.algorithm.graph;

/**
 * 2D DFS
 */
public class Dfs2d {

    // 북 - 동 - 남 - 서
    final int[] dx = {-1, 0, 1, 0};
    final int[] dy = {0, 1, 0, -1};

    int X_MAX;
    int Y_MAX;

    void dfs(int[][] map, boolean[][] visited, int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= X_MAX || ny < 0 || ny >= Y_MAX) {
                continue;
            }

            if (visited[nx][ny]) {
                continue;
            }

            visited[x][y] = true;
            dfs(map, visited, nx, ny);
        }
    }

    private boolean isOut(int x, int y) {
        return x < 0 || x >= X_MAX || y < 0 || y >= Y_MAX;
    }
}
