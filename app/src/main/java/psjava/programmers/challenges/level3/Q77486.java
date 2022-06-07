package psjava.programmers.challenges.level3;

import java.util.*;

/**
 * 2022.06.07 - 프로그래머스 - #77486 다단계 칫솔 판매
 * <p>
 * LEVEL 3
 * <p>
 * 2021 Dev-Matching: 웹 백엔드 개발자(상반기)
 * <p>
 * 유사 트리
 *
 * @see <a href="https://programmers.co.kr/learn/courses/30/lessons/77486">https://programmers.co.kr/learn/courses/30/lessons/77486</a>
 */
public class Q77486 {

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];

        Map<String, String> parentMap = new HashMap<>(); // 자식: 부모
        Map<String, Integer> memberIdxMap = new HashMap<>(); // 자신: 자신의 순서

        for (int i = 0; i < enroll.length; i++) {
            parentMap.put(enroll[i], referral[i]);
            memberIdxMap.put(enroll[i], i);
        }

        for (int i = 0; i < seller.length; i++) {
            // 판매원의 이름
            String now = seller[i];

            // 칫솔의 가격 100원
            int profit = 100 * amount[i];

            while (!now.equals("-")) {
                int profitForParent = profit / 10; // 부모에게 줄 금액
                int profitForSelf = profit - profitForParent; // 자신이 가져갈 금액

                answer[memberIdxMap.get(now)] += profitForSelf;

                // 부모 노드로 이동 및 수익은 부모에게 넘겨준 금액으로 초기화
                now = parentMap.get(now);
                profit /= 10;

                // 10% 금액 배분했을 때 1원 미만이면 정지
                if (profit < 1) {
                    break;
                }
            }
        }

        return answer;
    }
}
