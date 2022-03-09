package psjava.algorithms.examples;

import java.io.*;
import java.util.*;

/**
 * HashMap<K, V>
 *
 * - 입력된 데이터들을 key와 Value로 저장하고 싶을 때, 탐색이 O(1)이기 때문에 특정한 값을 바로 읽어와야 할 때 사용한다.
 *
 * - 중복을 허용하지 않는다.
 *
 * - Key를 가지고 정렬을 할 수 있다. (예제 확인, Comparator)
 */
public class MapEg {
  public static void main(String[] args) throws IOException {

    // HashMap 생성
    Map<String, Integer> hm = new HashMap<>();

    // key로 key1, key2, key3, value로 각각 1, 2, 3을 넣는다.
    hm.put("key1", 1);
    hm.put("key2", 2);
    hm.put("key3", 3);

    // key 중 key1이 존재하고 value 중 1이 존재하는지 확인한다.
    if (hm.containsKey("key1") && hm.containsValue(1)) {
      System.out.println("hashmap contains key \"key1\", and contains value \"1\".");
    }

    // key1에 현재 key1의 값 * 10을 넣는다.
    hm.put("key1", hm.getOrDefault("key1", 0) * 10);

    // key로 이루어진 List을 생성한다.
    List<String> keyList = new ArrayList<>(hm.keySet());

    // 오름차순으로 key를 정렬한다.
    Collections.sort(keyList, new Comparator<String>() {
      public int compare(String s1, String s2) {
        int v1 = hm.get(s1);
        int v2 = hm.get(s2);

        return Integer.compare(v1, v2);
      }
    });

    // 출력
    System.out.println("hashmap ordered by value in ascending order:");
    for (String s : keyList) {
      System.out.println(s + " = " + hm.get(s));
    }

    // 크기를 출력한다.
    System.out.println("hashmap size: " + hm.size());
  }
}
