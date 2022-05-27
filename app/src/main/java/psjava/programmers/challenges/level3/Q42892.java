package psjava.programmers.challenges.level3;

import java.util.*;

/**
 * 2022.05.26 - 프로그래머스 - #42892 길 찾기 게임
 * <p>
 * LEVEL 3
 * <p>
 * 2019 KAKAO BLIND RECRUITMENT
 * <p>
 * 이진 트리 순회 (전위 순회, 후위 순회)
 */
public class Q42892 {

    public static void main(String[] args) {
        int[][] nodeinfo = {{5, 3}, {11, 5}, {13, 3}, {3, 5}, {6, 1}, {1, 3}, {8, 6}, {7, 2}, {2, 2}};
        solution(nodeinfo);
    }

    static int[][] result = {};

    static int idx;

    // 카카오 프렌즈를 "두 팀"으로 나누고, 각 팀이 "같은 곳을 다른 순서로" 방문하도록 해서 먼저 순회를 마친 팀이 승리
    public static int[][] solution(int[][] nodeinfo) {
        // 노드를 입력받아서 1차원 배열에 넣는다. (정렬 없이)
        Node[] node = new Node[nodeinfo.length];
        for (int i = 0; i < nodeinfo.length; i++) {
            node[i] = new Node(nodeinfo[i][0], nodeinfo[i][1], i + 1, null, null);
        }

        System.out.println("node = " + Arrays.toString(node));

        // y, x의 좌표에 따라 정렬한다.
        // y가 클 수록 0번 인덱스에 가깝다.
        // y가 같으면, x가 작을 수록 0번 인덱스에 가깝다.
        Arrays.sort(node, (o1, o2) -> {
            if (o1.y == o2.y) {
                // 같은 y 좌표라면
                return o1.x - o2.x;
            } else {
                return o2.y - o1.y;
            }
        });

        System.out.println("node = " + Arrays.toString(node));

        // 트리 생성
        Node root = node[0];
        for (int i = 1; i < node.length; i++) {
            insertNode(root, node[i]);
        }

        result = new int[2][nodeinfo.length];
        idx = 0;
        preorder(root); // 전위 순회
        idx = 0;
        postorder(root); // 후위 순회

        return result;
    }

    static void preorder(Node root) {
        if (root != null) {
            result[0][idx++] = root.value;
            preorder(root.left);
            preorder(root.right);
        }
    }

    static void postorder(Node root) {
        if (root != null) {
            postorder(root.left);
            postorder(root.right);
            result[1][idx++] = root.value;
        }
    }

    static void insertNode(Node parent, Node child) {
        // 대소 비교 후 재귀적으로 빈 곳에 넣는다
        if (parent.x > child.x) {
            if (parent.left == null) {
                parent.left = child;
            } else {
                insertNode(parent.left, child);
            }
        } else {
            if (parent.right == null) {
                parent.right = child;
            } else {
                insertNode(parent.right, child);
            }
        }
    }

    static class Node {
        int x, y, value;

        Node left, right;

        public Node(int x, int y, int value, Node left, Node right) {
            this.x = x;
            this.y = y;
            this.value = value;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", y=" + y +
                    ", value=" + value +
                    '}';
        }
    }
}
