-- drop database if already exists
use master;
if exists (select *
from sys.databases
where name='miniproject_persistence')
  drop database miniproject_persistence;
go

create database miniproject_persistence;
go

use miniproject_persistence;

-- create tables
create table addresses
(
  id int IDENTITY(1, 1),
  address text not null,
  city varchar(30) not null,
  zip int not null,
  country varchar(30) not null,

  primary key (id)
);

create table customers
(
  id int IDENTITY(1, 1),
  name text not null,
  phoneno varchar(10) not null,
  address_id int not null,

  primary key (id),
  constraint fkaddress foreign key (address_id) references addresses(id)
);
