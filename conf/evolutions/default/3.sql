# --- !Ups

CREATE TABLE PaymentSource (
  payment_source_id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255),
  user_id INT DEFAULT NULL,
  amount INT DEFAULT 0,
  UNIQUE KEY (`name`),
  FOREIGN KEY (`user_id`) REFERENCES User(`user_id`),
  PRIMARY KEY (`payment_source_id`)
);

INSERT INTO PaymentSource (name) VALUES ('財布');
INSERT INTO PaymentSource (name) VALUES ('銀行');
INSERT INTO PaymentSource (name) VALUES ('ポイント');

# --- !Downs
DROP TABLE PaymentSource;
