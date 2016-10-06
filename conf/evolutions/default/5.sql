# --- !Ups

CREATE TABLE Account (
  id INT NOT NULL AUTO_INCREMENT,
  user_id INT DEFAULT NULL,
  name VARCHAR(255) NOT NULL,
  cost INT NOT NULL,
  category_id INT NOT NULL,
  register_at DATE NOT NULL,
  payment_destination_id INT,
  payment_source_id INT,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`user_id`) REFERENCES User(`user_id`),
  FOREIGN KEY (`category_id`) REFERENCES Category(`category_id`),
  FOREIGN KEY (`payment_destination_id`) REFERENCES PaymentDestination(`payment_destination_id`),
  FOREIGN KEY (`payment_source_id`) REFERENCES PaymentSource(`payment_source_id`)
);

# --- !Downs
DROP TABLE Account;

