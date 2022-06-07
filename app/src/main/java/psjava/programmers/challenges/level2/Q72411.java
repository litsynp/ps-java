package psjava.programmers.challenges.level2;

import java.util.*;
import java.util.stream.*;

/**
 * 2022.06.07 - 프로그래머스 - #72411 메뉴 리뉴얼
 * <p>
 * LEVEL 2
 * 2021 KAKAO BLIND RECRUITMENT
 *
 * @see <a href="https://programmers.co.kr/learn/courses/30/lessons/72411">https://programmers.co.kr/learn/courses/30/lessons/72411</a>
 */
public class Q72411 {

    // 코스: 주문한 횟수
    Map<String, Integer> map;
    int max;

    public String[] solution(String[] orders, int[] course) {
        List<String> answer = new ArrayList<>();
        map = new HashMap<>();

        for (int num : course) {
            max = 0;

            for (String order : orders) {
                comb(order, "", 0, 0, num);
            }

            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                if (entry.getValue() == max && entry.getValue() > 1) {
                    answer.add(entry.getKey());
                }
            }

            map.clear();
        }

        Collections.sort(answer);
        return answer.toArray(new String[0]);
    }

    public void comb(String orig, String selected, int start, int depth, int r) {
        if (depth == r) {
            char[] c = selected.toCharArray();
            Arrays.sort(c);
            String res = new String(c);
            map.put(res, map.getOrDefault(res, 0) + 1);
            max = Math.max(max, map.get(res));
            return;
        }

        for (int i = start; i < orig.length(); i++) {
            char now = orig.charAt(i);
            comb(orig, selected + now, i + 1, depth + 1, r);
        }
    }
}
