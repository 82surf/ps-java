select a.food_type, b.rest_id, b.rest_name, b.favorites
from (
         select food_type, max(favorites) max_favorite
         from rest_info
         group by food_type
     ) a
         left join rest_info b
                   on a.food_type = b.food_type and a.max_favorite = b.favorites
order by food_type desc;