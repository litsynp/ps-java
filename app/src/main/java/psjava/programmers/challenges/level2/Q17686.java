package psjava.programmers.challenges.level2;

import java.util.*;
import java.util.regex.*;

/**
 * 2022.06.02 - 프로그래머스 - #17686 [3차] 파일명 정렬
 * <p>
 * LEVEL 2
 * <p>
 * 문자열
 * <p>
 * 2018 KAKAO BLIND RECRUITMENT
 *
 * @see <a href="https://programmers.co.kr/learn/courses/30/lessons/17686">https://programmers.co.kr/learn/courses/30/lessons/17686</a>
 */
public class Q17686 {

    public String[] solution(String[] files) {
        List<Node> list = new ArrayList<>();
        for (int i = 0; i < files.length; i++) {
            String[] part = files[i].split("(?<=\\D)(?=\\d)");

            String head = part[0];
            String number = "";
            Pattern pattern = Pattern.compile("\\d+");
            Matcher matcher = pattern.matcher(part[1]);
            if (matcher.find()) {
                number = matcher.group();
            }

            Node node = new Node(i, files[i], head, number);
            list.add(node);
        }

        Collections.sort(list);
        return list.stream().map(n -> n.orig).toArray(String[]::new);
    }

    static class Node implements Comparable<Node> {
        int idx;
        String orig;
        String head;
        String number;

        @Override
        public String toString() {
            return "Node{" +
                    "idx=" + idx +
                    ", head='" + head + '\'' +
                    ", number='" + number + '\'' +
                    '}';
        }

        public Node(int idx, String orig, String head, String number) {
            this.idx = idx;
            this.orig = orig;
            this.head = head.toLowerCase();
            this.number = number;
        }

        @Override
        public int compareTo(Node o) {
            int order1 = this.head.compareTo(o.head);
            if (order1 != 0) {
                return order1;
            }

            int i1 = Integer.parseInt(this.number);
            int i2 = Integer.parseInt(o.number);
            int order2 = Integer.compare(i1, i2);
            if (order2 != 0) {
                return order2;
            }

            return Integer.compare(this.idx, o.idx);
        }
    }
}
