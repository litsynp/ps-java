package psjava.programmers.challenges.level2;

import java.util.*;

/**
 * 2022.06.02 - 프로그래머스 - #17687 [3차] n진수 게임
 * <p>
 * LEVEL 2
 * <p>
 * 문자열
 * <p>
 *
 * @see <a href="https://programmers.co.kr/learn/courses/30/lessons/17685">https://programmers.co.kr/learn/courses/30/lessons/17685</a>
 */
public class Q17687 {

    public String solution(int n, int t, int m, int p) {
        // n진법으로 m명이 돌아가면서 진행
        // 튜브의 순서는 p번째 (1 <= p <= m)
        // 튜브가 말해야할 숫자 t개를 구해야 됨
        StringBuilder answer = new StringBuilder();

        int cnt = 0;
        Loop:
        for (int i = 0; i < t * p * m; i++) {
            String[] res = convertToK(i, n).split("");

            for (String re : res) {
                if (cnt == (p - 1)) {
                    answer.append(re);

                    if (answer.length() == t) {
                        break Loop;
                    }
                }
                cnt++;
                if (cnt == m) {
                    cnt = 0;
                }
            }
        }

        return answer.toString();
    }

    /**
     * 10진수를 K진수로 변환
     */
    static String convertToK(int decimal, int K) {
        StringBuilder sb = new StringBuilder();
        int current = decimal;

        if (current == 0) {
            return "0";
        }

        while (current > 0) {
            // N진법으로 나누었는데 10보다 작으면 바로 추가
            if (current % K < 10) {
                sb.append(current % K);
            }
            // 10이상은 알파벳으로 표기한다.
            else {
                // 10진수를 구해주는 법과 반대로 수행해주면 된다. 10이상의 수부터 표현할 수 있기 때문에 10을 빼주는 것이다.
                sb.append((char) (current % K - 10 + 'A'));
            }
            current /= K;
        }
        return sb.reverse().toString();
    }
}
