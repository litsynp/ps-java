-- 2022.05.13 - 프로그래머스 - #59042 없어진 기록 찾기
SELECT
  O.ANIMAL_ID,
  O.NAME
FROM
  ANIMAL_OUTS O
  LEFT OUTER JOIN ANIMAL_INS I ON I.ANIMAL_ID = O.ANIMAL_ID
WHERE
  I.DATETIME IS NULL
ORDER BY
  O.ANIMAL_ID;