package psjava.algorithms;

import psjava.utils.RandomNumber;

/**
 * 에라토스테네스의 체
 *
 * - 체로 치듯 수를 걸러내 소수를 찾는 방법
 *
 * - 완전 탐색으로 특정 범위 내의 소수를 찾는 방법
 *
 * - 특정 범위 (M, N)가 주어지고 그 범위 내의 모든 소수를 찾아야 하는 경우 이보다 빠른 방법이 없음.
 *
 * - 단, '특정 범위 내의 소수'를 판정하는 데에만 효율적
 *
 * - 방법
 *
 * 1. 범위 내의 모든 수 나열
 *
 * 2. 소수도, 합성수도 아닌 1부터 제거
 *
 * 3. 2부터 차례대로 소수인 n의 배수를 모두 제거
 *
 * 4. 주어진 범위가 k라면 √k보다 작은 소수들에 한하여 3.의 과정 반복
 */
public class SieveOfEratosthenes {

  /** 입력값의 범위 (최대값) */
  public static int N;

  /** 에라토스테네스의 체 -- false면 소수 */
  public static boolean sieve[];

  public static void main(String[] args) {
    N = 100;
    sieve = new boolean[N + 1];

    // 에라토스테네스의 체로 소수를 걸러낸다.
    sift();

    for (int i = 0; i < 10; i++) {
      int n = RandomNumber.getRandomNumber(2, 100);
      System.out.printf("%d is " + (sieve[n] ? "not " : "") + "a prime.\n", n);
    }
  }

  public static void sift() {
    // 소수는 false
    // 0, 1은 소수에서 제외
    sieve[0] = sieve[1] = true;

    for (int i = 2; i * i <= N; i++) {
      // i가 소수면 i의 배수 j는 소수가 아니다.
      if (!sieve[i]) {
        for (int j = i * i; j <= N; j += i) {
          sieve[j] = true;
        }
      }
    }
  }
}
