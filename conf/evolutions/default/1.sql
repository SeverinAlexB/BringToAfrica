# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table address (
  id                        bigint not null,
  country                   varchar(255),
  city                      varchar(255),
  constraint pk_address primary key (id))
;

create table donation (
  id                        bigint not null,
  date                      date,
  amount                    integer,
  message_to_collector      varchar(255),
  user_id                   bigint,
  donation_goal_id          bigint,
  constraint pk_donation primary key (id))
;

create table donation_goal (
  id                        bigint not null,
  amount                    integer,
  project_id                bigint,
  type_id                   bigint,
  constraint pk_donation_goal primary key (id))
;

create table donation_type (
  id                        bigint not null,
  name                      varchar(255),
  constraint pk_donation_type primary key (id))
;

create table news (
  id                        bigint not null,
  title                     varchar(255),
  description               varchar(255),
  date                      date,
  image_url                 varchar(255),
  project_id                bigint,
  constraint pk_news primary key (id))
;

create table Project (
  id                        bigint not null,
  title                     varchar(255),
  description               varchar(255),
  image_url                 varchar(255),
  starts_at                 date,
  ends_at                   date,
  contact                   varchar(255),
  address_id                bigint,
  owner_id                  bigint,
  constraint pk_Project primary key (id))
;

create table AfrikaUser (
  id                        bigint not null,
  first_name                varchar(255),
  last_name                 varchar(255),
  email                     varchar(255),
  password_hashed_salted    varchar(255),
  constraint pk_AfrikaUser primary key (id))
;

create sequence address_seq;

create sequence donation_seq;

create sequence donation_goal_seq;

create sequence donation_type_seq;

create sequence news_seq;

create sequence Project_seq;

create sequence AfrikaUser_seq;

alter table donation add constraint fk_donation_user_1 foreign key (user_id) references AfrikaUser (id) on delete restrict on update restrict;
create index ix_donation_user_1 on donation (user_id);
alter table donation add constraint fk_donation_donationGoal_2 foreign key (donation_goal_id) references donation_goal (id) on delete restrict on update restrict;
create index ix_donation_donationGoal_2 on donation (donation_goal_id);
alter table donation_goal add constraint fk_donation_goal_project_3 foreign key (project_id) references Project (id) on delete restrict on update restrict;
create index ix_donation_goal_project_3 on donation_goal (project_id);
alter table donation_goal add constraint fk_donation_goal_type_4 foreign key (type_id) references donation_type (id) on delete restrict on update restrict;
create index ix_donation_goal_type_4 on donation_goal (type_id);
alter table news add constraint fk_news_project_5 foreign key (project_id) references Project (id) on delete restrict on update restrict;
create index ix_news_project_5 on news (project_id);
alter table Project add constraint fk_Project_address_6 foreign key (address_id) references address (id) on delete restrict on update restrict;
create index ix_Project_address_6 on Project (address_id);
alter table Project add constraint fk_Project_owner_7 foreign key (owner_id) references AfrikaUser (id) on delete restrict on update restrict;
create index ix_Project_owner_7 on Project (owner_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists address;

drop table if exists donation;

drop table if exists donation_goal;

drop table if exists donation_type;

drop table if exists news;

drop table if exists Project;

drop table if exists AfrikaUser;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists address_seq;

drop sequence if exists donation_seq;

drop sequence if exists donation_goal_seq;

drop sequence if exists donation_type_seq;

drop sequence if exists news_seq;

drop sequence if exists Project_seq;

drop sequence if exists AfrikaUser_seq;

