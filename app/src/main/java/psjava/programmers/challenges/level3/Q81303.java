package psjava.programmers.challenges.level3;

import java.util.*;

/**
 * 2022.05.01 - 프로그래머스 - #81303 표 편집
 *
 * REF: https://moonsbeen.tistory.com/294
 *
 * - 효율성 테스트를 위해 연결 리스트를 이용한 O(n + 1,000,000)으로 구현
 * - 35번 줄에서 유의 `U X` 또는 `D X`에서 X의 길이가 2 이상인 경우에 유의 (substring 이용)
 */
public class Q81303 {

    public String solution(int n, int k, String[] cmd) {

        // 각 노드의 이전과 다음 노드
        int[] pre = new int[n];
        int[] nxt = new int[n];

        for (int i = 0; i < n; i++) {
            pre[i] = i - 1;
            nxt[i] = i + 1;
        }
        nxt[n - 1] = -1;

        Stack<Node> deletedRows = new Stack<>();
        StringBuilder answer = new StringBuilder("O".repeat(n));

        for (String c : cmd) {
            char op = c.charAt(0);

            // U or D
            if (op == 'U') {
                int num = getDistance(c);

                // 한 칸씩 이전 행 조회
                while (num-- > 0) {
                    k = pre[k];
                }
            } else if (op == 'D') {
                int num = getDistance(c);

                // 한 칸씩 다음 행 조회
                while (num-- > 0) {
                    k = nxt[k];
                }
            } else if (op == 'C') {
                deletedRows.push(new Node(pre[k], k, nxt[k]));

                // 현재 노드 삭제 후 앞뒤 연결
                if (pre[k] != -1) {
                    nxt[pre[k]] = nxt[k];
                }
                if (nxt[k] != -1) {
                    pre[nxt[k]] = pre[k];
                }
                answer.setCharAt(k, 'X');

                if (nxt[k] != -1) {
                    k = nxt[k];
                } else {
                    // 현재 행이 마지막 행인 경우, 바로 윗 행 선택
                    k = pre[k];
                }
            } else {
                // op == 'Z'
                Node lastDeleted = deletedRows.pop();

                // 노드를 복구하고 앞뒤 연결
                if (lastDeleted.pre != -1) {
                    nxt[lastDeleted.pre] = lastDeleted.cur;
                }
                if (lastDeleted.nxt != -1) {
                    pre[lastDeleted.nxt] = lastDeleted.cur;
                }

                answer.setCharAt(lastDeleted.cur, 'O');
            }
        }

        return answer.toString();
    }

    private int getDistance(String s) {
        return Integer.parseInt(s.substring(2));
    }

    static class Node {
        int pre, cur, nxt;

        Node(int pre, int cur, int nxt) {
            this.pre = pre;
            this.cur = cur;
            this.nxt = nxt;
        }
    }
}
