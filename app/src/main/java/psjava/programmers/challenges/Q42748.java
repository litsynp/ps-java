package psjava.programmers.challenges;

import java.util.*;

/**
 * 2022.04.20 - 프로그래머스 - #42748 K번째수
 */
public class Q42748 {

    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        for (int i = 0; i < commands.length; i++) {
            int[] command = commands[i];
            int[] sub = Arrays.copyOfRange(array, command[0] - 1, command[1]);
            Arrays.sort(sub);
            answer[i] = sub[command[2] - 1];
        }
        return answer;
    }
}
