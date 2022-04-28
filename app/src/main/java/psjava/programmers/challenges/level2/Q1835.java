package psjava.programmers.challenges.level2;

import java.util.*;

/**
 * 2022.04.28 - 프로그래머스 - #1835 단체사진 찍기
 *
 * 순열을 이용한 완전 탐색, 조건에 맞지 않으면 skip
 */
public class Q1835 {

    static Map<Character, Integer> friends = Map.ofEntries(
            Map.entry('A', 0),
            Map.entry('C', 1),
            Map.entry('F', 2),
            Map.entry('J', 3),
            Map.entry('M', 4),
            Map.entry('N', 5),
            Map.entry('R', 6),
            Map.entry('T', 7));

    static String[] d;
    static int[] position = new int[8]; // 현재 프렌즈가 서있는 위치
    static boolean[] visited = new boolean[8]; // 방문 여부
    static int answer;

    public int solution(int n, String[] data) {
        d = data;
        answer = 0;
        System.out.println(friends);
        perm(0);
        return answer;
    }

    static void perm(int idx) {
        if (idx == 8) {
            // 8개를 다 뒀다면 조건 검사
            if (isOk()) {
                answer++;
            }
            return;
        }

        // 8개를 하나씩 놔본다
        for (int i = 0; i < 8; i++) {
            if (visited[i]) {
                continue;
            }

            visited[i] = true;

            // 한 반복마다,
            // 같은 depth에서 i에는 0부터 7까지 들어간다
            position[idx] = i;

            // 두고 나서는 다음 i도 체크
            perm(idx + 1);

            visited[i] = false;
        }
    }

    static boolean isOk() {
        // 현재 position에 대해 각 조건 검사
        for (String s : d) {
            int a = friends.get(s.charAt(0));
            int b = friends.get(s.charAt(2));

            char op = s.charAt(3);
            int diff = s.charAt(4) - '0';

            // a와 b 사이의 거리
            int dist = Math.abs(position[a] - position[b]) - 1;

            if (op == '=') {
                if (dist != diff) {
                    return false;
                }
            } else if (op == '>') {
                if (dist <= diff) {
                    return false;
                }
            } else if (op == '<') {
                if (dist >= diff) {
                    return false;
                }
            }
        }
        return true;
    }
}
