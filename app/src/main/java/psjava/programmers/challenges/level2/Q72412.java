package psjava.programmers.challenges.level2;

import java.util.*;

/**
 * 2022.06.07 - 프로그래머스 - #72411 순위 검색
 * <p>
 * LEVEL 2
 * <p>
 * 2021 KAKAO BLIND RECRUITMENT
 * <p>
 * 알고리즘, 이분 탐색
 *
 * @see <a href="https://loosie.tistory.com/265">https://loosie.tistory.com/265</a>
 * @see <a href="https://programmers.co.kr/learn/courses/30/lessons/72412">https://programmers.co.kr/learn/courses/30/lessons/72412</a>
 */
public class Q72412 {

    // info : [score1, score2, ...]
    Map<String, List<Integer>> allInfo = new HashMap<>();
    int[] answer;

    public int[] solution(String[] info, String[] query) {
        answer = new int[query.length];

        // 지원자 정보 초기화 -- 모든 경우의 수를 map에 저장
        for (String is : info) {
            dfs("", 0, is.split(" "));
        }

        // 이분 탐색을 위해 점수 List에 대해 데이터 정렬
        for (Iterator<String> iter = allInfo.keySet().iterator(); iter.hasNext(); ) {
            String key = iter.next();
            List<Integer> li = allInfo.get(key);  // 얕은 복사
            Collections.sort(li);
        }

        for (int i = 0; i < query.length; i++) {
            // e.g., [javabackendjuniorpizza 100]
            // e.g., [cpp-seniorpizza 250]
            String[] strArr = query[i]
                    .replace(" and ", "")
                    .split(" ");

            String key = strArr[0];
            int score = Integer.parseInt(strArr[1]);
            answer[i] = binarySearchCount(key, score);
        }

        return answer;
    }

    int binarySearchCount(String str, int score) {
        // e.g., javabackendjuniorpizza
        // e.g., cpp-seniorpizza
        if (!allInfo.containsKey(str)) {
            return 0;
        }

        List<Integer> scoreList = allInfo.get(str);
        int start = 0;
        int end = scoreList.size() - 1;

        // 정렬된 데이터에 대해서 이분 탐색
        while (start <= end) {
            int mid = (start + end) / 2;

            if (scoreList.get(mid) < score) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return scoreList.size() - start;
    }

    void dfs(String pos, int depth, String[] info) {
        if (depth == 4) {
            if (!allInfo.containsKey(pos)) {
                List<Integer> list = new ArrayList<>();
                list.add(Integer.parseInt(info[4]));
                allInfo.put(pos, list);
            } else {
                allInfo.get(pos).add(Integer.parseInt(info[4]));
            }
            return;
        }

        // query 조건이 상관 없을 때의 경우도 포함
        dfs(pos + "-", depth + 1, info);
        dfs(pos + info[depth], depth + 1, info);
    }
}
