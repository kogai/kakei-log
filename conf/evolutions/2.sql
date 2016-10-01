# --- !Ups

CREATE TABLE User (
  id MEDIUMINT NOT NULL AUTO_INCREMENT,
  password_digest VARCHAR(255) NOT NULL,
  mail_address VARCHAR(255) NOT NULL,
  is_verified INTEGER(1) NOT NULL DEFAULT 0,
  verifiy_id VARCHAR(36) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY (`mail_address`)
);

# --- !Downs
DROP TABLE User;

