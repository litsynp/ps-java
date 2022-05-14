package psjava.programmers.challenges.level3;

import java.util.*;

/**
 * 2022.05.06 - 프로그래머스 - #92343 양과 늑대
 *
 * 2022 KAKAO BLIND RECRUITMENT
 *
 * Backtracking
 *
 * REF:
 * https://velog.io/@topqr123q/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-2022-KAKAO-BLIND-RECRUITMENT-%EC%96%91%EA%B3%BC-%EB%8A%91%EB%8C%80-by-Java
 */
public class Q92343 {

    private static int max = 0;
    private static List<Integer>[] childs;

    public int solution(int[] info, int[][] edges) {

        // 그래프 초기화
        initGraph(info, edges);

        // DFS로 완전 탐색
        // 다음으로 갈 수 있는 노드들의 번호
        ArrayList<Integer> available = new ArrayList<>();
        available.add(0);
        dfs(info, available, 0, 0, 0);

        return max;
    }

    private static void initGraph(int[] info, int[][] edges) {
        childs = new ArrayList[info.length];
        for (int[] edge : edges) {
            int parent = edge[0];
            int child = edge[1];

            if (childs[parent] == null) {
                childs[parent] = new ArrayList<>();
            }
            childs[parent].add(child);
        }
    }

    private static void dfs(int[] info, ArrayList<Integer> available, int cur, int sheepCnt, int wolfCnt) {

        sheepCnt += info[cur] ^ 1; // XOR 1 for !info[cur]
        wolfCnt += info[cur];

        // 답 갱신
        max = Math.max(sheepCnt, max);

        if (sheepCnt <= wolfCnt) {
            // 잡아먹히는 결과
            return;
        }

        // 다음으로 갈 수 있는 노드 갱신
        // 각각 경우의 수마다 필요하므로 새로 생성
        ArrayList<Integer> copyAvailable = new ArrayList<>();
        copyAvailable.addAll(available);
        if (childs[cur] != null) {
            copyAvailable.addAll(childs[cur]);
        }
        // 현재 방문한 노드는 제거
        copyAvailable.remove((Integer) cur);

        for (int next : copyAvailable) {
            dfs(info, copyAvailable, next, sheepCnt, wolfCnt);
        }
    }
}
