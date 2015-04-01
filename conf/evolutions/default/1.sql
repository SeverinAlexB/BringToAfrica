# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table address (
  id                        bigint not null,
  country                   varchar(255),
  city                      varchar(255),
  street                    varchar(255),
  constraint pk_address primary key (id))
;

create table consumer (
  id                        bigint not null,
  first_name                varchar(255),
  last_name                 varchar(255),
  email                     varchar(255),
  password                  varchar(255),
  constraint pk_consumer primary key (id))
;

create table donation (
  id                        bigint not null,
  description               varchar(255),
  project_id                bigint,
  donation_type_id          bigint,
  consumer_id               bigint,
  constraint pk_donation primary key (id))
;

create table donation_goal (
  id                        bigint not null,
  amount                    integer,
  project_id                bigint,
  donation_type_id          bigint,
  constraint pk_donation_goal primary key (id))
;

create table donation_type (
  id                        bigint not null,
  name                      varchar(255),
  constraint pk_donation_type primary key (id))
;

create table news (
  id                        bigint not null,
  project_id                bigint not null,
  title                     varchar(255),
  description               varchar(255),
  date                      date,
  picture                   bytea,
  constraint pk_news primary key (id))
;

create table project (
  id                        bigint not null,
  title                     varchar(255),
  description               varchar(255),
  starts_at                 date,
  ends_at                   date,
  contact                   varchar(255),
  consumer_id               bigint,
  address_id                bigint,
  constraint pk_project primary key (id))
;

create sequence address_seq;

create sequence consumer_seq;

create sequence donation_seq;

create sequence donation_goal_seq;

create sequence donation_type_seq;

create sequence news_seq;

create sequence project_seq;

alter table donation add constraint fk_donation_project_1 foreign key (project_id) references project (id);
create index ix_donation_project_1 on donation (project_id);
alter table donation add constraint fk_donation_donationType_2 foreign key (donation_type_id) references donation_type (id);
create index ix_donation_donationType_2 on donation (donation_type_id);
alter table donation add constraint fk_donation_consumer_3 foreign key (consumer_id) references consumer (id);
create index ix_donation_consumer_3 on donation (consumer_id);
alter table donation_goal add constraint fk_donation_goal_project_4 foreign key (project_id) references project (id);
create index ix_donation_goal_project_4 on donation_goal (project_id);
alter table donation_goal add constraint fk_donation_goal_donationType_5 foreign key (donation_type_id) references donation_type (id);
create index ix_donation_goal_donationType_5 on donation_goal (donation_type_id);
alter table news add constraint fk_news_project_6 foreign key (project_id) references project (id);
create index ix_news_project_6 on news (project_id);
alter table project add constraint fk_project_consumer_7 foreign key (consumer_id) references consumer (id);
create index ix_project_consumer_7 on project (consumer_id);
alter table project add constraint fk_project_address_8 foreign key (address_id) references address (id);
create index ix_project_address_8 on project (address_id);



# --- !Downs

drop table if exists address cascade;

drop table if exists consumer cascade;

drop table if exists donation cascade;

drop table if exists donation_goal cascade;

drop table if exists donation_type cascade;

drop table if exists news cascade;

drop table if exists project cascade;

drop sequence if exists address_seq;

drop sequence if exists consumer_seq;

drop sequence if exists donation_seq;

drop sequence if exists donation_goal_seq;

drop sequence if exists donation_type_seq;

drop sequence if exists news_seq;

drop sequence if exists project_seq;

