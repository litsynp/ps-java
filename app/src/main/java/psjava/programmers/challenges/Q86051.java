package psjava.programmers.challenges;

/**
 * 2022.03.23 - 프로그래머스 - #86051 없는 숫자 더하기
 */
public class Q86051 {

    public int solution(int[] numbers) {
        int answer = 45;

        for (int n : numbers) {
            answer -= n;
        }

        return answer;
    }
}
