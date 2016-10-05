# --- !Ups

CREATE TABLE PaymentDestination (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255),
  UNIQUE KEY (`name`),
  PRIMARY KEY (`id`)
);

# 中間テーブル
CREATE TABLE UserWithPaymentDestination (
  user_id INT,
  payment_destination_id INT,
  FOREIGN KEY (`user_id`) REFERENCES User(`id`),
  FOREIGN KEY (`payment_destination_id`) REFERENCES PaymentDestination(`id`)
);

# 初期値の挿入
INSERT INTO PaymentDestination (name) VALUES ('Amazon.jp');
INSERT INTO PaymentDestination (name) VALUES ('スーパー');
INSERT INTO PaymentDestination (name) VALUES ('コンビニ');
INSERT INTO PaymentDestination (name) VALUES ('生活協同組合');
INSERT INTO PaymentDestination (name) VALUES ('その他');

INSERT INTO UserWithPaymentDestination (user_id, payment_destination_id) VALUES (NULL, 1);
INSERT INTO UserWithPaymentDestination (user_id, payment_destination_id) VALUES (NULL, 2);
INSERT INTO UserWithPaymentDestination (user_id, payment_destination_id) VALUES (NULL, 3);
INSERT INTO UserWithPaymentDestination (user_id, payment_destination_id) VALUES (NULL, 4);
INSERT INTO UserWithPaymentDestination (user_id, payment_destination_id) VALUES (NULL, 5);

# --- !Downs

DROP TABLE UserWithPaymentDestination;
DROP TABLE PaymentDestination;

