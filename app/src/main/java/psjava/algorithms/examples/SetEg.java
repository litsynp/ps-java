package psjava.algorithms.examples;

import java.util.*;

/**
 * Set<E>
 *
 * - 입력된 데이터들을 중복없이 저장하고 싶을 때 사용하면 좋다.
 *
 * - 중복을 허용하지 않는다.
 *
 * - TreeSet은 정렬되어서 저장되지만, HashSet은 정렬을 보장하지 않는다.
 */
public class SetEg {

  public static void main(String[] args) {

    // TreeSet 생성
    TreeSet<String> ts = new TreeSet<>();

    // TreeSet에 요소 추가 -- TreeSet은 순서 유지
    ts.add("apple");
    ts.add("banana");
    ts.add("strawberry");

    // 정렬된 순서로 출력
    System.out.println("Sorted TreeSet:");
    for (String s : ts) {
      System.out.println(s);
    }

    // TreeSet을 HashSet으로 변경
    HashSet<String> hs = new HashSet<>(ts);

    // 정렬하지 않은 채 출력
    System.out.println("Unsorted HashSet:");
    for (String s : hs) {
      System.out.println(s);
    }

    // 요소 삭제
    Iterator<String> it = hs.iterator();
    while (it.hasNext()) {
      // 요소 조회
      String e = it.next();

      // 조건에 맞는 요소 삭제
      if (e.length() % 2 == 0) {
        it.remove();
      }
    }

    // 삭제 후 출력
    System.out.println("Set after some elements removed:");
    for (String s : hs) {
      System.out.println(s);
    }
  }
}
