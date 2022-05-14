use master
go

create database PRODUCT
go

use PRODUCT
go

create table User_App (
	user_id nvarchar(30) primary key,
	username nvarchar(30),
	password nvarchar(60),
	full_name nvarchar(100),
	phone_num nvarchar(20),
	mail nvarchar(40),
	role int
)

create table Product (
	product_id nvarchar(30) primary key,
	product_name nvarchar(100),
	product_quantity int,
	product_detail nvarchar(255)
);

create table Tag (
	tag_id nvarchar(30) primary key,
	product_id nvarchar(30) not null,
	tag_gate_in nvarchar(30),
	tag_date_in datetime,
	tag_gate_out nvarchar(30),
	tag_date_out datetime,
	order_id nvarchar(30)
);

create table Order_Product (
	order_id nvarchar(30) primary key,
	order_date datetime,
	created_by nvarchar(30),
	completed_by nvarchar(30),
	status int
);

create table Order_Detail (
	order_detail_id nvarchar(30) primary key,
	order_id nvarchar(30) not null,
	product_id nvarchar(30) not null,
	order_quantity int
);

create table Storage (
	storage_id nvarchar(30) primary key,
);

create table Slot (
	slot_id nvarchar(30) primary key,
	storage_id nvarchar(30) not null,
	product_id nvarchar(30) not null, 
	status int
);

create table LastId (
	user_id nvarchar(30),
	product_id nvarchar(30),
	order_id nvarchar(30),
	order_detail_id nvarchar(30),
	storage_id nvarchar(30),
	slot_id nvarchar(30)
)


alter table Tag add constraint FK_Tag_Product
foreign key (product_id) references Product(product_id);

alter table Order_Detail add constraint FK_OrderDetail_Order
foreign key (order_id) references Order_Product(order_id);
alter table Order_Detail add constraint FK_OrderDetail_Product
foreign key (product_id) references Product(product_id);

alter table Slot add constraint FK_Slot_Storage
foreign key (storage_id) references Storage(storage_id);
alter table Slot add constraint FK_Slot_Product
foreign key (product_id) references Product(product_id);

insert into Product
values ('PRO_1','product 1',0,'detail product 1'),
('PRO_2','product 2',0,'detail product 2'),
('PRO_3','product 3',0,'detail product 3'),
('PRO_4','product 4',0,'detail product 4'),
('PRO_5','product 5',0,'detail product 5'),
('PRO_6','product 6',0,'detail product 6'),
('PRO_7','product 7',0,'detail product 7');

insert into User_App
values ('USER_1','admin','21232f297a57a5a743894a0e4a801fc3','Duong Kiet','0909090909','mail@gmail.com',1)

insert into LastId
values ('1','7','0','0','0','0');

insert into User_App
values ('USER_4','user4','21232f297a57a5a743894a0e4a801fc3',N'Lê " '' . Dưỡng','0909090909','mail@gmail.com',2)

insert into Tag
values ('TAG_1','PRO_2','',null,'',null);

drop table Slot
go
drop table Tag
go
drop table Order_Detail
go
drop table Order_Product
go
drop table Storage
go
drop table Product
go
drop table User_App
go
drop table LastId
go

select op.order_id,op.order_date,op.status,op.product_id,op.product_name,op.order_quantity,Tag.tag_id,Tag.tag_gate_in,Tag.tag_date_in,Tag.tag_gate_out,Tag.tag_date_out
from (select temp.order_id,temp.product_id,Product.product_name,temp.order_date,temp.order_quantity,temp.status from 
					(select Order_Detail.order_id,Order_Detail.product_id,Order_Detail.order_quantity,Order_Product.order_date,Order_Product.status
					from Order_Detail left join Order_Product 
					on Order_Detail.order_id = Order_Product.order_id) temp left join Product 
	on temp.product_id = Product.product_id) op left join Tag on Tag.order_id = op.order_id and Tag.product_id = op.product_id 
where op.order_id = 'ORDER_1';

select op.order_id,op.order_date,op.status,op.product_id,op.product_name,op.order_quantity,Tag.tag_id,Tag.tag_gate_in,Tag.tag_date_in,Tag.tag_gate_out,Tag.tag_date_out,(select User_App.full_name from User_App where op.created_by = User_App.user_id) cr_usename,(select User_App.full_name from User_App where op.completed_by = User_App.user_id) cpl_usename
from (select temp.order_id,temp.product_id,Product.product_name,temp.order_date,temp.order_quantity,temp.created_by,temp.completed_by,temp.status from 
					(select Order_Detail.order_id,Order_Detail.product_id,Order_Detail.order_quantity,Order_Product.created_by,Order_Product.completed_by,Order_Product.order_date,Order_Product.status
					from Order_Detail left join Order_Product 
					on Order_Detail.order_id = Order_Product.order_id) temp left join Product 
	on temp.product_id = Product.product_id) op left join Tag on Tag.order_id = op.order_id and Tag.product_id = op.product_id 
where op.order_id = 'ORDER_8';