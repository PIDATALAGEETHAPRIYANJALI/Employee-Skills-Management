-- Table: public.organization

-- DROP TABLE public.organization;

CREATE TABLE IF NOT EXISTS public.organization
(
    id bigint NOT NULL,
    city character varying(255) COLLATE pg_catalog."default",
    country character varying(255) COLLATE pg_catalog."default",
    email character varying(255) COLLATE pg_catalog."default" NOT NULL,
    org_name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    phone bigint,
    state character varying(255) COLLATE pg_catalog."default",
    zip bigint,
    user_id integer,
    CONSTRAINT organization_pkey PRIMARY KEY (id),
    CONSTRAINT fklr2xqteeiv28xewncy99y4nhn FOREIGN KEY (user_id)
        REFERENCES public.user_info (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE public.organization
    OWNER to postgres;