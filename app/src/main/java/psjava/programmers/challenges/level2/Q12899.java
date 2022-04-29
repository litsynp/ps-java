package psjava.programmers.challenges.level2;

import java.util.*;

/**
 * 2022.04.29 - 프로그래머스 - #12899 124 나라의 숫자
 */
public class Q12899 {

    public String solution(int n) {

        // Basic idea: convert to ternary and to 124 digit number.
        // *4 is regarded as 0

        // 1. if n IS NOT ternary: (e.g. 5)
        // 5 / 3 = 1 ... 2
        // 1 / 3 = 0 ... 1
        // numbers[1] + numbers[2] = "12"

        // 2. if n IS ternary: (e.g. 6)
        // 6 / 3 = 2 ... 0
        // 2 / 3 = 0 ... 2
        // numbers[2] + numbers[0] = "24"
        // There is 4 instead of 0 in 124 country, so need to decrease by 1
        // hence, "14"

        String[] numbers = { "4", "1", "2" };
        StringBuilder answer = new StringBuilder();
        while (n > 0) {
            answer.insert(0, numbers[n % 3]);
            n = n / 3 - (n % 3 == 0 ? 1 : 0);
        }

        return answer.toString();
    }
}
