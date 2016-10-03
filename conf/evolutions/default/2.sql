# --- !Ups

CREATE TABLE Account (
  id INT NOT NULL AUTO_INCREMENT,
  user_id INT NOT NULL,
  name VARCHAR(255) NOT NULL,
  cost MEDIUMINT NOT NULL,
  category INT NOT NULL,
  register_at DATE NOT NULL,
  payment_destination INT,
  payment_source INT,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`user_id`) REFERENCES User(`id`),
  FOREIGN KEY (`category`) REFERENCES Category(`id`),
  FOREIGN KEY (`payment_destination`) REFERENCES PaymentDestination(`id`),
  FOREIGN KEY (`payment_source`) REFERENCES PaymentSource(`id`)
);

# --- !Downs

DROP TABLE Account;

