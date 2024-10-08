SELECT A.CATEGORY, A.PRICE, A.PRODUCT_NAME
FROM FOOD_PRODUCT A INNER JOIN
(SELECT CATEGORY, MAX(PRICE) MAX_PRICE FROM FOOD_PRODUCT GROUP BY CATEGORY) B
ON A.CATEGORY = B.CATEGORY AND A.PRICE = B.MAX_PRICE
WHERE A.CATEGORY IN ('식용유', '과자', '국', '김치')
ORDER BY 2 DESC;