package psjava.programmers.challenges.level1;

import java.util.*;

/**
 * 2022.03.22 - 프로그래머스 - #77484 로또의 최고 순위와 최저 순위
 * <p>
 * LEVEL 1
 * <p>
 * 2021 Dev-Matching: 웹 백엔드 개발자(상반기)
 *
 * @see <a href=https://programmers.co.kr/learn/courses/30/lessons/77484">https://programmers.co.kr/learn/courses/30/lessons/77484</a>
 */
public class Q77484 {

    public int[] solution(int[] lottos, int[] win_nums) {
        Set<Integer> set = new HashSet<>();

        int eqCnt = 0;
        int zeroCnt = 0;

        for (int lotto : lottos) {
            if (lotto == 0) {
                // 0이면 지워진 숫자이므로, 불확정한 순위를 결정지음
                zeroCnt++;
            } else {
                // 0이 아니라면, 기본 순서를 확정지음
                set.add(lotto);
            }
        }

        for (int winNum : win_nums) {
            if (set.contains(winNum)) {
                eqCnt++;
            }
        }

        int h = 7 - (eqCnt + zeroCnt);
        int l = 7 - eqCnt;
        if (h > 6) {
            h = 6;
        }
        if (l > 6) {
            l = 6;
        }

        return new int[]{h, l};
    }
}
