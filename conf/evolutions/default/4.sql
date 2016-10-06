# --- !Ups

CREATE TABLE PaymentDestination (
  payment_destination_id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255),
  user_id INT DEFAULT NULL,
  UNIQUE KEY (`name`),
  FOREIGN KEY (`user_id`) REFERENCES User(`user_id`),
  PRIMARY KEY (`payment_destination_id`)
);

# 初期値の挿入
INSERT INTO PaymentDestination (name) VALUES ('Amazon.jp');
INSERT INTO PaymentDestination (name) VALUES ('スーパー');
INSERT INTO PaymentDestination (name) VALUES ('コンビニ');
INSERT INTO PaymentDestination (name) VALUES ('生活協同組合');
INSERT INTO PaymentDestination (name) VALUES ('その他');

# --- !Downs
DROP TABLE PaymentDestination;

