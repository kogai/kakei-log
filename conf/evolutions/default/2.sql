# --- !Ups

CREATE TABLE accounts (
  id MEDIUMINT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  cost MEDIUMINT NOT NULL,
  category INT NOT NULL,
  register_at DATETIME NOT NULL,
  payment_from VARCHAR(255),
  PRIMARY KEY (`id`)
);

# --- !Downs

DROP TABLE accounts;

