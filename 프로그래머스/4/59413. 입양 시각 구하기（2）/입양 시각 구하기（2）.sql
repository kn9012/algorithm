WITH RECURSIVE TIME
AS (
    SELECT 0 AS HOUR
    UNION ALL
    SELECT HOUR + 1
    FROM TIME
    WHERE HOUR < 23
)

SELECT T.HOUR, COUNT(DATETIME) COUNT
FROM TIME T LEFT JOIN (SELECT *, HOUR(DATETIME) HOUR FROM ANIMAL_OUTS) O
ON O.HOUR = T.HOUR
GROUP BY T.HOUR
ORDER BY 1;