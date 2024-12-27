SELECT CAR_ID, 
    CASE
    WHEN AVAILABILITY != 0 THEN '대여중'
    ELSE '대여 가능'
    END
FROM (SELECT CAR_ID, SUM(CASE WHEN START_DATE <= '2022-10-16' AND END_DATE >= '2022-10-16' THEN 1 ELSE 0 END) AVAILABILITY
      FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY GROUP BY CAR_ID) A
ORDER BY 1 DESC;