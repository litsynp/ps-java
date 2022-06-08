package psjava.algorithm.search;

/**
 * 이진 탐색
 * <p>
 * 탐색을 반복할 때마다 탐색 범위를 반으로 줄인다. 따라서 O(log n) 시간 복잡도로 찾는다.
 *
 * @see psjava.programmers.challenges.level2.Q72412
 * @see <a href="https://minhamina.tistory.com/127">https://minhamina.tistory.com/127</a>
 */
public class BinarySearch {

    int[] arr;

    /**
     * 이진 탐색 - 재귀 구현 O(log n)
     * <p>
     * 정렬이 되어 있어야 한다.
     * <p>
     * 시작은 binarySearchIter(key, 0, arr.length - 1)
     */
    int binarySearchRecur(int key, int low, int high) {
        int mid;

        if (low <= high) {
            mid = (low + high) / 2;

            if (key == arr[mid]) { // 탐색 성공
                return mid;
            } else if (key < arr[mid]) {
                // 왼쪽 부분 arr[0]부터 arr[mid-1]에서의 탐색
                return binarySearchRecur(key, low, mid - 1);
            } else {
                // 오른쪽 부분 - arr[mid+1]부터 arr[high]에서의 탐색
                return binarySearchRecur(key, mid + 1, high);
            }
        }

        return -1; // 탐색 실패
    }

    /**
     * 이진 탐색 - 반복문 구현 O(log n)
     * <p>
     * 정렬이 되어 있어야 한다.
     */
    int binarySearchIter(int[] arr, int target) {
        int start = 0;
        int end = arr.length - 1;
        int mid;

        while (start <= end) {
            mid = (start + end) / 2;

            if (target == arr[mid]) {
                return mid;
            } else if (target < arr[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return -1; // 탐색 실패
    }
}
