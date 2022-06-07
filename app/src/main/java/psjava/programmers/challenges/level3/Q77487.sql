-- 2022.05.15 - 프로그래머스 - #77487 헤비 유저가 소유한 장소
-- LEVEL 3
-- 2021 Dev-Matching: 웹 백엔드 개발자(상반기)
-- Link: https://programmers.co.kr/learn/courses/30/lessons/77487
SELECT
  P1.*
FROM
  PLACES AS P1
  INNER JOIN PLACES AS P2 ON P1.ID != P2.ID
  AND P1.HOST_ID = P2.HOST_ID
GROUP BY
  P1.ID
ORDER BY
  P1.ID;
