package psjava.programmers.challenges.level2;

/**
 * 2022.05.04 - 프로그래머스 - #92335 k진수에서 소수 개수 구하기
 *
 * 2022 KAKAO BLIND RECRUITMENT
 *
 * - n을 k 진법으로 변환
 * - n 최대 1_000_000, k 최소 2
 * - 최대 길이 len(1_212_210_202_001) = 13
 * - 따라서 int 범위를 넘어가므로 에라토스테네스의 체를 쓸 수 없음
 */
public class Q92335 {

    public int solution(int n, int k) {

        String[] values = Integer.toString(n, k).split("0{1,}");
        int answer = 0;

        for (int i = 0; i < values.length; i++) {
            if (isPrime(Long.parseLong(values[i]))) {
                answer++;
            }
        }

        return answer;
    }

    private boolean isPrime(long n) {
        if (n < 2) {
            return false;
        }

        for (int div = 2; div <= Math.sqrt(n); div++) {
            if (n % div == 0) {
                return false;
            }
        }
        return true;
    }
}
