package psjava.programmers.challenges.level2;

/**
 * 2022.05.13 - 프로그래머스 - #43165 타겟 넘버
 *
 * n개의 음이 아닌 정수들이 있습니다. 이 정수들을 순서를 바꾸지 않고 적절히 더하거나 빼서 타겟 넘버를 만들려고 합니다.
 *
 * DFS / BFS
 */
public class Q43165 {

    public int solution(int[] numbers, int target) {
        return dfs(numbers, 0, 0, target);
    }

    public static int dfs(int[] numbers, int depth, int sum, int target) {
        if (depth == numbers.length) {
            if (sum == target) {
                return 1;
            }
            return 0;
        }

        return dfs(numbers, depth + 1, sum + numbers[depth], target)
                + dfs(numbers, depth + 1, sum - numbers[depth], target);
    }
}
