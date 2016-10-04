# --- !Ups

CREATE TABLE PaymentDestination (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255),
  PRIMARY KEY (`id`)
);

INSERT INTO PaymentDestination (name) VALUES ('Amazon.jp');
INSERT INTO PaymentDestination (name) VALUES ('スーパー');
INSERT INTO PaymentDestination (name) VALUES ('生活協同組合');
INSERT INTO PaymentDestination (name) VALUES ('その他');

# --- !Downs

DROP TABLE PaymentDestination;

