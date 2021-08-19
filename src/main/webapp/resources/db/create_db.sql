use shoes_store;
/*
admin
category
user
order
product
image
product in order
*/


create table admin (
	admin_id int auto_increment,
    ad_name nvarchar(200) not null,
    ad_password nvarchar(100) not null,
    ad_email nvarchar(100) not null,
    ad_phone_number nvarchar(20) null,
    ad_roles nvarchar(100) not null,
    ad_state tinyint not null, -- active or not. 1-true; 0-false
    ad_remember_token tinyint not null, -- remember me or not
    constraint pk_admin primary key (admin_id)
);
alter table admin
alter ad_state set default 0,
alter ad_roles set default 'c,r,u,d',
add column ad_login_name nvarchar(100) not null unique;


create table category (
	category_id int auto_increment,
    admin_id int not null,
    c_slug nvarchar(200) not null,
    c_name nvarchar(200) not null,
    created_at datetime not null,
    updated_at datetime,
    constraint pk_category primary key (category_id),
    constraint fk_category_admin foreign key (admin_id) references admin(admin_id)
);
alter table category
modify created_at timestamp not null
default current_timestamp not null,
modify updated_at timestamp
default current_timestamp,
modify c_slug nvarchar(200) not null unique;



create table user (
	user_id int auto_increment,
    admin_id int null, -- null if admin has not changed any data of user
    u_name nvarchar(200) not null,
    u_login_name nvarchar(100) not null unique,
    u_password nvarchar(100) not null,
    u_email nvarchar(100) null,
    u_phone_number nvarchar(20) not null,
    u_address nvarchar(300) not null,
    u_avatar longblob null,
    u_purchased_order_id int null,
    u_remember_token tinyint not null default 0,
    u_state tinyint not null default 0,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    constraint pk_user primary key (user_id),
    constraint fk_user_admin foreign key (admin_id) references admin(admin_id)
);

create table purchase_order (
	order_id int auto_increment,
	admin_id int not null,
    user_id int not null,
    or_total_cost decimal(15, 2) not null,
    or_shipping_cost decimal(15, 2) not null default 0,
    created_at timestamp not null default current_timestamp,
    updated_at timestamp default current_timestamp,
    constraint pk_order primary key (order_id),
    constraint fk_order_admin foreign key (admin_id) references admin(admin_id),
    constraint fk_order_user foreign key (user_id) references user(user_id)
);
alter table purchase_order
add column or_status tinyint not null default 0;



create table product_line (
	product_line_id int auto_increment,
    admin_id int not null,
    category_id int not null,
    pr_slug nvarchar(100) not null unique,
    pr_name nvarchar(200) not null,
    pr_sku nvarchar(30) not null unique,
    pr_price decimal(15, 2) not null,
    pr_colors nvarchar(200) not null, -- deleted!
    pr_sizes nvarchar(200) not null, -- deleted!
    pr_in_stock_quantity int not null,
    pr_description text not null,
    created_at timestamp not null default current_timestamp,
    updated_at timestamp default current_timestamp,
    constraint pk_product_line primary key (product_line_id),
    constraint fk_product_line_admin foreign key (admin_id) references admin(admin_id),
    constraint fk_product_line_category foreign key (category_id) references category(category_id)
);
alter table product_line
drop column pr_sizes,
drop column pr_colors;

create table image (
	image_id int auto_increment,
    product_line_id int not null,
    img_name nvarchar(200) not null,
    img_location nvarchar(200) not null, -- full name of the image file
    constraint pk_image primary key (image_id),
    constraint fk_image_product_line foreign key (product_line_id) references product_line(product_line_id) 
);
alter table image
add column admin_id int not null;
alter table image
add constraint fk_image_admin foreign key (admin_id) references admin(admin_id);

create table product_in_order (
	product_in_order_id int auto_increment,
    product_line_id int not null,
    order_id int not null,
    pio_size tinyint not null, -- deleted!
    pio_color nvarchar(100) not null, -- deleted!
    pio_discount_percent tinyint not null default 0, -- percent off
    pio_quantity smallint not null,
    constraint pk_product_in_order primary key (product_in_order_id),
    constraint fk_pio_order foreign key (order_id) references purchase_order(order_id),
    constraint fk_pio_product_line foreign key (product_line_id) references product_line(product_line_id)
);

create table size (
	size_id int not null auto_increment,
    size_number tinyint not null,
    size_detail text null,
    constraint pk_size primary key(size_id)
);

create table color (
	color_id int not null auto_increment,
    color_name nvarchar(100) not null,
    color_code nvarchar(50) not null, -- hex code of the color
    constraint pk_color primary key(color_id)
);

create table size_product_line (
	size_product_line_id int not null auto_increment,
    product_line_id int not null,
    size_id int not null,
    spl_quantity smallint not null default 0,
    check (spl_quantity >=0),
    constraint pk_spl primary key (size_product_line_id),
    constraint fk_spl_product_line foreign key (product_line_id) references product_line(product_line_id),
    constraint fk_spl_size foreign key (size_id) references size(size_id)
);

create table color_product_line (
	color_product_line_id int not null auto_increment,
    product_line_id int not null,
    color_id int not null,
    cpl_quantity smallint not null default 0,
    check (cpl_quantity>=0),
    constraint pk_cpl primary key (color_product_line_id),
    constraint fk_cpl_product_line foreign key (product_line_id) references product_line(product_line_id),
    constraint fk_cpl_color foreign key (color_id) references color(color_id)
);

create table cta (
	cta_id int auto_increment,
    cta_slug nvarchar(200) not null,
    cta_banner_location nvarchar(200) not null,
    cta_title text not null,
    cta_button_text nvarchar(100) not null,
    cta_description text null,
    constraint pk_cta primary key (cta_id)
);

create table color (
	color_id int not null auto_increment primary key,
    color_name nvarchar(100) not null default 'default',
    color_code nvarchar(50) null
);


create table size (
	size_id int not null auto_increment primary key,
    size_number tinyint not null,
    size_description text null
);

create table specific_product (
	specific_product_id int not null auto_increment primary key,
	product_line_id int not null,
    color_id int not null,
    size_id int not null,
    foreign key (product_line_id) references product_line(product_line_id),
    foreign key (color_id) references color(color_id),
    foreign key (size_id) references size(size_id)
);

create table product_in_order (
	product_in_order_id int not null auto_increment primary key,
    specific_product_id int not null,
    order_id int not null ,
    foreign key (specific_product_id) references specific_product(specific_product_id),
    foreign key (order_id) references purchase_order(order_id)
);

ALTER TABLE `specific_product` ADD UNIQUE (`product_line_id`, `color_id`, `size_id`);

-- tạo thêm bảng brand
create table brand (
	brand_id int primary key auto_increment,
    brand_name nvarchar(100) not null,
    brand_nation_code nvarchar(4) null, -- vn, us, uk,..
    brand_description text null
);

select brand_id, brand_name from brand;

select * from admin;
select * from category;
select * from cta;
select * from color;
select * from size;
select * from product_line; -- co the xoa pr_price vi khong con can thiet
select * from specific_product;

select * from purchase_order;
select * from product_in_order;
select * from user;


select *
from product_line p left join image i on p.product_line_id=i.product_line_id 
	join category c on c.category_id=p.category_id
	right join specific_product s on s.product_line_id=p.product_line_id
where c.c_slug='/women' and
	pr_brand_id like '%' and (pr_price between 0 and 999)
group by p.product_line_id
order by p.created_at DESC limit 100 offset 0;

select count(distinct(p.product_line_id))
from product_line p join category c on p.category_id=c.category_id
	right join specific_product s on s.product_line_id=p.product_line_id
where c.c_slug='/men' and 
	pr_brand_id like '%' and (pr_price between 0 and 999);

select count(distinct(p.product_line_id)) 
from product_line p join image i on p.product_line_id=i.product_line_id
	join category c on c.category_id=p.category_id 
where p.pr_name like '%';

select count(*) from
(select count(*)
	from product_line p join image i on p.product_line_id=i.product_line_id
	join category c on c.category_id=p.category_id
	join specific_product s on s.product_line_id=p.product_line_id
where p.pr_name like '%%'
group by p.product_line_id
having count(s.specific_product_id) > 0) as rs;


select *
	from product_line p join image i on p.product_line_id=i.product_line_id
	join category c on c.category_id=p.category_id
	join specific_product s on s.product_line_id=p.product_line_id
where p.pr_name like '%n%'
group by p.product_line_id
having count(s.specific_product_id) > 0;

-- --------------trigger for inserting-----------------
-- we don't need trigger now
-- create crud for data entry
-- ...

-- sau này có dữ liệu, cần trigger mỗi khi update, xóa, insert,.. 
