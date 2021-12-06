
CREATE TABLE IF NOT EXISTS public.authority
(
    id integer NOT NULL,
    name character varying(50),
    CONSTRAINT role_pkey PRIMARY KEY (id),
    CONSTRAINT authority_name_unique_index UNIQUE (name)
)


insert into authority values (1,'ROLE_SUPER_ADMIN');
insert into authority values (2,'ROLE_HR');
insert into authority values (3,'ROLE_EMPLOYEE');