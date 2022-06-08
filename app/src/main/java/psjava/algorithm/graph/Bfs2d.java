package psjava.algorithm.graph;

import java.util.*;

/**
 * 2D BFS
 * <p>
 * BFS 특성으로 최단거리 반환
 */
public class Bfs2d {

    // 북 - 동 - 남 - 서
    final int[] dx = {-1, 0, 1, 0};
    final int[] dy = {0, 1, 0, -1};

    int X_MAX = 0;
    int Y_MAX = 0;

    int bfs(int[][] board, int x, int y, int target) {
        Queue<Node> q = new LinkedList<>();
        boolean[][] visited = new boolean[X_MAX][Y_MAX];

        visited[x][y] = true;
        q.add(new Node(x, y, 0));

        while (!q.isEmpty()) {
            Node next = q.poll();
            int px = next.x;
            int py = next.y;
            int move = next.move;

            if (board[px][py] == target) {
                // End condition
                // Found the target at (px, py)
                return move;
            }

            // Move 1 block
            for (int i = 0; i < 4; i++) {
                int nx = px + dx[i];
                int ny = py + dy[i];

                if (isOut(nx, ny)) {
                    continue;
                }

                if (visited[nx][ny]) {
                    continue;
                }

                visited[nx][ny] = true;
                q.add(new Node(nx, ny, move + 1));
            }
        }

        return 0;
    }

    private boolean isOut(int x, int y) {
        return x < 0 || x >= X_MAX || y < 0 || y >= Y_MAX;
    }

    class Node {
        int x, y, move;

        public Node(int x, int y, int move) {
            this.x = x;
            this.y = y;
            this.move = move;
        }
    }
}
