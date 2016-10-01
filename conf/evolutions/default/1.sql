# --- !Ups

CREATE TABLE users (
  id MEDIUMINT NOT NULL AUTO_INCREMENT,
  password_digest VARCHAR(255) NOT NULL,
  mail_address VARCHAR(255) NOT NULL,
  is_verified BOOLEAN NOT NULL DEFAULT FALSE,
  verifiy_id VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY (`mail_address`)
);

# --- !Downs

DROP TABLE users;

