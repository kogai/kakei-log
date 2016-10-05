# --- !Ups

CREATE TABLE PaymentSource (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255),
  PRIMARY KEY (`id`)
);

INSERT INTO PaymentSource (name) VALUES ('財布');
INSERT INTO PaymentSource (name) VALUES ('銀行');

# 中間テーブル
CREATE TABLE UserWithPaymentSource (
  user_id INT,
  payment_source_id INT,
  FOREIGN KEY (`user_id`) REFERENCES User(`id`),
  FOREIGN KEY (`payment_source_id`) REFERENCES PaymentSource(`id`)
);

# --- !Downs
DROP TABLE PaymentSource;

