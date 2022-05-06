package psjava.programmers.challenges.level2;

/**
 * 2022.05.05 - 프로그래머스 - #92342 양궁대회
 *
 * 2022 KAKAO BLIND RECRUITMENT
 *
 * 백트래킹 + 조합
 *
 * REF:
 * https://velog.io/@qodlstjd12/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-Kakao-%EC%96%91%EA%B6%81-%EB%8C%80%ED%9A%8C-Java
 */
public class Q92342 {

    private static int[] answer = { -1 };
    private static int[] ryan, apeach;
    private static int maxDiff = Integer.MIN_VALUE;

    public static int[] solution(int n, int[] info) {
        // 10점부터 0점까지
        ryan = new int[11];
        apeach = info;
        combi(1, n);
        return answer;
    }

    // i점 과녁에 대해 라이언이 할 수 있는 경우의 수는 2가지.
    // 1. 어피치보다 i점 과녁을 한 번 더 맞춘다
    // 2. 포기하고 (0번 맞추고) 다음 점수 과녁을 노려본다
    private static void combi(int idx, int n) {
        if (idx == n + 1) {
            int apeachScore = 0;
            int ryanScore = 0;
            for (int i = 0; i <= 10; i++) {
                // 10 - i번째 과녁에 대해서 맞춘 사람이 있다면 점수 계산
                if (apeach[i] != 0 || ryan[i] != 0) {
                    if (apeach[i] < ryan[i]) {
                        ryanScore += 10 - i;
                    } else {
                        apeachScore += 10 - i;
                    }
                }
            }
            // 답이 존재한다면
            if (ryanScore > apeachScore) {
                // 점수 차이가 가장 큰 답
                if (ryanScore - apeachScore >= maxDiff) {
                    // 등호를 넣음으로써, 점수 차이가 같으면
                    // 라이언이 가장 낮은 점수의 과녁을 많이 맞힌 답 결정
                    answer = ryan.clone();
                    maxDiff = ryanScore - apeachScore;
                }
            }
            return;
        }

        // 과녁은 10 - i부터 i점까지, 큰 점수부터 낮은 점수 경우 확인
        // 라이언이 어피치에게 점수를 따기위해서 최소한 어피치가 쏜 것 만큼은 쏴야한다.
        // 즉, ryan[j] <= apeach[j] 인 상태에서만 탐색 (가지치기)
        for (int i = 0; i <= 10 && ryan[i] <= apeach[i]; i++) {
            ryan[i]++;
            combi(idx + 1, n);
            ryan[i]--;
        }
    }
}
