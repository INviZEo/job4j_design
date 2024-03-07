select avg(price) from devices;

select p.name, avg(dv.price)
from people as p
join devices_people dp on p.id = dp.people_id
join devices dv on dp.device_id = dv.id
group by p.name;

select p.name, avg(dv.price)
from people as p
join devices_people dp on p.id = dp.people_id
join devices dv on dp.device_id = dv.id
group by p.name
having avg(dv.price) > 5000;
