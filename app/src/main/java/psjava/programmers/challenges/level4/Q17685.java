package psjava.programmers.challenges.level4;

import java.util.*;

/**
 * 2022.06.02 - 프로그래머스 - #17685 [3차] 자동완성
 * <p>
 * LEVEL 4
 * <p>
 * 문자열
 * <p>
 * 2018 KAKAO BLIND RECRUITMENT
 *
 * @see <a href="https://programmers.co.kr/learn/courses/30/lessons/17685">https://programmers.co.kr/learn/courses/30/lessons/17685</a>
 */
public class Q17685 {

    public int solution(String[] words) {
        Arrays.sort(words);
        int[] result = new int[words.length];

        for (int i = 0; i < words.length - 1; i++) {
            int shorter = Math.min(words[i].length(), words[i + 1].length());
            int firstDiff = findFirstDiff(words[i], words[i + 1], shorter);

            if (shorter == firstDiff) {
                result[i] = firstDiff;
            } else {
                result[i] = Math.max(result[i], firstDiff + 1);
            }
            result[i + 1] = firstDiff + 1;
        }

        return Arrays.stream(result).sum();
    }

    static int findFirstDiff(String word1, String word2, int len) {
        int i = 0;
        for (i = 0; i < len; i++) {
            char c1 = word1.charAt(i);
            char c2 = word2.charAt(i);

            if (c1 != c2) {
                break;
            }
        }
        return i;
    }
}
