package psjava.programmers.challenges.level2;

import java.util.*;

/**
 * 2022.06.05 - 프로그래머스 - #17680 [1차] 캐시
 * <p>
 * LEVEL 2
 * <p>
 * 2018 KAKAO BLIND RECRUITMENT
 * <p>
 * 자료구조
 *
 * @see https://programmers.co.kr/learn/courses/30/lessons/17680
 */
public class Q17680 {

    public int solution(int cacheSize, String[] cities) {
        int answer = 0;

        // 캐시 사이즈 0이면 저장이 안됨
        if (cacheSize == 0) {
            return cities.length * 5;
        }

        LRUCache cache = new LRUCache(cacheSize);
        for (String city : cities) {
            answer += cache.execute(city);
        }

        return answer;
    }

    // LRU: Least Recently Used
    class LRUCache {

        Map<String, Integer> cache;
        final int cacheSize;

        public LRUCache(int _cacheSize) {
            cacheSize = _cacheSize;
            // true를 줌으로써 "insertion-ordered"가 아닌 "access-ordered" map으로 바꾼다.
            cache = new LinkedHashMap<>(cacheSize, 0.75f, true) {
                @Override
                protected boolean removeEldestEntry(java.util.Map.Entry<String, Integer> eldest) {
                    // capacity보다 크면 가장 오래된 데이터를 자동으로 삭제
                    return size() > cacheSize;
                }
            };
        }

        public int execute(String in) {
            in = in.toLowerCase();

            if (cache.containsKey(in)) {
                // cache hit
                cache.get(in);
                return 1;
            } else {
                cache.put(in, 0);
                return 5;
            }
        }
    }
}
