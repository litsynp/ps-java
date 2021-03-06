-- 2022.05.13 - 프로그래머스 - #59041 동명 동물 수 찾기
SELECT
  NAME,
  COUNT(*) AS COUNT
FROM
  ANIMAL_INS
WHERE
  NAME != ''
GROUP BY
  NAME
HAVING
  COUNT(NAME) > 1
ORDER BY
  NAME;