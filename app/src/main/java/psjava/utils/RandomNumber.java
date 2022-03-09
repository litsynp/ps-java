package psjava.utils;

public class RandomNumber {

  // min <= x <= max인 임의의 정수를 불러온다.
  public static int getRandomNumber(int min, int max) {
    return (int) ((Math.random() * (max - min)) + min);
  }
}
