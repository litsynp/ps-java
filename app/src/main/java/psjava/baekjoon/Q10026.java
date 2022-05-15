package psjava.baekjoon;

import java.io.*;

/**
 * 2022.05.15 - 백준 - #10026 적록색약
 */
public class Q10026 {

    private static int N;
    private static char[][] map;
    private static boolean[][] visited;
    private static int[] dx = { 0, -1, 0, 1 };
    private static int[] dy = { -1, 0, 1, 0 };

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(ps.br.readLine());

        map = new char[N][N];
        for (int i = 0; i < N; i++) {
            map[i] = ps.br.readLine().toCharArray();
        }

        // 1. Non-blind
        int areas = 0;
        visited = new boolean[N][N];
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (!visited[x][y]) {
                    dfs(map, visited, x, y);
                    areas++;
                }
            }
        }

        // 2. Blind
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                // G -> R
                if (map[x][y] == 'G') {
                    map[x][y] = 'R';
                }
            }
        }

        int areasForBlind = 0;
        visited = new boolean[N][N];
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (!visited[x][y]) {
                    dfs(map, visited, x, y);
                    areasForBlind++;
                }
            }
        }

        ps.sb.append(areas).append(" ").append(areasForBlind).append("\n");
        ps.close();
    }

    private static void dfs(char[][] map, boolean[][] visited, int x, int y) {
        visited[x][y] = true;
        char color = map[x][y];

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                continue;
            }

            if (visited[nx][ny]) {
                continue;
            }

            if (map[nx][ny] == color) {
                dfs(map, visited, nx, ny);
            }
        }
    }

    private static class ps {

        public static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        public static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        public static final StringBuilder sb = new StringBuilder();

        public static void close() throws IOException {
            bw.write(sb.toString());
            br.close();
            bw.close();
        }
    }
}
