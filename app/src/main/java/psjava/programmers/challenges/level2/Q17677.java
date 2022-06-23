package psjava.programmers.challenges.level2;

import java.util.*;

/**
 * 2022.06.24 - 프로그래머스 - #17677 [1차] 뉴스 클러스터링
 * <p>
 * LEVEL 2
 * <p>
 * 2018 KAKAO BLIND RECRUITMENT
 * <p>
 * 구현
 *
 * @see <a href="https://programmers.co.kr/learn/courses/30/lessons/17677">https://programmers.co.kr/learn/courses/30/lessons/17677</a>
 */
class Q17677 {
    public int solution(String str1, String str2) {
        return (int) (getJ(getMap(str1), getMap(str2)) * 65536);
    }

    private Map<String, Integer> getMap(String s) {
        s = s.toLowerCase();

        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length() - 1; i++) {
            char first = s.charAt(i);
            char second = s.charAt(i + 1);
            if (('a' <= first && first <= 'z') && ('a' <= second && second <= 'z')) {
                String item = "" + first + second;
                map.put(item, map.getOrDefault(item, 0) + 1);
            }
        }
        return map;
    }

    /**
     * Gets Jaccard Similarity of String a and b
     */
    private double getJ(Map<String, Integer> a, Map<String, Integer> b) {
        if (a.isEmpty() && b.isEmpty()) return 1;

        // if |A n B| == 0 || |A u B| == 0 => 0
        Set<String> intersectionKeys = new HashSet<>(a.keySet());
        intersectionKeys.retainAll(b.keySet());

        Map<String, Integer> intersection = new HashMap<>();
        intersectionKeys.forEach(key -> intersection.put(key, 1));
        intersectionKeys.forEach(key ->
                intersection.put(key,
                        Math.min(a.getOrDefault(key, 1),
                                b.getOrDefault(key, 1))));

        Map<String, Integer> union = new HashMap<>(a);
        b.keySet().forEach(key ->
                union.put(key, union.containsKey(key)
                        ? Math.max(union.get(key), b.get(key))
                        : b.get(key)));

        int intersectionSize = intersection.values().stream().mapToInt(Integer::intValue).sum();
        int unionSize = union.values().stream().mapToInt(Integer::intValue).sum();

        // else |A n B| / |A u B|
        return (double) intersectionSize / unionSize;
    }
}
