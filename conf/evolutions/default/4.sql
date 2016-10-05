# --- !Ups

CREATE TABLE PaymentSource (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255),
  UNIQUE KEY (`name`),
  PRIMARY KEY (`id`)
);

# 中間テーブル
CREATE TABLE UserWithPaymentSource (
  user_id INT,
  payment_source_id INT,
  FOREIGN KEY (`user_id`) REFERENCES User(`id`),
  FOREIGN KEY (`payment_source_id`) REFERENCES PaymentSource(`id`)
);

INSERT INTO PaymentSource (name) VALUES ('財布');
INSERT INTO PaymentSource (name) VALUES ('銀行');
INSERT INTO PaymentSource (name) VALUES ('ポイント');

INSERT INTO UserWithPaymentSource (user_id, payment_source_id) VALUES (NULL, 1);
INSERT INTO UserWithPaymentSource (user_id, payment_source_id) VALUES (NULL, 2);
INSERT INTO UserWithPaymentSource (user_id, payment_source_id) VALUES (NULL, 3);

# --- !Downs
DROP TABLE UserWithPaymentSource;
DROP TABLE PaymentSource;
