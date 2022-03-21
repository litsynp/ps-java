package psjava.programmers;

import java.util.*;

/**
 * 2022.03.21 - 프로그래머스 - #92334 신고 결과 받기
 */
public class Q92334 {

    public int[] solution(String[] id_list, String[] report, int k) {
        // 한 유저가 동일한 유저를 1번만 신고할 수 있으므로 stream().distinct()를 이용해서 중복을 제거.
        report = Arrays.stream(report).distinct().toArray(String[]::new);

        // result.get(신고자) == 메일 횟수
        // LinkedHashMap으로 순서 유지
        Map<String, Integer> result = new LinkedHashMap<>();

        // reportMap.get(신고당한 사람) == { 신고자1, 신고자2, ... }
        Map<String, List<String>> reportMap = new HashMap<>();

        // 각각의 메일 횟수는 0으로 초기화
        for (int i = 0; i < id_list.length; i++) {
            result.put(id_list[i], 0);
        }

        for (int i = 0; i < id_list.length; i++) {
            reportMap.put(id_list[i], new ArrayList<>());
        }

        for (int i = 0; i < report.length; i++) {
            String[] arrReport = report[i].split(" ");
            String reporter = arrReport[0];
            String target = arrReport[1];

            // 신고받은 사람에 신고자 목록 추가
            reportMap.get(target).add(reporter);
        }

        // 각 신고받은 사람마다
        for (String key : reportMap.keySet()) {
            // 신고를 k번 이상 당했다면
            if (reportMap.get(key).size() >= k) {
                // 해당 사람을 신고한 사람의 이메일 갯수 추가
                for (String i : reportMap.get(key)) {
                    result.put(i, result.getOrDefault(i, 0) + 1);
                }
            }
        }

        return result.values().stream().mapToInt(Integer::intValue).toArray();
    }
}
