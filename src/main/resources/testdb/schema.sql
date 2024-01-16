drop table TICKETS if exists;

create table TICKETS (ID bigint identity primary key, NUMBER varchar(9));
CREATE SEQUENCE public.seq_tickets START WITH 1 INCREMENT BY 1;