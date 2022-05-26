package psjava.programmers.challenges.level2;

import java.util.*;

/**
 * 2022.05.26 - 프로그래머스 - #42890 후보키
 *
 * LEVEL 2
 *
 * 후보키: 튜플을 유일하게 식별할 수 있는 속성 / 속성 집합 중 두 가지 성질을 만족하면 후보 키라고 한다.
 * - 유일성: 릴레이션에 있는 모든 튜플에 대해 "유일하게 식별" 되어야 한다.
 * - 최소성: 튜플을 유일하게 식별하는 데 "꼭 필요한 것만" 넣기
 *
 * https://loosie.tistory.com/430
 */
public class Q42890 {

    // 필드 개수
    static int col;
    static int row;

    // 비트 마스크
    static String bits;

    // 각 속성의 집합
    static String[][] relations;
    static List<Integer> answers;

    public static int solution(String[][] relation) {
        relations = relation;
        row = relations.length;
        col = relations[0].length;
        answers = new ArrayList<>();

        for (int i = 0; i < col; i++) {
            Stack<Integer> stack = new Stack<>();
            boolean[] visited = new boolean[col];
            comb(stack, visited, 0, i + 1);
        }

        return answers.size();
    }

    static void comb(Stack<Integer> stack, boolean[] visited, int pos, int target) {
        if (stack.size() == target) {
            List<Integer> selectedFields = new ArrayList<>();
            for (int num : stack) {
                selectedFields.add(num);
            }
            // System.out.println(selectedFields);

            // 유일성 확인
            if (isUnique(selectedFields)) {
                // 비트 마스킹 (선택한 row)
                int cur = toBinary(selectedFields);
                // System.out.println(Integer.toBinaryString(cur));

                // 최소성 확인
                if (isMinimal(cur)) {
                    answers.add(cur);
                }
            }
            return;
        }

        // 필드의 조합 생성
        for (int i = pos; i < col; i++) {
            if (!visited[i]) {
                visited[i] = true;
                stack.push(i);
                comb(stack, visited, i, target);
                stack.pop();
                visited[i] = false;
            }
        }
    }

    // 비트 마스킹
    static int toBinary(List<Integer> selectedFields) {
        int cur = 0;
        for (int field : selectedFields) {
            cur |= 1 << field;
        }
        return cur;
    }

    // 유일성 검사
    // 해당 집합이 중복 컬럼이 있는지 검사
    static boolean isUnique(List<Integer> rows) {
        Set<String> set = new HashSet<>();

        for (int i = 0; i < row; i++) {
            String s = "";

            // 각 필드의 값을 하나의 String으로 합친다
            for (int row : rows) {
                s += relations[i][row];
            }

            // 이미 존재하면 유일성 탈락
            if (set.contains(s)) {
                return false;
            }
            set.add(s);
        }

        return true;
    }

    // 최소성 아닌지 검사
    // 해당 집합이 어떤 집합의 부분 집합은 아닌지
    // 제일 크기가 작은 집합부터 검사하므로,
    // 크기가 큰 집합이 이미 최소성을 만족하는 해당 집합을 포함한다면 최소성
    static boolean isMinimal(int cur) {
        for (int i = 0; i < answers.size(); i++) {
            int ans = answers.get(i);

            // 비트 마스킹 활용
            // answers = [1000, 0110] (유일성, 최소성 만족한 친구들)

            // 검사할 친구가 0111

            // cur과 1000 비교 => 1000 & 0111 = 0000 => 성공
            // cur과 0110 비교 => 0110 & 0111 = 0110 => 실패
            // => 최종 실패

            if ((ans & cur) == ans) {
                return false;
            }
        }

        return true;
    }
}
