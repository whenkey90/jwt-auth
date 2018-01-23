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