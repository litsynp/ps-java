package psjava.programmers.challenges.level2;

import java.util.*;

/**
 * 2022.04.28 - 프로그래머스 - #42586 기능개발
 */
public class Q42586 {

    public int[] solution(int[] progresses, int[] speeds) {

        boolean done;
        int[] durations = new int[progresses.length];

        while (true) {
            // Check if at least 1 task is left
            done = true;
            for (int i = 0; i < progresses.length; i++) {
                if (progresses[i] < 100) {
                    done = false;
                    break;
                }
            }
            if (done) {
                break;
            }

            // Proceed task
            for (int i = 0; i < progresses.length; i++) {
                if (progresses[i] < 100) {
                    progresses[i] += speeds[i];
                    durations[i] += 1;
                }
            }
        }

        Map<Integer, Integer> map = new LinkedHashMap<>();
        map.put(durations[0], 1);
        for (int i = 1; i < durations.length; i++) {
            durations[i] = Math.max(durations[i - 1], durations[i]);
            if (map.containsKey(durations[i])) {
                map.put(durations[i], map.get(durations[i]) + 1);
            } else {
                map.put(durations[i], 1);
            }
        }

        return map.values().stream().mapToInt(i -> i).toArray();
    }
}
