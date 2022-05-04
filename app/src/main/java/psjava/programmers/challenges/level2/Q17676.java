package psjava.programmers.challenges.level2;

/**
 * 2022.04.28 - 프로그래머스 - #17676 추석 트래픽
 *
 * REF:
 * https://velog.io/@hyeon930/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EC%B6%94%EC%84%9D-%ED%8A%B8%EB%9E%98%ED%94%BD-Java
 */
public class Q17676 {

    public int solution(String[] lines) {

        int answer = 0;
        int[] startTimes = new int[lines.length];
        int[] endTimes = new int[lines.length];

        // 주어진 입력값 lines에 따른 startTimes와 endTimes 계산
        calcTimes(lines, startTimes, endTimes);

        /*
         * - 처리량이 가장 많은 1초만 구하면 된다.
         * - 처리량이 변하는 시점은 하나의 데이터를 처리 "시작"하는 시점과 "완료"한 시점이다.
         * 각 로그의 시작과 끝을 윈도우의 시작으로 보고,
         * 각 윈도우에 대해서 처리량이 얼마나 되는지 확인하면 된다
         */

        for (int i = 0; i < lines.length; ++i) {
            int cnt = 0;
            int startOfSection = startTimes[i];
            int endOfSection = startOfSection + 1000;

            for (int j = 0; j < lines.length; ++j) {
                if (startTimes[j] >= startOfSection && startTimes[j] < endOfSection) {
                    cnt++;
                } else if (endTimes[j] >= startOfSection && endTimes[j] < endOfSection) {
                    cnt++;
                } else if (startTimes[j] <= startOfSection && endTimes[j] >= endOfSection) {
                    cnt++;
                }
            }

            answer = cnt > answer ? cnt : answer;
        }

        for (int i = 0; i < lines.length; ++i) {
            int cnt = 0;
            int startOfSection = endTimes[i];
            int endOfSection = startOfSection + 1000;

            for (int j = 0; j < lines.length; ++j) {
                if (startTimes[j] >= startOfSection && startTimes[j] < endOfSection) {
                    cnt++;
                } else if (endTimes[j] >= startOfSection && endTimes[j] < endOfSection) {
                    cnt++;
                } else if (startTimes[j] <= startOfSection && endTimes[j] >= endOfSection) {
                    cnt++;
                }
            }

            answer = cnt > answer ? cnt : answer;
        }

        return answer;
    }

    static void calcTimes(String[] lines, int[] startTimes, int[] endTimes) {
        for (int i = 0; i < lines.length; i++) {
            String[] log = lines[i].split(" ");
            String[] responseTime = log[1].split(":");
            int processingTime = (int) (Double.parseDouble(log[2].substring(0, log[2].length() - 1)) * 1000);
            int startTime = 0;
            int endTime = 0;

            endTime += Integer.parseInt(responseTime[0]) * 60 * 60 * 1000;
            endTime += Integer.parseInt(responseTime[1]) * 60 * 1000;
            endTime += (int) (Double.parseDouble(responseTime[2]) * 1000);
            startTime = endTime - processingTime + 1;

            startTimes[i] = startTime;
            endTimes[i] = endTime;
        }
    }
}
