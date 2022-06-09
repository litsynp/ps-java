package psjava.programmers.challenges.level3;

import java.util.*;

/**
 * 2022.06.09 - 프로그래머스 - #17678 [1차] 셔틀버스
 * <p>
 * LEVEL 3
 * <p>
 * 2018 KAKAO BLIND RECRUITMENT
 * <p>
 * 구현
 *
 * @see <a href="https://moonsbeen.tistory.com/372">https://moonsbeen.tistory.com/372</a>
 * @see <a href="https://programmers.co.kr/learn/courses/30/lessons/17678">https://programmers.co.kr/learn/courses/30/lessons/17678</a>
 */
public class Q17678 {

    public String solution(int n, int t, int m, String[] timetable) {
        // 우선 순위 큐로 시간 오름차순 정렬
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (String timeStr : timetable) {
            String[] timeStrArr = timeStr.split(":");
            int hour = Integer.parseInt(timeStrArr[0]);
            int minute = Integer.parseInt(timeStrArr[1]);

            int crewArrivalTime = hour * 60 + minute;
            pq.add(crewArrivalTime);
        }

        // 시작 시간 09:00
        int startTime = 9 * 60;
        int lastTime = 0;
        int total = 0;

        // * 9시부터 태우기 시작.
        // 셔틀버스가 오는 매 시간에 대해서
        for (int i = 0; i < n; i++) {
            total = 0;

            // 한 명씩 버스에 태운다
            while (!pq.isEmpty()) {
                // 기다리기 시작한 시간 확인
                int curTime = pq.peek();
                // 현재 버스 도착 전까지 와있었다면 && 아직 더 태울 수 있다면
                if (curTime <= startTime && total < m) {
                    pq.poll();
                    total++;
                } else {
                    break;
                }
                // 크루가 탈 때마다 마지막 시간을 재계산한다.
                // * 마지막 버스면 콘이 탑승해야 한다.
                // 마지막 버스에 탄 마지막 사람이 온 시간보단 1분 빨리 와야 한다.
                lastTime = curTime - 1;
            }

            // 버스 시간 추가
            startTime += t;
        }

        // 현재 버스가 다 태우지 않고 끝났다면
        // (마지막 셔틀 버스에 탄 인원이 m보다 작다면)
        // 해당 셔틀 버스에 콘이 탈 수 있다.
        // 버스의 도착 시간이 마지노선.
        if (total < m) {
            lastTime = startTime - t;
        }

        String hour = String.format("%02d", lastTime / 60);
        String minute = String.format("%02d", lastTime % 60);
        return hour + ":" + minute;
    }
}

