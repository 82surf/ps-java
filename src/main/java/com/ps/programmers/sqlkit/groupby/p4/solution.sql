select a.category, a.max_price, b.product_name
from (
         select category, max(price) max_price
         from food_product
         group by category
         having category in ('과자', '국', '김치', '식용유')
     ) a
         left join food_product b
                   on a.category = b.category and a.max_price = b.price
order by max_price desc;