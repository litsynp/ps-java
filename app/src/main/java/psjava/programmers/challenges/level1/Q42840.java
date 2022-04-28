package psjava.programmers.challenges.level1;

import java.util.*;

/**
 * 2022.04.20 - 프로그래머스 - #42840 모의고사
 */
public class Q42840 {

    public int[] solution(int[] answers) {
        int[][] patterns = {
                { 1, 2, 3, 4, 5 },
                { 2, 1, 2, 3, 2, 4, 2, 5 },
                { 3, 3, 1, 1, 2, 2, 4, 4, 5, 5 }
        };

        int[] counts = new int[3];
        for (int i = 0; i < answers.length; i++) {
            for (int n = 0; n < 3; n++) {
                if (answers[i] == patterns[n][i % patterns[n].length]) {
                    counts[n] += 1;
                }
            }
        }

        int max = Math.max(counts[0], Math.max(counts[1], counts[2]));
        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] == max) {
                answer.add(i + 1);
            }
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }
}
