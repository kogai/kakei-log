# --- !Ups

CREATE TABLE Category (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255),
  hierarchy INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`)
);

# --- !Downs

DROP TABLE Category;

