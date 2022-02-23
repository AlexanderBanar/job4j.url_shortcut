create database urls;

create table sites(
                      id serial primary key,
                      name varchar,
                      login varchar,
                      password varchar
);

create table shortcuts(
                          id serial primary key,
                          key varchar,
                          value varchar,
                          count int
);