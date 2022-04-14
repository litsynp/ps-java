package psjava.programmers.challenges;

/**
 * 2022.03.23 - 프로그래머스 - #70128 내적
 */
public class Q70128 {

    public int solution(int[] a, int[] b) {
        int answer = 0;
        for (int i = 0; i < a.length; i++) {
            answer += a[i] * b[i];
        }
        return answer;
    }
}
