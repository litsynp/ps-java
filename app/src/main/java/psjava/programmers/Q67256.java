package psjava.programmers;

import java.util.*;

/**
 * 2022.03.22 - 프로그래머스 - #67256 키패드 누르기
 */
public class Q67256 {

    static class Num {
        int x, y;

        Num(int x, int y) {
            this.x = x;
            this.y = y;
        }

        double getDiff(Num num) {
            // 대각선 이동 불가능
            return Math.abs(x - num.x) + Math.abs(y - num.y);
        }
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[] { 7, 9, 5, 0, 0, 0 }, "left"));
    }

    public static String solution(int[] numbers, String hand) {
        Map<String, Num> map = new HashMap<>();
        map.put("1", new Num(0, 0));
        map.put("2", new Num(0, 1));
        map.put("3", new Num(0, 2));
        map.put("4", new Num(1, 0));
        map.put("5", new Num(1, 1));
        map.put("6", new Num(1, 2));
        map.put("7", new Num(2, 0));
        map.put("8", new Num(2, 1));
        map.put("9", new Num(2, 2));
        map.put("*", new Num(3, 0));
        map.put("0", new Num(3, 1));
        map.put("#", new Num(3, 2));

        String answer = "";
        Num lpos = map.get("*");
        Num rpos = map.get("#");

        boolean leftHanded = hand.equals("left");

        for (int number : numbers) {
            Num cur = map.get(Integer.toString(number));

            if (number == 0 || number % 3 == 2) {
                double ld = lpos.getDiff(cur);
                double rd = rpos.getDiff(cur);

                if (ld < rd) {
                    lpos = cur;
                    answer += "L";
                } else if (ld == rd) {
                    if (leftHanded) {
                        lpos = cur;
                        answer += "L";
                    } else {
                        rpos = cur;
                        answer += "R";
                    }
                } else {
                    rpos = cur;
                    answer += "R";
                }
            } else if (number % 3 == 1) {
                lpos = cur;
                answer += "L";
            } else {
                rpos = cur;
                answer += "R";
            }
        }

        return answer;
    }
}
