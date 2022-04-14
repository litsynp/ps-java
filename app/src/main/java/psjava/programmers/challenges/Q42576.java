package psjava.programmers.challenges;

import java.util.*;

/**
 * 2022.03.23 - 프로그래머스 - #42576 완주하지 못한 선수
 */
public class Q42576 {

    public String solution(String[] participant, String[] completion) {

        String answer = "";
        Map<String, Integer> hm = new HashMap<>();

        // 참가자 초기화
        for (String player : participant) {
            // 해당 player가 없으면 새로 추가
            // 동명이인이 있다면 +1
            hm.put(player, hm.getOrDefault(player, 0) + 1);
        }

        // 완주자 초기화 - 완주했다면 HashMap에서 -1 (완주한 사람만큼 제외)
        for (String player : completion) {
            hm.put(player, hm.get(player) - 1);
        }

        // 완주하지 않았다면 0이 아니므로, 0이 아닌 것을 찾으면 return
        for (String key : hm.keySet()) {
            if (hm.get(key) != 0) {
                answer = key;
                break;
            }
        }
        return answer;
    }
}
