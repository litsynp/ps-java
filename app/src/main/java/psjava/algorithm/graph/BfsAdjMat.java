package psjava.algorithm.graph;

import java.io.*;
import java.util.*;

import psjava.util.*;

/**
 * BFS (너비 우선 탐색, Breadth-First Search) - 인접 행렬 방식
 *
 * 2차원 배열을 이용해 BFS를 실시한다.
 *
 * https://bbangson.tistory.com/42
 */
public class BfsAdjMat {

    static int N, M, V;
    static int[][] map;
    static boolean[] visited;

    public static void bfs(int start) {
        visited[start] = true;
        ps.sb.append(start).append(" ");

        Queue<Integer> q = new LinkedList<>();
        q.add(start);

        while (!q.isEmpty()) {
            int v = q.poll();

            for (int i = 1; i < map.length; i++) {
                // 방문하지 않은 정점이 있는 경우
                if (map[v][i] == 1 && visited[i] == false) {
                    q.add(i);
                    visited[i] = true;
                    ps.sb.append(i).append(" ");
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {

        int[] in = Arrays.stream(ps.br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = in[0];
        M = in[1];
        V = in[2];
        map = new int[N + 1][N + 1];
        visited = new boolean[N + 1];

        // 초기 경로 세팅
        for (int i = 0; i < M; i++) {
            int[] in2 = Arrays.stream(ps.br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int a = in2[0];
            int b = in2[1];

            // 인접 행렬 방식 - 번호가 작은 것부터 방문한다.
            map[a][b] = 1;
            map[b][a] = 1;
        }

        bfs(V);

        ps.sb.append("\n");
        ps.close();
    }
}
