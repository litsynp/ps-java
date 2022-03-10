package psjava.utils;

public class MathUtil {

  /** precision 자리수에서 반올림하는 함수 */
  public static double round(double value, int precision) {
    int scale = (int) Math.pow(10, precision);
    return (double) Math.round(value * scale) / scale;
  }
}
