package psjava.programmers;

/**
 * 2022.03.23 - 프로그래머스 - #76501 음양 더하기
 */
public class Q76501 {

    public int solution(int[] absolutes, boolean[] signs) {
        int answer = 0;
        for (int i = 0; i < absolutes.length; i++) {
            answer += signs[i] ? absolutes[i] : -absolutes[i];
        }
        return answer;
    }
}
