package psjava.baekjoon;

import java.io.*;
import java.util.*;

/**
 * 2022.04.11 - 백준 - #1806 부분합
 */
public class Q1806 {

    public static void main(String[] args) throws IOException {
        ps.init();

        int[] in = Arrays.stream(ps.br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = in[0];
        int S = in[1];
        int[] arr = Arrays.stream(ps.br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int first = 0;
        int second = 0;
        int answer = Integer.MAX_VALUE;
        int sum = 0;
        while (true) {
            if (sum >= S) {
                // first를 한 칸 밀고 값을 뺀다
                sum -= arr[first];
                first++;

                // 길이 최솟값 비교
                answer = Math.min(answer, second - first + 1);
            } else if (second == N) {
                // sum이 S보다 작은 상태에서 second가 끝에 도달
                // first를 밀면 더 작아지므로 더 이상 확인할 필요가 없다
                break;
            } else {
                // second를 밀면서 값을 더한다
                sum += arr[second];
                second++;
            }
        }

        answer = answer == Integer.MAX_VALUE ? 0 : answer;
        ps.sb.append(answer).append("\n");
        ps.close();
    }

    static class ps {

        public static BufferedReader br;
        public static BufferedWriter bw;
        public static StringBuilder sb;

        public static void init() {
            br = new BufferedReader(new InputStreamReader(System.in));
            bw = new BufferedWriter(new OutputStreamWriter(System.out));
            sb = new StringBuilder();
        }

        public static void close() throws IOException {
            bw.write(sb.toString());
            br.close();
            bw.close();
        }

        public static int[] getIntInputs() throws IOException {
            return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
    }
}
