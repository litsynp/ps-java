package psjava.programmers.challenges.level2;

import java.util.*;

/**
 * 2022.06.11 - 프로그래머스 - #60058 괄호 변환
 * <p>
 * LEVEL 2
 * <p>
 * 2020 KAKAO BLIND RECRUITMENT
 * <p>
 * 문자열
 *
 * @see <a href="https://programmers.co.kr/learn/courses/30/lessons/60058">https://programmers.co.kr/learn/courses/30/lessons/60058</a>
 */
public class Q60058 {

    // 균형 잡힌 괄호 문자열 -> 올바른 괄호 문자열 변환
    public String solution(String p) {
        return procedure(p);
    }

    String procedure(String w) {
        // 1
        if (w.length() == 0) {
            return "";
        }

        // 2
        String[] in = splitIntoBalanced(w);
        String u = in[0];
        String v = in[1];
        if (isCorrect(u)) { // 3
            // 3-1
            u += procedure(v);
            return u;
        } else { // 4
            // 4-1, 4-2, 4-3
            String res = "(" + procedure(v) + ")";
            // 4-4
            res += reverseParen(u.substring(1, u.length() - 1));
            // 4-5
            return res;
        }
    }

    String reverseParen(String w) {
        StringBuilder reversed = new StringBuilder();
        for (int i = 0; i < w.length(); i++) {
            reversed.append(w.charAt(i) == '(' ? ')' : '(');
        }
        return reversed.toString();
    }

    String[] splitIntoBalanced(String w) {
        StringBuilder u = new StringBuilder();
        String v = "";

        int openCnt = 0;
        int closeCnt = 0;
        for (char c : w.toCharArray()) {
            if (c == '(') {
                openCnt++;
            } else {
                closeCnt++;
            }
            u.append(c);

            if (openCnt != 0 && openCnt == closeCnt) {
                v = w.substring(u.length());
                break;
            }
        }

        return new String[]{u.toString(), v};
    }

    boolean isCorrect(String w) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < w.length(); i++) {
            char c = w.charAt(i);

            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                if (!stack.isEmpty()) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}

