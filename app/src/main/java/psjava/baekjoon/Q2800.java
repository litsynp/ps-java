package psjava.baekjoon;

import java.io.*;
import java.util.*;

/**
 * 2022.04.24 - 백준 - #2800 괄호 제거
 *
 * - combination을 이용한 완전 탐색
 * - Set을 이용한 결과 사전순 출력 및 중복 제거
 * (괄호가 여럿 겹쳐 있으면 다른 괄호쌍을 지우더라도 중복 발생 가능F)
 */
public class Q2800 {

    static List<int[]> brackets = new ArrayList<>();
    static Set<String> result = new TreeSet<>();
    static boolean[] check;

    public static void main(String[] args) throws IOException {

        char[] in = ps.br.readLine().toCharArray();
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < in.length; i++) {
            if (in[i] == '(') {
                stack.push(i);
            } else if (in[i] == ')') {
                // (, ) 괄호쌍의 시작 및 끝 index 저장
                int start = stack.pop();
                brackets.add(new int[] { start, i });
            }
        }

        check = new boolean[in.length];
        comb(0, in);

        result.stream().forEach(ps.sb::append);
        ps.close();
    }

    static void comb(int depth, char[] str) {
        if (depth == brackets.size()) {
            boolean isDifferentFromInput = false;
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < str.length; i++) {
                if (!check[i]) {
                    sb.append(str[i]);
                } else {
                    // 괄호쌍이 하나라도 제거됐다면 결과 추가함
                    isDifferentFromInput = true;
                }
            }

            if (isDifferentFromInput) {
                // 처음 입력값과 다르다면 결과 추가
                result.add(sb.append("\n").toString());
            }
            return;
        }

        // 괄호쌍을 제거하지 않은 상태의 다음 결과를 가져옴
        comb(depth + 1, str);

        // 해당 depth의 괄호쌍 정보
        int[] bracket = brackets.get(depth);

        check[bracket[0]] = true;
        check[bracket[1]] = true;

        // 해당 괄호쌍을 제거한 상태의 다음 결과를 가져옴
        comb(depth + 1, str);

        check[bracket[0]] = false;
        check[bracket[1]] = false;
    }

    static class ps {

        public static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        public static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        public static final StringBuilder sb = new StringBuilder();

        public static void close() throws IOException {
            bw.write(sb.toString());
            br.close();
            bw.close();
        }

        public static int[] getIntInputs() throws IOException {
            return Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
    }
}
