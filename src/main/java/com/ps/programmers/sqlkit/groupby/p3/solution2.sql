-- 1개의 서브쿼리로 단순화
select a.author_id, a.author_name, bs.category, sum(bs.total_price) total_sales
from (
         select b.author_id, b.category, b.price * bs.sales total_price
         from book_sales bs
                  left join book b
                            on bs.book_id = b.book_id
         where to_char(bs.sales_date, 'yyyy-mm') = '2022-01'
     ) bs
         left join author a
                   on bs.author_id = a.author_id
group by a.author_id, a.author_name, bs.category
order by author_id asc, category desc;