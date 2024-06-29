CREATE TABLE public.funcionario
(
  id_funcionario integer NOT NULL,
  nm_funcionario character varying(60),
  ds_funcionario character varying(30),
  sl_funcionario real,
  fn_funcionario character varying(20),
  en_funcionario character varying(60),
  CONSTRAINT pk_funcionario PRIMARY KEY (id_funcionario)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.funcionario
  OWNER TO postgres;


CREATE SEQUENCE public.funcionario_seq
  INCREMENT 0
  MINVALUE 0
  MAXVALUE 0
  START 0
  CACHE 0;
ALTER TABLE public.funcionario_seq
  OWNER TO postgres;
