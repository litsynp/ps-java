package psjava.programmers.challenges.level2;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 2022.04.28 - 프로그래머스 - #42626 더 맵게
 *
 * 성능을 높이기 위한 우선순위 큐
 */
public class Q42626 {

    public int solution(int[] scoville, int K) {
        int answer = 0;

        PriorityQueue<Integer> pq = Arrays.stream(scoville)
                .boxed()
                .collect(Collectors.toCollection(PriorityQueue::new));

        while (pq.peek() < K) {
            try {
                int newFood = pq.poll() + pq.poll() * 2;
                pq.offer(newFood);
                answer++;

                if (pq.size() == 1 && pq.peek() < K) {
                    answer = -1;
                    break;
                }
            } catch (Exception e) {
                // 더 이상 섞을 수 없음
                answer = -1;
                break;
            }
        }

        return answer;
    }
}
