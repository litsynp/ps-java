package psjava.baekjoon;

import java.io.*;
import java.util.*;

/**
 * 2022.04.14 - 백준 - #1450 냅색문제
 *
 * #meet-in-the-middle
 */
public class Q1450 {

    static int N, C, index;
    static int[] weights;
    static List<Integer> sumA = new ArrayList<>();
    static List<Integer> sumB = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        ps.init();

        int[] in = Arrays.stream(ps.br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        N = in[0];
        C = in[1];

        weights = Arrays.stream(ps.br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // N을 반으로 나눈다.
        // 1. weights[start ~ N / 2] -> sumA
        // 2. weights[N / 2 ~ end] -> sumB
        // 각각 완전탐색(BF)을 하여, C 이하로 만들 수 있는 모든 무게를 찾아
        // 각각 배열에 담는다.
        bruteForce(sumA, 0, N / 2, 0);
        bruteForce(sumB, N / 2, N, 0);

        // sumA, sumB 중 하나에 대해 이진탐색을 수행하기 위해
        // 기준이 아닌 다른 배열을 오름차순으로 정렬
        Collections.sort(sumB);

        int answer = 0;

        // sumA 각각의 모든 무게에 대해 sumB의 무게 중 하나를 합쳐서,
        // C보다 작은 sumB에서의 인덱스를 찾는다.
        for (int i = 0; i < sumA.size(); i++) {
            index = -1;

            // sumA의 각각의 무게를 더한 bSum에 대해 이진 탐색 수행
            binarySearch(0, sumB.size() - 1, sumA.get(i));
            answer += index + 1;
        }

        ps.sb.append(answer).append("\n");
        ps.close();
    }

    static void bruteForce(List<Integer> sumArr, int i, int bound, int sum) {
        if (sum > C) {
            return;
        }

        if (i == bound) {
            sumArr.add(sum);
            return;
        }

        // 다음 물건을 추가하는 경우
        bruteForce(sumArr, i + 1, bound, sum + weights[i]);

        // 다음 물건을 추가하지 않고 다음으로 넘어가는 경우
        bruteForce(sumArr, i + 1, bound, sum);
    }

    static void binarySearch(int start, int end, int val) {
        if (start > end) {
            return;
        }

        int mid = (start + end) / 2;

        if (sumB.get(mid) + val <= C) {
            // sumA 각각의 무게에 대해 sumB 중 하나의 무게를 더했을 때,
            // C보다 작은 sumB에서의 인덱스를 찾았더라도,
            index = mid;

            // start > end가 될 때까지 계속해서 이진탐색을 진행해 나간다.
            binarySearch(mid + 1, end, val);
        } else {
            binarySearch(start, mid - 1, val);
        }
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
