drop table order_lines;
drop table orders;
drop table products;
drop table suppliers;
drop table customers;
drop table addresses;
drop table zip_cities;
drop table countries;

-- create tables
create table countries
(
  id int IDENTITY(1, 1),
  name varchar(30) not null,

  primary key (id)
);

create table zip_cities
(
  city varchar(30) not null,
  zip varchar(8) not null,
  country_id int not null,

  primary key (zip),
  constraint fk_zip_city_country foreign key (country_id) references countries(id)
);


create table addresses
(
  id int IDENTITY(1, 1),
  address text not null,
  zip varchar(8) not null,

  primary key (id),
  constraint fk_address_zip foreign key (zip) references zip_cities(zip)
);

create table customers
(
  id int IDENTITY(1, 1),
  name text not null,
  phoneno varchar(12) not null,
  address_id int not null,

  primary key (id),
  constraint fk_customer_address foreign key (address_id) references addresses(id)
);

create table suppliers
(
  id int IDENTITY(1, 1),
  name varchar(30) not null,
  phoneno varchar(12) not null,
  email text not null,
  address_id int not null,

  primary key (id),
  constraint fk_supplier_address foreign key (address_id) references addresses(id)
);

create table products
(
  id int IDENTITY(1, 1),
  name varchar(30) not null,
  purchasePrice float not null,
  salesPrice float not null,
  stock int not null,
  supplier_id int not null,

  primary key (id),
  constraint fk_product_supplier foreign key (supplier_id) references suppliers(id)
);

create table orders
(
  id int IDENTITY(1, 1),
  total float not null,
  deliveryStatus varchar(30) not null,
  deliveryDate varchar(15) not null,
  customer_id int not null,

  primary key (id),
  constraint fk_orders_customer foreign key (customer_id) references customers(id)
);

create table order_lines
(
  id int IDENTITY(1, 1),
  quantity int not null,
  subtotal float not null,
  order_id int not null,
  product_id int not null,

  primary key (id),
  constraint fk_order_lines_order foreign key (order_id) references orders(id),
  constraint fk_order_lines_product foreign key (product_id) references products(id)
);
