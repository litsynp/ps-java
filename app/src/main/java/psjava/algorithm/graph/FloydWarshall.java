package psjava.algorithm.graph;

import java.io.*;
import java.util.*;

import psjava.util.*;

/**
 * Floyd-Warshall Algorithm (플로이드-워셜 알고리즘)
 *
 * 그래프에서 가능한 모든 노드 쌍에 대해 최단 거리를 구하는 알고리즘이다.
 *
 * D_ab = min(D_ab, D_ak + D_kb)
 *
 * - a, b, k에 대해서 N번 반복하므로, O(N^3) 의 시간 복잡도를 갖는다.
 *
 * - N:N일 때 Dijkstra's가 아닌 FW를 사용하면 좋다.
 *
 * BJ #11404 참고
 */
public class FloydWarshall {

    static int N, M;
    static int[][] graph;
    static final int INF = (int) 1e9;

    public static void main(String[] args) throws IOException {

        N = Integer.parseInt(ps.br.readLine());
        M = Integer.parseInt(ps.br.readLine());

        graph = new int[N + 1][N + 1];

        // INF로 초기화
        for (int[] row : graph) {
            Arrays.fill(row, INF);
        }

        // 자기 자신으로 가는 비용은 0으로 초기화
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) {
                    graph[i][j] = 0;
                }
            }
        }

        for (int i = 0; i < M; i++) {
            int[] in = ps.getIntInputs();
            int a = in[0];
            int b = in[1];
            int c = in[2];

            graph[a][b] = c;
        }

        floydWarshall();

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (graph[i][j] == INF) {
                    ps.sb.append("INF ");
                } else {
                    ps.sb.append(graph[i][j]).append(" ");
                }
            }
            ps.sb.append("\n");
        }

        ps.close();
    }

    private static void floydWarshall() {
        for (int k = 1; k <= N; k++) {
            for (int a = 1; a <= N; a++) {
                for (int b = 1; b <= N; b++) {
                    graph[a][b] = Math.min(graph[a][b], graph[a][k] + graph[k][b]);
                }
            }
        }
    }
}
