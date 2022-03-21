package psjava.algorithm.sort;

/**
 * 퀵 정렬 (Quick Sort)
 *
 * 퀵 정렬 (왼쪽 피벗 선택 방식) 이다.
 *
 * https://st-lab.tistory.com/250
 */
public class QuickSort {

  public static void quickSort(int[] arr) {
    sort(arr, 0, arr.length - 1);
  }

  /**
   * 퀵소트 (왼쪽 피벗 선택 방식)
   *
   * @param a 정렬할 배열
   * @param lo 현재 부분 배열의 왼쪽
   * @param hi 현재 부분 배열의 오른쪽
   */
  public static void sort(int[] a, int lo, int hi) {

    // lo >= hi면 정렬할 원소가 1개 이하이므로, 정렬하지 않고 return
    if (lo >= hi) {
      return;
    }

    // pivot을 기준으로 요소들이 왼쪽과 오른쪽으로 약하게 정렬된 상태로 만들어 준 뒤,
    // 최종적으로 pivot의 위치를 얻는다.
    int pivot = partition(a, lo, hi);

    // 해당 pivot의 기준으로 왼쪽 부분 배열과 오른쪽 부분 배열로 나누어 분할 정복을 실시한다.
    sort(a, lo, pivot - 1);
    sort(a, pivot + 1, hi);
  }

  /**
   * pivot을 기준으로 파티션을 나누기 위한 약한 정렬 메소드.
   *
   * @param a 정렬할 배열
   * @param left 현재 배열의 가장 왼쪽 부분
   * @param right 현재 배열의 가장 오른쪽 부분
   * @return 최종적으로 위치한 피벗의 위치 (lo)
   */
  public static int partition(int[] a, int left, int right) {

    int lo = left;
    int hi = right;
    int pivot = left;

    // lo보다 hi가 작은 동안만 반복
    while (lo < hi) {
      // hi가 lo보다 크면서,
      // hi의 요소가 pivot보다 작거나 같은 원소를 찾을 때까지 hi를 감소시킨다.
      while (a[hi] > a[pivot] && lo < hi) {
        hi--;
      }

      // hi가 lo보다 크면서,
      // lo의 요소가 pivot보다 큰 원소를 찾을 때까지 hi를 감소시킨다.
      while (a[lo] <= a[pivot] && lo < hi) {
        lo++;
      }

      // 교환될 두 요소를 찾았으면 두 요소를 바꾼다.
      swap(a, lo, hi);
    }

    // 마지막으로, 맨 처음 pivot으로 설정했던 위치인 left를 이용해
    // a[left]와 a[lo]를 바꾼다.
    swap(a, pivot, lo);

    // 두 요소가 교환되었다면, 피벗이었던 요소는 lo에 위치하므로 lo를 반환한다.
    return lo;
  }

  /**
   * a[i]와 a[j]를 바꾼다.
   *
   * @param a 배열
   * @param i 인덱스 i
   * @param j 인덱스 j
   */
  public static void swap(int[] a, int i, int j) {
    int temp = a[i];
    a[i] = a[j];
    a[j] = temp;
  }
}
