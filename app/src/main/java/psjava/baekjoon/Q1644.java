package psjava.baekjoon;

import java.io.*;
import java.util.*;

/**
 * 2022.04.12 - 백준 - #1644 소수의 연속합
 */
public class Q1644 {

    static int N;
    static boolean[] sieve;
    static List<Integer> primes = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        ps.init();

        N = Integer.parseInt(ps.br.readLine());
        sieve = new boolean[N + 1];

        // 에라토스테네스의 체로 소수를 구한다
        sift();

        for (int i = 0; i < N + 1; i++) {
            if (!sieve[i]) {
                primes.add(i);
            }
        }

        int start = 0;
        int end = 0;
        int sum = 0;
        int answer = 0;

        while (true) {
            if (sum >= N) {
                // start를 밀고 부분합에서 뺀다
                sum -= primes.get(start++);
            } else if (end == primes.size()) {
                // 합이 N보다 일단 작은 상태에서 end가 끝에 도달했다면,
                // start를 미는 등 더 이상 확인할 필요가 없다
                break;
            } else {
                // end를 밀고 부분합에 더한다
                sum += primes.get(end++);
            }

            if (N == sum) {
                answer++;
            }
        }

        ps.sb.append(answer).append("\n");
        ps.close();
    }

    public static void sift() {
        // 소수는 false
        // 0, 1은 소수에서 제외
        sieve[0] = sieve[1] = true;

        for (int i = 2; i * i <= N; i++) {
            // i가 소수면 i의 배수 j는 소수가 아니다.
            if (!sieve[i]) {
                for (int j = i * i; j <= N; j += i) {
                    sieve[j] = true;
                }
            }
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
