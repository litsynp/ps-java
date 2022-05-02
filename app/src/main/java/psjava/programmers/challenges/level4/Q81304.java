package psjava.programmers.challenges.level4;

import java.util.*;

/**
 * 2022.05.02 - 프로그래머스 - #81304 미로 탈출
 *
 * 우선순위 큐 + 다익스트라
 *
 * - BFS에서 더 나아가 가중치가 필요하므로 다익스트라
 * - 똑같은 경로여도 작은 가중치를 가진 간선을 위주로 탐색해야 하므로 우선순위 큐
 *
 * REF: https://loosie.tistory.com/341,
 * REF2: https://bellog.tistory.com/196?category=909112
 */
public class Q81304 {

    private static final int INF = Integer.MAX_VALUE;

    public int solution(int n, int start, int end, int[][] roads, int[] traps) {

        int[][] graph = new int[n + 1][n + 1];
        Arrays.stream(graph).forEach(x -> Arrays.fill(x, INF));

        // Init graph
        for (int i = 1; i <= n; i++) {
            graph[i][i] = 0;
        }

        // 여러 경로가 존재할 수 있으므로 최단거리를 저장
        for (int[] road : roads) {
            graph[road[0]][road[1]] = Math.min(graph[road[0]][road[1]], road[2]);
        }

        // 트랩 활성 상태를 비트마스크로 저장
        boolean[][] visited = new boolean[n + 1][1 << traps.length];

        // Dijkstra's
        Queue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            int state = cur.state;

            // pq에서 뽑히는 원소는 가까운 순이라는 점을 이용, end일 때 바로 답을 반환
            if (cur.num == end) {
                return cur.cost;
            }

            if (visited[cur.num][cur.state]) {
                continue;
            }

            visited[cur.num][cur.state] = true;

            boolean curTrapped = false;
            Set<Integer> trapped = new HashSet<>();
            for (int i = 0; i < traps.length; i++) {
                int bit = 1 << i;

                // bit에 해당하는 trap이 활성 상태인 경우
                if ((state & bit) != 0) {
                    // 그 trap이 현재 노드인 경우
                    if (cur.num == traps[i]) {
                        // trap 비활성화
                        state &= ~bit;
                    }

                    trapped.add(traps[i]);
                } else {
                    // bit에 해당하는 trap이 활성 상태가 아닌 경우
                    // 그 trap이 현재 노드인 경우
                    if (cur.num == traps[i]) {
                        // 트랩 비활성화
                        state |= bit;
                        trapped.add(traps[i]);
                        curTrapped = true;
                    }
                }
            }

            for (int i = 1; i <= n; i++) {
                if (cur.num == i) {
                    continue;
                }

                // 다음 이동할 노드가 trap인지 체크
                boolean nextTrapped = trapped.contains(i);

                if (curTrapped == nextTrapped) {
                    // 현재 노드, 다음 노드가 둘 다 트랩 또는 둘다 아니거나 결과는 같음
                    if (graph[cur.num][i] != INF) {
                        pq.add(new Node(i, cur.cost + graph[cur.num][i], state));
                    }
                } else {
                    // 둘 중 하나가 트랩이라면 그래프 역방향 이동
                    if (graph[i][cur.num] != INF) {
                        pq.add(new Node(i, cur.cost + graph[i][cur.num], state));
                    }
                }
            }
        }

        return INF;
    }

    private class Node implements Comparable<Node> {

        int num, cost, state;

        private Node(int num, int cost, int state) {
            this.num = num;
            this.cost = cost;
            this.state = state;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}
