create table cars (
	brand varchar(100) primary key,
	model varchar(100),
	dateOfSales date,
	country varchar(50),
	power numeric
) 

insert into cars values('Dodge', 'RAM', to_date('27-09-2019', 'DD-MM-yyyy'), 'USA', 395);
insert into cars values('LADA', 'XRay', to_date('18-04-2018', 'DD-MM-yyyy'), 'Russia', 122);
insert into cars values('Nissan', 'Pathfinder', to_date('01-06-2010', 'DD-MM-yyyy'), 'Japan', 170);
insert into cars values('Cadillac', 'Escalade', to_date('21-03-2005', 'DD-MM-yyyy'), 'USA', 362);
