package psjava.programmers.challenges.level3;

/**
 * 2022.06.09 - 프로그래머스 - #1832 보행자 천국
 * <p>
 * LEVEL 3
 * <p>
 * 2017 카카오코드 예선
 * <p>
 * DP
 *
 * @see <a href="https://velog.io/@hyeon930/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EB%B3%B4%ED%96%89%EC%9E%90-%EC%B2%9C%EA%B5%AD-Java">https://velog.io/@hyeon930/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EB%B3%B4%ED%96%89%EC%9E%90-%EC%B2%9C%EA%B5%AD-Java</a>
 * @see <a href="https://programmers.co.kr/learn/courses/30/lessons/1832">https://programmers.co.kr/learn/courses/30/lessons/1832</a>
 */
public class Q1832 {

    int MOD = 20170805;

    public int solution(int m, int n, int[][] cityMap) {

        // dp[][][0] : 아래로 이동
        // dp[][][1] : 오른쪽으로 이동
        int[][][] dp = new int[m + 1][n + 1][2];
        // Boundary check 편의상 Padding을 추가
        dp[1][1][0] = 1;
        dp[1][1][1] = 1;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (cityMap[i - 1][j - 1] == 0) {
                    // 하: 길이 있다면, 위쪽 + 왼쪽의 경로를 둘 다 더함
                    dp[i][j][0] += (dp[i - 1][j][0] + dp[i][j - 1][1]) % MOD;
                    // 우: 길이 있다면, 위쪽 + 왼쪽의 경로를 둘 다 더함
                    dp[i][j][1] += (dp[i - 1][j][0] + dp[i][j - 1][1]) % MOD;
                } else if (cityMap[i - 1][j - 1] == 1) {
                    // 길이 막혔을 경우, 해당 위치로 가는 경로는 없음
                    dp[i][j][0] = 0;
                    dp[i][j][1] = 0;
                } else {
                    // 좌회전 / 우회전 금지
                    // 상->하: 위쪽에서 오는 경우만 더함
                    dp[i][j][0] = dp[i - 1][j][0];
                    // 좌->우: 왼쪽에서 오는 경우만 더함
                    dp[i][j][1] = dp[i][j - 1][0];
                }
            }
        }

        // dp[m][n][0] == dp[m][n][1]
        return dp[m][n][0];
    }
}

