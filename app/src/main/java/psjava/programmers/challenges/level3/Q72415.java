package psjava.programmers.challenges.level3;

import java.util.*;

/**
 * 2022.06.08 - 프로그래머스 - #72415 카드 짝 맞추기
 * <p>
 * LEVEL 3
 * <p>
 * 2021 KAKAO BLIND RECRUITMENT
 *
 * @see <a href="https://loosie.tistory.com/170">https://loosie.tistory.com/170</a>
 * @see <a href="https://programmers.co.kr/learn/courses/30/lessons/72415">https://programmers.co.kr/learn/courses/30/lessons/72415</a>
 */
public class Q72415 {

    final int[] dx = {1, -1, 0, 0};
    final int[] dy = {0, 0, 1, -1};
    List<String> orders;

    public int solution(int[][] board, int r, int c) {
        // 남아있는 카드 짝 개수 구하기
        int cardNum = initCardNum(board);
        int[] cards = initCards(cardNum);

//        System.out.println(Arrays.toString(cards));

        // 카드의 순서 순열 생성 - orders
        orders = new ArrayList<>();
        permutation("", 0, cards);

        int answer = Integer.MAX_VALUE;

        for (String order : orders) {
            // 카드의 순서
            String[] combi = order.split("");

            int totalMove = 0;
            int[] pos = new int[2];
            pos[0] = r;
            pos[1] = c;

            // Copy
            int[][] copyBoard = Arrays.stream(board).map(int[]::clone).toArray(int[][]::new);

            for (String targetCardStr : combi) {
                int targetCard = Integer.parseInt(targetCardStr);

                // Find first card
                totalMove += bfs(copyBoard, pos, targetCard);
                copyBoard[pos[0]][pos[1]] = 0;

                // Enter
                totalMove++;

                // Find second card
                totalMove += bfs(copyBoard, pos, targetCard);
                copyBoard[pos[0]][pos[1]] = 0;

                // Enter
                totalMove++;
            }

            answer = Math.min(answer, totalMove);
        }

        return answer;
    }

    private int[] initCards(int cardNum) {
        int[] cards = new int[cardNum];
        for (int i = 0; i < cardNum; i++) {
            cards[i] = i + 1;
        }
        return cards;
    }

    private int initCardNum(int[][] board) {
        int cardNum = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] != 0) {
                    cardNum++;
                }
            }
        }
        cardNum /= 2;
        return cardNum;
    }

    // Search card
    int bfs(int[][] board, int[] pos, int targetCard) {
        Queue<Node> q = new LinkedList<>();
        boolean[][] check = new boolean[4][4];

        int x = pos[0];
        int y = pos[1];
        check[x][y] = true;
        q.add(new Node(x, y, 0));

        while (!q.isEmpty()) {
            Node next = q.poll();
            int px = next.x;
            int py = next.y;
            int move = next.move;

            if (board[px][py] == targetCard) {
                // Found the target at (px, py)
//                System.out.println("Target " + targetCard + " found at (" + px + ", " + py + ")");
                pos[0] = px;
                pos[1] = py;
                return move;
            }

            // Move 1 block
            for (int i = 0; i < 4; i++) {
                int nx = px + dx[i];
                int ny = py + dy[i];

                if (isOut(nx, ny)) {
                    continue;
                }

                if (check[nx][ny]) {
                    continue;
                }

                check[nx][ny] = true;
                q.add(new Node(nx, ny, move + 1));
            }

            // Move with ctrl
            for (int i = 0; i < 4; i++) {
                Node res = checkRoute(px, py, i, board);
                int nx = res.x;
                int ny = res.y;

                if (nx == x && ny == y) {
                    continue;
                }

                if (check[nx][ny]) {
                    continue;
                }

                check[nx][ny] = true;
                q.add(new Node(nx, ny, move + 1));
            }
        }

        return 0;
    }

    boolean isOut(int x, int y) {
        return x < 0 || x >= 4 || y < 0 || y >= 4;
    }

    /**
     * 뒤집을 카드의 순서를 모든 경우의 수로 orders에 담는다.
     */
    void permutation(String selected, int depth, int[] cards) {
        if (depth == cards.length) {
            orders.add(selected);
            return;
        }

        for (int i = 0; i < cards.length; i++) {
            int num = cards[i];
            String numStr = String.valueOf(num);

            // 카드를 고른 적 없다면
            if (!selected.contains(numStr)) {
                permutation(selected + num, depth + 1, cards);
            }
        }
    }

    Node checkRoute(int x, int y, int direction, int[][] board) {
        x += dx[direction];
        y += dy[direction];

        while (!isOut(x, y)) {
            if (board[x][y] != 0) {
                return new Node(x, y, 0);
            }

            x += dx[direction];
            y += dy[direction];
        }

        // 카드가 없으면 종료
        return new Node(x - dx[direction], y - dy[direction], 0);
    }

    class Node {
        int x, y, move;

        public Node(int x, int y, int move) {
            this.x = x;
            this.y = y;
            this.move = move;
        }
    }
}
