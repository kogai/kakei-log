# --- !Ups

CREATE TABLE PaymentSource (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255),
  PRIMARY KEY (`id`)
);

INSERT INTO PaymentSource (name) VALUES ('財布');
INSERT INTO PaymentSource (name) VALUES ('銀行');

# --- !Downs
DROP TABLE PaymentSource;

