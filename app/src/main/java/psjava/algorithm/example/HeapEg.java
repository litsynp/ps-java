package psjava.algorithm.example;

import java.util.*;

/**
 * Heap<>
 *
 * - 데이터의 추가 및 삭제 후에도 항상 정렬 상태를 유지한다.
 *
 * - `PriorityQueue`의 생성자로 (Min/Max) Heap Tree 등 다양한 형태를 구현할 수 있다.
 *
 * - 중복을 허용한다.
 */
public class HeapEg {

  public static void main(String[] args) {

    // (x, y) 튜플이 들어가는 우선순위큐 생성
    PriorityQueue<Item> pq = new PriorityQueue<>(new Comparator<Item>() {
      public int compare(Item i1, Item i2) {
        // 1번 아이템을 먼저 비교하고 2번 아이템을 비교한다.
        return i1.v1 == i2.v1 ? Integer.compare(i1.v2, i2.v2) : Integer.compare(i1.v1, i2.v1);
      }
    });

    pq.add(new Item(4, 3));
    pq.add(new Item(3, 6));
    pq.add(new Item(4, 8));
    pq.add(new Item(1, 1));

    if (pq.size() != 0) {
      // Poll & peek (조회하면서 삭제)
      Item item = pq.poll();
      System.out.println("(" + item.v1 + ", " + item.v2 + ") 삭제");
    }

    pq.add(new Item(7, 3));
    pq.add(new Item(2, 6));

    // 단순 출력 (정렬되어 있으나 단순 출력으로는 정렬 되어있지 않음!)
    System.out.println("Print elements:");
    for (Item item : pq) {
      System.out.println(item.v1 + " : " + item.v2);
    }

    // 정렬된 출력
    System.out.println("Print sorted elements:");
    while (pq.size() != 0) {
      Item item = pq.poll();
      System.out.println(item.v1 + " : " + item.v2);
    }
  }

  public static class Item {
    int v1, v2;

    public Item(int v1, int v2) {
      this.v1 = v1;
      this.v2 = v2;
    }
  }
}
