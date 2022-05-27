package psjava.baekjoon;

import java.io.*;
import java.util.*;

/**
 * 2022.05.15 - 백준 - #1520 내리막 길
 * <p>
 * GOLD 4
 * <p>
 * DFS + DP
 * <p>
 *
 * @see <a href="https://www.acmicpc.net/problem/1520">https://www.acmicpc.net/problem/1520</a>
 */
public class Q1520 {

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int m, n;
    static int[][] map;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        ps.nextLine();
        m = ps.stoi(ps.st.nextToken());
        n = ps.stoi(ps.st.nextToken());

        map = new int[m + 1][n + 1];
        dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            ps.nextLine();
            for (int j = 1; j <= n; j++) {
                map[i][j] = ps.stoi(ps.st.nextToken());
                dp[i][j] = -1;
            }
        }

        ps.sb.append(dfs(1, 1));
        ps.close();
    }

    static int dfs(int x, int y) {
        if (x == m && y == n) {
            // 도착
            return 1;
        }

        if (dp[x][y] != -1) {
            // 이미 방문한 경우
            return dp[x][y];
        }

        dp[x][y] = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx > m || ny < 0 || ny > n) {
                continue;
            }

            // 내리막으로만 이동
            if (map[x][y] > map[nx][ny]) {
                dp[x][y] += dfs(nx, ny);
            }
        }

        return dp[x][y];
    }

    private static class ps {

        public static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        public static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        public static final StringBuilder sb = new StringBuilder();
        public static StringTokenizer st = null;

        public static void nextLine() throws IOException {
            st = new StringTokenizer(br.readLine());
        }

        public static int stoi(String s) {
            return Integer.parseInt(s);
        }

        public static void close() throws IOException {
            bw.write(sb.toString());
            br.close();
            bw.close();
        }
    }
}
