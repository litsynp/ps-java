package psjava.programmers.challenges;

/**
 * 2022.03.23 - 프로그래머스 - #12977 소수 만들기
 */
public class Q12977 {

    static int N = 2997;
    static boolean[] sieve = new boolean[N + 1];
    static int answer = 0;

    public int solution(int[] nums) {
        sift();
        boolean[] selected = new boolean[1001];

        // 조합으로 3개를 뽑는다
        combi(nums, selected, 0, nums.length, 3);

        return answer;
    }

    static int getNumber(int[] arr, boolean[] selected) {
        int res = 0;
        for (int i = 0; i < selected.length; i++) {
            if (selected[i]) {
                res += arr[i];
            }
        }
        return res;
    }

    static void combi(int[] arr, boolean[] selected, int start, int n, int r) {
        if (r == 0) {
            int num = getNumber(arr, selected);
            if (!sieve[num]) {
                answer++;
            }

            return;
        } else {
            for (int i = start; i < n; i++) {
                selected[i] = true;
                combi(arr, selected, i + 1, n, r - 1);
                selected[i] = false;
            }
        }
    }

    static void sift() {
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
}
