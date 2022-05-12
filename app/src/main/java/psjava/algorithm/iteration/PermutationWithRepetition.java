package psjava.algorithm.iteration;

/**
 * 중복 순열 (Permutation with Repetition)
 *
 * - nπr
 * - 중복 가능한 n개중에서 r개를 선택하는 경우의 수를 의미한다. (순서 상관 있음)
 * - 순서가 있게 뽑는 순열과 동일하지만, 이미 뽑은 것을 또 뽑을 수 있음 (중복 허용)
 * - 순열 코드에서 중복을 체크하기 위해 사용하는 visited를 제거하면 됨
 *
 * REF:
 * https://velog.io/@cgw0519/%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98-%EC%88%9C%EC%97%B4-%EC%A4%91%EB%B3%B5%EC%88%9C%EC%97%B4-%EC%A1%B0%ED%95%A9-%EC%A4%91%EB%B3%B5%EC%A1%B0%ED%95%A9-%EC%B4%9D%EC%A0%95%EB%A6%AC
 */
public class PermutationWithRepetition {

    /**
     * 중복 가능한 n개중에서 r개를 선택. (순서 상관 있음)
     *
     * @param arr   선택 가능한 수를 담은 배열
     * @param out   현재 선택된 수가 담긴 배열
     * @param start 0부터 시작
     * @param depth 현재 선택한 수의 갯수. 0부터 시작
     * @param r     선택할 개수. depth가 r이 되면 현재 경우 종료
     */
    public static void perm(int[] arr, int[] out, int depth, int r) {
        if (depth == r) {
            for (int i = 0; i < out.length; i++) {
                System.out.print(out[i] + " ");
            }
            System.out.println();
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            out[depth] = arr[i];
            perm(arr, out, depth + 1, r);
        }
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5 };
        int r = 3;
        perm(arr, new int[r], 0, r);
    }
}
