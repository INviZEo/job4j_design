select pp.name, p.metal, p.position
from jewerly as pp join safe as p on pp.case_jewerly = p.id;

select pp.name as Имя, p.metal as Металл, p.position as Позиция
from jewerly as pp join safe as p on pp.case_jewerly = p.id;

select pp.name "Название Драгоценного Камня", p.metal Металл, p.position Позиция
from jewerly pp join safe p on pp.case_jewerly = p.id;