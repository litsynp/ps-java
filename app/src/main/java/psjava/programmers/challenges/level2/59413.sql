-- 2022.05.13 - 프로그래머스 - #59413 입양 시각 구하기(2)
-- REF: https://seosh817.tistory.com/51
SET
  @hour = -1;

SELECT
  (@hour := @hour + 1) AS HOUR,
  (
    SELECT
      COUNT(*)
    FROM
      ANIMAL_OUTS
    WHERE
      HOUR(DATETIME) = @hour
  ) AS COUNT
FROM
  ANIMAL_OUTS AS A
GROUP BY
  HOUR
HAVING
  HOUR BETWEEN 0
  AND 23
ORDER BY
  HOUR;