package psjava.util;

public class MathUtil {

    /** precision 자리수에서 반올림하는 함수 */
    public static double round(double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }

    public static boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }

        for (int div = 2; div * div < n; div++) {
            if (n % div == 0) {
                return false;
            }
        }
        return true;
    }

    /** 재귀를 이용한 팩토리얼 n! */
    public static int factorialRecur(int n) {
        if (n == 1) {
            return 1;
        }

        return n * factorialRecur(n - 1);
    }

    /** 반복문을 이용한 팩토리얼 n! */
    public static int factorialIter(int n) {
        for (int i = n - 1; i >= 1; i--) {
            n *= i;
        }
        return n;
    }
}
