package psjava.algorithm.iteration;

/**
 * 순열 (Permutation)
 *
 * - nPr
 * - 서로 다른 n개중에 r개를 선택하는 경우의 수를 의미한다. (순서 상관 있음)
 * - [1, 2] != [2, 1]
 *
 * REF:
 * https://velog.io/@cgw0519/%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-%EC%88%9C%EC%97%B4-%EC%A4%91%EB%B3%B5%EC%88%9C%EC%97%B4-%EC%A1%B0%ED%95%A9-%EC%A4%91%EB%B3%B5%EC%A1%B0%ED%95%A9-%EC%B4%9D%EC%A0%95%EB%A6%AC
 */
public class Permutation {

    /**
     * 서로 다른 n개중에 r개를 선택. (순서 상관 있음)
     *
     * @param arr     선택 가능한 수가 담긴 배열
     * @param out     현재 선택된 수가 순서대로 담긴 배열
     * @param visited 중복 선택을 방지하기 위해 선택된 원소를 담은 배열
     * @param depth   현재 선택한 수의 갯수. 0부터 시작
     * @param r       선택할 개수. depth가 r이 되면 현재 경우 종료
     */
    public static void perm(int[] arr, int[] out, boolean[] visited, int depth, int r) {
        if (depth == r) {
            for (int i = 0; i < out.length; i++) {
                System.out.print(out[i] + " ");
            }
            System.out.println();
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                out[depth] = arr[i];
                perm(arr, out, visited, depth + 1, r);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5 };
        int r = 3;
        perm(arr, new int[r], new boolean[arr.length], 0, r);
    }
}
