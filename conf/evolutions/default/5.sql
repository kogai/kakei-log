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

# 中間テーブル
CREATE TABLE UserWithPaymentDestination (
  user_id INT,
  payment_destination_id INT,
  FOREIGN KEY (`user_id`) REFERENCES User(`id`),
  FOREIGN KEY (`payment_destination_id`) REFERENCES PaymentDestination(`id`)
);

# --- !Downs

DROP TABLE PaymentDestination;

