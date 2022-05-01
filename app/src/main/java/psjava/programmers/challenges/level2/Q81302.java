package psjava.programmers.challenges.level2;

import java.util.*;

/**
 * 2022.05.01 - 프로그래머스 - #81302 거리두기 확인하기
 *
 * 2021 카카오 채용연계형 인턴십 문제
 *
 * 조건부 BFS
 *
 * REF: https://moonsbeen.tistory.com/297
 */
public class Q81302 {

    static int[] dx = { -1, 1, 0, 0 };
    static int[] dy = { 0, 0, -1, 1 };

    public int[] solution(String[][] places) {

        int[] answer = new int[places.length];
        for (int i = 0; i < places.length; i++) {
            answer[i] = isCorrect(places[i]) ? 1 : 0;
        }
        return answer;
    }

    // 각 P에 대해서 실행하고, 한 가지라도 실패하면 false
    static boolean isCorrect(String[] p) {
        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                // 모든 자리 P에 대해서 검사
                if (p[x].charAt(y) == 'P') {
                    if (!bfs(p, x, y)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    static boolean bfs(String[] p, int x, int y) {
        Queue<Node> q = new LinkedList<>();
        boolean[][] visited = new boolean[5][5];

        // 시작 자리 P
        q.offer(new Node(x, y));
        visited[x][y] = true;

        // P로부터 맨하튼 거리가 2 이하인 자리 검사
        while (!q.isEmpty()) {
            // 현재 자리
            Node cur = q.poll();

            // 사방 탐색
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5) {
                    continue;
                }

                // 시작 자리와의 맨하튼 거리
                int d = getDist(nx, ny, x, y);

                // 이미 방문했거나 현재 자리의 "맨하튼 거리가 2보다 크면" 조회 필요 X
                if (visited[nx][ny] || d > 2) {
                    continue;
                }

                // 방문 처리
                visited[nx][ny] = true;

                // 맨하튼 거리가 2 이하인 경우
                Character ch = p[nx].charAt(ny);
                if (ch == 'X') {
                    // 현재 자리가 X면 무시
                    continue;
                } else if (ch == 'P') {
                    // 현재 자리가 P면 안됨
                    return false;
                }

                // 다음 자리 O에 대해서는 탐색
                q.offer(new Node(nx, ny));
            }
        }

        return true;
    }

    // 맨하튼 거리
    static int getDist(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    static class Node {
        int x, y;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
