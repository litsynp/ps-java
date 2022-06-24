package psjava.programmers.challenges.level2;

import java.util.*;

/**
 * 2022.06.24 - 프로그래머스 - #64065 튜플
 * <p>
 * LEVEL 2
 * <p>
 * 2019 카카오 개발자 겨울 인턴십
 * <p>
 * 알고리즘, 구현
 *
 * @see <a href="https://programmers.co.kr/learn/courses/30/lessons/64065">https://programmers.co.kr/learn/courses/30/lessons/64065</a>
 */
public class Q64065 {

    public int[] solution(String s) {
        // e.g. s = "{{1,2,3},{2,1},{1,2,4,3},{2}}"
        String[] arr = s.replaceAll("[{}]", " ").trim().split(" , ");
        // arr = {"1,2,3,", "2,1,", "1,2,4,3,", "2"}

        // Sort by length
        Arrays.sort(arr, Comparator.comparingInt(String::length));
        // arr = {"2", "2,1,", "1,2,3,", "1,2,4,3,"}

        Set<String> set = new HashSet<>();
        int[] answer = new int[arr.length];
        int idx = 0;
        for (String s1 : arr) {
            for (String s2 : s1.split(",")) {
                // s2 contains each number
                // Add to set
                if (set.add(s2)) {
                    // and if new number has been added,
                    // set number as element of answer
                    answer[idx++] = Integer.parseInt(s2);
                }
            }
        }

        return answer;
    }
}
