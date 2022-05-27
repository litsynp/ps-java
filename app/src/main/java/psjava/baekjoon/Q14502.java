package psjava.baekjoon;

import java.io.*;
import java.util.*;

/**
 * 2022.05.15 - 백준 - #14502 연구소
 * <p>
 * GOLD 5
 * <p>
 * 조합 + DFS / BFS (2D BFS)
 */
public class Q14502 {

    static final int BLANK = 0;
    static final int WALL = 1;
    static final int VIRUS = 2;
    static final int[] dx = {0, 0, 1, -1};
    static final int[] dy = {1, -1, 0, 0};
    static int n, m;
    static int[][] map;
    static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        int[] in = Arrays.stream(ps.br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        n = in[0];
        m = in[1];
        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            map[i] = Arrays.stream(ps.br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        dfs(0);

        ps.sb.append(answer).append("\n");
        ps.close();
    }

    //벽 기둥 3개를 세우기 위한 함수
    static void dfs(int cnt) {
        if (cnt == 3) {
            bfs();
            return;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == BLANK) {
                    map[i][j] = WALL;
                    dfs(cnt + 1);
                    map[i][j] = BLANK;
                }
            }
        }
    }

    public static void bfs() {
        int[][] virusMap = new int[n][m];
        for (int i = 0; i < n; i++) {
            virusMap[i] = Arrays.copyOf(map[i], m);
        }

        Queue<VirusPos> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (virusMap[i][j] == VIRUS) {
                    q.add(new VirusPos(i, j));
                }
            }
        }

        while (!q.isEmpty()) {
            VirusPos p = q.remove();
            int x = p.x;
            int y = p.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }

                if (virusMap[nx][ny] == BLANK) {
                    virusMap[nx][ny] = VIRUS;
                    q.add(new VirusPos(nx, ny));
                }
            }
        }

        answer = Math.max(getSafeZone(virusMap), answer);
    }

    static int getSafeZone(int[][] virusMap) {
        int area = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (virusMap[i][j] == BLANK) {
                    area++;
                }
            }
        }
        return area;
    }

    static class VirusPos {
        int x;
        int y;

        public VirusPos(int x, int y) {
            this.x = x;
            this.y = y;
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
