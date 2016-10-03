# --- !Ups

CREATE TABLE PaymentDestination (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255),
  PRIMARY KEY (`id`)
);

# --- !Downs

DROP TABLE PaymentDestination;

