package psjava.algorithm;

import java.io.*;

import psjava.util.ps;

/**
 * k진법 변환
 *
 * Alternative: Integer.toString(i, radix)로 간편하게 변환 가능
 *
 * REF: https://bbangson.tistory.com/80
 */
public class NotationConversion {

    public static void main(String[] args) throws IOException {

        System.out.println("몇 진수를 입력하시겠습니까? (2~16만입력만 가능합니다.)");
        int N = Integer.parseInt(ps.br.readLine());

        System.out.println("수를 입력해 주세요. 10이상은 대문자를 입력하십시오.");
        String number = ps.br.readLine();

        // 입력값 검사
        switch (isNotationError(N, number)) {
            case INPUT_ERROR:
                System.out.println("잘못된 입력입니다.");
                System.exit(0);
            case UNSUPPORTED_NOTATION:
                System.out.println("계산할 수 없는 진수입니다. 프로그램을 다시 실행하십시오.");
                System.exit(0);
            default:
        }

        // 먼저 입력 받은 각각의 진수를 10진법으로 바꿔준다.
        int decimal = toDecimal(N, number);

        System.out.println("몇 진수로 바꾸시겠습니까?");
        int K = Integer.parseInt(ps.br.readLine());
        String answer = convertToK(decimal, K);

        ps.sb.append("결과 : ").append(answer).append("\n");
        ps.close();
    }

    private enum NotationCode {
        SUCCESS, INPUT_ERROR, UNSUPPORTED_NOTATION
    }

    /** N진수를 10진수로 변환 */
    public static int toDecimal(int N, String number) {

        int result = 0;

        // 입력이 10진법인 경우 바로 리턴
        if (N == 10) {
            return Integer.parseInt(number);
        } else if (N >= 2 && N <= 16) {
            // 2에서 16진수까지만 허용 (나머지는 isNotationError에서 검사)
            char[] num = number.toCharArray();
            /*
             * 0부터 시작하여 진수와 곱해주고 각 자리 수 숫자를 더해준다.
             * 그리고 그 결과를 다음 자리수로 갈 때 진수와 곱해주고 현재 자리수 값을 더해준다.
             * 반복.
             */
            for (int i = 0; i < num.length; i++) {
                // 10이상의 숫자는 'A'를 빼고 +10을 해주면 된다.
                if (num[i] >= 'A') {
                    result = result * N + (num[i] - 'A' + 10);
                } else {
                    result = result * N + (num[i] - '0');
                }
            }
        }

        return result;
    }

    /** 10진수를 K진수로 변환 */
    public static String convertToK(int decimal, int K) {

        StringBuilder sb = new StringBuilder();
        int current = decimal;

        while (current > 0) {
            // N진법으로 나누었는데 10보다 작으면 바로 추가
            if (current % K < 10) {
                sb.append(current % K);
            }
            // 10이상은 알파벳으로 표기한다.
            else {
                // 10진수를 구해주는 법과 반대로 수행해주면 된다. 10이상의 수부터 표현할 수 있기 때문에 10을 빼주는 것이다.
                sb.append((char) (current % K - 10 + 'A'));
            }
            current /= K;
        }
        return sb.reverse().toString();
    }

    /** 올바른 진법과 입력이 주어졌는지 체크 */
    private static NotationCode isNotationError(int N, String number) {

        char[] num = number.toCharArray();
        for (int i = 0; i < num.length; i++) {
            if (!isError(num[i], N)) {
                return NotationCode.INPUT_ERROR;
            }
        }
        if (N >= 2 && N <= 16) {
            return NotationCode.SUCCESS;
        } else {
            return NotationCode.UNSUPPORTED_NOTATION;
        }
    }

    /** 주어진 진법보다 높은 숫자인지 체크 */
    private static boolean isError(char num, int notation) {

        int n = 0;
        if (num >= 'A') {
            n = num - 'A' + 10;
        } else {
            n = num - '0';
        }

        if (n >= notation) {
            return false;
        }
        return true;
    }
}
