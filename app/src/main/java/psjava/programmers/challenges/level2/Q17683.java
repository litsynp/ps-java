package psjava.programmers.challenges.level2;

import java.util.*;

/**
 * 2022.05.15 - 프로그래머스 - #17683 방금그곡
 *
 * 2018 KAKAO BLIND RECRUITMENT
 */
public class Q17683 {

    private static Map<String, String> map;

    public String solution(String m, String[] musicinfos) {
        map = new HashMap<>();

        map.put("C#", "H");
        map.put("D#", "I");
        map.put("F#", "J");
        map.put("G#", "K");
        map.put("A#", "L");

        for (String key : map.keySet()) {
            m = m.replaceAll(key, map.get(key));
        }

        String answer = "(None)";

        int maxSongLen = 0;
        for (String s : musicinfos) {
            String[] in = s.split(",");
            int[] startTime = Arrays.stream(in[0].split(":")).mapToInt(Integer::parseInt).toArray();
            int[] endTime = Arrays.stream(in[1].split(":")).mapToInt(Integer::parseInt).toArray();
            int duration = (endTime[0] * 60 + endTime[1]) - (startTime[0] * 60 + startTime[1]);

            String title = in[2];
            String melody = in[3];

            for (String key : map.keySet()) {
                melody = melody.replaceAll(key, map.get(key));
            }

            int dividend = duration;
            int divisor = melody.length();
            int quotient = dividend / divisor;
            int remainder = dividend % divisor;

            melody = melody.repeat(quotient) + melody.substring(0, remainder);

            if (melody.contains(m)) {
                if (maxSongLen <= melody.length()) {
                    if (maxSongLen == melody.length()) {
                        continue;
                    }

                    maxSongLen = melody.length();
                    answer = title;
                }
            }
        }

        return answer;
    }
}
