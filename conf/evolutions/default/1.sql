# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table address (
  id                        integer not null,
  city                      varchar(255),
  street                    varchar(255),
  project_id                integer,
  constraint pk_address primary key (id))
;

create table donation (
  id                        integer not null,
  description               varchar(255),
  project_id                integer,
  donation_type_id          integer,
  user_id                   integer,
  constraint pk_donation primary key (id))
;

create table donation_goal (
  id                        integer not null,
  amount                    integer,
  project_id                integer,
  donation_type_id          integer,
  constraint pk_donation_goal primary key (id))
;

create table donation_type (
  id                        integer not null,
  name                      varchar(255),
  constraint pk_donation_type primary key (id))
;

create table news (
  id                        integer not null,
  title                     varchar(255),
  description               varchar(255),
  picture                   bytea,
  project_id                integer,
  constraint pk_news primary key (id))
;

create table project (
  id                        integer not null,
  title                     varchar(255),
  description               varchar(255),
  starts_at                 date,
  ends_at                   date,
  contact                   varchar(255),
  address_id                integer,
  user_id                   integer,
  constraint pk_project primary key (id))
;

create table user (
  id                        integer not null,
  first_name                varchar(255),
  last_name                 varchar(255),
  email                     varchar(255),
  password                  varchar(255),
  constraint pk_user primary key (id))
;

create sequence address_seq;

create sequence donation_seq;

create sequence donation_goal_seq;

create sequence donation_type_seq;

create sequence news_seq;

create sequence project_seq;

create sequence user_seq;

alter table address add constraint fk_address_project_1 foreign key (project_id) references project (id);
create index ix_address_project_1 on address (project_id);
alter table donation add constraint fk_donation_project_2 foreign key (project_id) references project (id);
create index ix_donation_project_2 on donation (project_id);
alter table donation add constraint fk_donation_donationType_3 foreign key (donation_type_id) references donation_type (id);
create index ix_donation_donationType_3 on donation (donation_type_id);
alter table donation add constraint fk_donation_user_4 foreign key (user_id) references user (id);
create index ix_donation_user_4 on donation (user_id);
alter table donation_goal add constraint fk_donation_goal_project_5 foreign key (project_id) references project (id);
create index ix_donation_goal_project_5 on donation_goal (project_id);
alter table donation_goal add constraint fk_donation_goal_donationType_6 foreign key (donation_type_id) references donation_type (id);
create index ix_donation_goal_donationType_6 on donation_goal (donation_type_id);
alter table news add constraint fk_news_project_7 foreign key (project_id) references project (id);
create index ix_news_project_7 on news (project_id);
alter table project add constraint fk_project_address_8 foreign key (address_id) references address (id);
create index ix_project_address_8 on project (address_id);
alter table project add constraint fk_project_user_9 foreign key (user_id) references user (id);
create index ix_project_user_9 on project (user_id);



# --- !Downs

drop table if exists address cascade;

drop table if exists donation cascade;

drop table if exists donation_goal cascade;

drop table if exists donation_type cascade;

drop table if exists news cascade;

drop table if exists project cascade;

drop table if exists user cascade;

drop sequence if exists address_seq;

drop sequence if exists donation_seq;

drop sequence if exists donation_goal_seq;

drop sequence if exists donation_type_seq;

drop sequence if exists news_seq;

drop sequence if exists project_seq;

drop sequence if exists user_seq;

