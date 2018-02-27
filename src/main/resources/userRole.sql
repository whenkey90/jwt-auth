CREATE  TABLE users (
  username VARCHAR(45) NOT NULL ,
  password VARCHAR(45) NOT NULL ,
  enabled boolean NOT NULL DEFAULT true ,
  PRIMARY KEY (username));

CREATE TABLE user_roles (
  user_role_id integer NOT NULL,
  username varchar(45) NOT NULL,
  role varchar(45) NOT NULL,
  PRIMARY KEY (user_role_id));

INSERT INTO users(username,password,enabled)
VALUES ('reader','reader', true);
INSERT INTO users(username,password,enabled)
VALUES ('writer','writer', true);

INSERT INTO user_roles (user_role_id, username, role)
VALUES (111, 'reader', 'READ');
INSERT INTO user_roles (user_role_id, username, role)
VALUES (222, 'reader', 'WRITE');
INSERT INTO user_roles (user_role_id, username, role)
VALUES (333, 'writer', 'READ');
INSERT INTO user_roles (user_role_id, username, role)
VALUES (444, 'writer', 'WRITE');

drop table if exists oauth_client_details;
        create table oauth_client_details (
        client_id VARCHAR(255) PRIMARY KEY,
        resource_ids VARCHAR(255),
        client_secret VARCHAR(255),
        scope VARCHAR(255),
        authorized_grant_types VARCHAR(255),
        web_server_redirect_uri VARCHAR(255),
        authorities VARCHAR(255),
        access_token_validity INTEGER,
        refresh_token_validity INTEGER,
        additional_information VARCHAR(4096),
        autoapprove VARCHAR(255)
        );
INSERT INTO oauth_client_details (client_id,resource_ids,client_secret,
  scope,
  authorized_grant_types, web_server_redirect_uri, authorities,
  access_token_validity, refresh_token_validity, autoapprove)
VALUES('blueprint_client_id','blueprint','blueprint_client_secret',
'blueprint',
'implicit,refresh_token,client_credentials,password,authorization_code', 'www.accion.com','READ, WRITE',
600000,11, 'true');

--
--KeySpring Scripts.
CREATE TABLE public.tenant
(
  id character varying(255) NOT NULL,
  ad_role character varying(255),
  ks_role character varying(255),
  sub_company character varying(255),
  tenant character varying(255),
  user_name character varying(50),
  CONSTRAINT tenant_pkey PRIMARY KEY (id)
)


CREATE TABLE public.authorities
(
  id character varying(255) NOT NULL,
  authority character varying(255),
  role character varying(255),
  CONSTRAINT authorities_pkey PRIMARY KEY (id)
)

INSERT INTO public.tenant(
            id, ad_role, ks_role, sub_company, tenant, user_name)
    VALUES ('111', 'READ', 'ROLE_READ', 'sb1', 't1', 'ben');
INSERT INTO public.tenant(
            id, ad_role, ks_role, sub_company, tenant, user_name)
    VALUES ('222', 'WRITE', 'ROLE_WRITE', 'sb2', 't2', 'bob');
INSERT INTO public.authorities(
            id, authority, role)
    VALUES ('111', 'READ', 'ROLE_READ');
INSERT INTO public.authorities(
            id, authority, role)
    VALUES ('222', 'WRITE', 'ROLE_WRITE');
INSERT INTO public.authorities(
            id, authority, role)
    VALUES ('333', 'READ', 'ROLE_WRITE');
