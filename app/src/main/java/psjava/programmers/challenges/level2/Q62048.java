package psjava.programmers.challenges.level2;

/**
 * 2022.04.28 - 프로그래머스 - #62048 멀쩡한 사각형
 *
 * 수학적 사고 - GCD
 */
public class Q62048 {

    public long solution(int w, int h) {
        long answer = 1;

        long width = w;
        long height = h;
        long gcd = getGcd(width, height);

        // 전체 개수 - 사용 불가능한 사각형 개수
        // 사용 불가능한 사각형의 개수 = w + h - 최대공약수
        answer = width * height - (width + height - gcd);

        return answer;
    }

    long getGcd(long a, long b) {
        if (b == 0) {
            return a;
        }

        return getGcd(b, a % b);
    }
}
