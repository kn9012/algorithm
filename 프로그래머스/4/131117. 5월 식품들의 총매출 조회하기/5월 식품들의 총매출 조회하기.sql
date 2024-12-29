SELECT F.PRODUCT_ID, F.PRODUCT_NAME, SUM(O.AMOUNT * F.PRICE) TOTAL_SALES
FROM FOOD_PRODUCT F JOIN FOOD_ORDER O
ON F.PRODUCT_ID = O.PRODUCT_ID
WHERE O.PRODUCE_DATE BETWEEN '2022-05-01' AND '2022-05-31'
GROUP BY F.PRODUCT_ID
ORDER BY 3 DESC, 1;