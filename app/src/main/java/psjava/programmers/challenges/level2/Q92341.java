package psjava.programmers.challenges.level2;

import java.util.*;

/**
 * 2022.05.04 - 프로그래머스 - #92341 주차 요금 계산
 *
 * 2022 KAKAO BLIND RECRUITMENT
 */
public class Q92341 {

    public int[] solution(int[] fees, String[] records) {

        // 차량번호 : 입차시간 (in minute)
        Map<String, Integer> map = new HashMap<>();

        // 차량번호 : 주차요금 (사전 순 정렬)
        Map<String, Integer> result = new TreeMap<>();

        for (String s : records) {
            String[] in = s.split(":|\\s");
            int hour = Integer.parseInt(in[0]);
            int minute = Integer.parseInt(in[1]);
            int val = 60 * hour + minute;
            String carNum = in[2];
            String type = in[3];

            if (type.equals("IN")) {
                // 주차 시작
                map.put(carNum, val);
                if (!result.containsKey(carNum)) {
                    result.put(carNum, 0);
                }
            } else {
                // 주차 종료
                int duration = val - map.get(carNum);
                map.remove(carNum);
                result.replace(carNum, result.get(carNum) + duration);
            }
        }

        for (String carNum : map.keySet()) {
            // 입차 시간만 남은 차량 존재 확인
            int duration = 23 * 60 + 59 - map.get(carNum);
            map.remove(carNum);
            if (result.containsKey(carNum)) {
                result.replace(carNum, result.get(carNum) + duration);
            } else {
                result.put(carNum, duration);
            }
        }

        for (String carNum : result.keySet()) {
            int duration = result.get(carNum);
            result.replace(carNum, calcFee(fees, duration));
        }

        return result.values().stream().mapToInt(i -> i).toArray();
    }

    private int calcFee(int[] fees, int time) {
        // 기본 요금
        int fee = fees[1];
        if (time > fees[0]) {
            // 기본 시간 초과
            fee += Math.ceil((double) (time - fees[0]) / fees[2]) * fees[3];
        }

        return fee;
    }
}
