-- 정답
-- 하지만 서브쿼리가 너무 많다

select author_id, author_name, category, sum(total_price) total_sales
from (select b.author_id, b.author_name, b.category, bs.sales * b.price total_price
      from book_sales bs
               left join (
          select b.book_id, b.category, b.price, b.published_date, a.author_id, a.author_name
          from book b
                   left join author a
                             on b.author_id = a.author_id
      ) b
                         on bs.book_id = b.book_id
      where to_char(bs.sales_date, 'yyyy-mm') = '2022-01'
     )
group by author_id, author_name, category
order by author_id asc, category desc;