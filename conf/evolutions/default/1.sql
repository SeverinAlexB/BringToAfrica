# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table person (
  id                        varchar(255) not null,
  name                      varchar(255),
  constraint pk_person primary key (id))
;

create table project (
  id                        varchar(255) not null,
  title                     varchar(255),
  description               varchar(255),
  contact                   varchar(255),
  constraint pk_project primary key (id))
;

create sequence person_seq;

create sequence project_seq;




# --- !Downs

drop table if exists person cascade;

drop table if exists project cascade;

drop sequence if exists person_seq;

drop sequence if exists project_seq;

