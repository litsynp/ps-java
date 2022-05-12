package psjava.programmers.challenges.level2;

import java.util.*;

/**
 * 2022.05.13 - 프로그래머스 - #42895 N으로 표현
 *
 * - 점화식 도출 어려움.
 * - DP 이용
 *
 * REF: https://bangu4.tistory.com/226
 */
public class Q42895 {

    public int solution(int N, int number) {
        int answer = -1;

        // N을 1개부터 8개까지 사용해서 만들 수 있는 수
        Set<Integer>[] counts = new Set[9];

        int t = N;
        for (int i = 1; i <= 8; i++) {
            counts[i] = new HashSet<>();

            // N, NN, NNN, NNNN, ..., NNNNNNNN
            counts[i].add(t);
            t = t * 10 + N;
        }

        for (int i = 1; i <= 8; i++) {
            for (int j = 1; j < i; j++) {
                for (int a : counts[j]) {
                    for (int b : counts[i - j]) {
                        // xN + N
                        counts[i].add(a + b);

                        // xN - N, N - xN
                        counts[i].add(a - b);
                        counts[i].add(b - a);

                        // nN * N
                        counts[i].add(a * b);

                        // nN / N
                        if (b != 0) {
                            counts[i].add(a / b);
                        }

                        if (a != 0) {
                            counts[i].add(b / a);
                        }
                    }
                }
            }
        }

        for (int i = 1; i < 9; i++) {
            if (counts[i].contains(number)) {
                answer = i;
                break;
            }
        }

        return answer;
    }
}
