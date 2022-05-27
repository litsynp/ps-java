package psjava.programmers.challenges.level1;

import java.util.*;

/**
 * 2022.05.27 - 프로그래머스 - #42889 실패율
 *
 * LEVEL 1
 *
 * 2019 KAKAO BLIND RECRUITMENT
 */
public class Q42889 {

    public int[] solution(int N, int[] stages) {
        // 각 스테이지마다 클리어한 수
        int[] cleared = new int[N + 1];
        int[] arrived = new int[N + 1];
        for (int stage : stages) {
            cleared[stage - 1] += 1;

            for (int j = 0; j < stage; j++) {
                arrived[j] += 1;
            }
        }

        List<Node> list = new ArrayList<>();
        for (int i = 1; i < N + 1; i++) {
            double failRate = cleared[i - 1] != 0 ? getFailRate(cleared[i - 1], arrived[i - 1]) : 0;
            Node node = new Node(i, failRate);
            list.add(node);
        }
        Collections.sort(list);

        int[] answer = new int[N];
        for (int i = 0; i < N; i++) {
            answer[i] = list.get(i).idx;
        }

        return answer;
    }

    static class Node implements Comparable<Node> {

        int idx;
        double failRate;

        public Node(int idx, double failRate) {
            this.idx = idx;
            this.failRate = failRate;
        }

        @Override
        public String toString() {
            return "(" + idx + "," + failRate + ")";
        }

        @Override
        public int compareTo(Node o) {
            if (failRate != o.failRate) {
                return failRate < o.failRate ? 1 : -1;
            } else if (idx != o.idx) {
                return idx > o.idx ? 1 : -1;
            }
            return 0;
        }
    }

    // 실패율 = 스테이지에 도달했으나 아직 클리어하지 못한 플레이어의 수 / 스테이지에 도달한 플레이어 수
    static double getFailRate(int arrivedButNotCleared, int arrived) {
        return (double) arrivedButNotCleared / (double) arrived;
    }
}
