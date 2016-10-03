# --- !Ups

CREATE TABLE PaymentSource (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255),
  user_id INT NOT NULL,
  FOREIGN KEY (`user_id`) REFERENCES User(`id`),
  PRIMARY KEY (`id`)
);

# --- !Downs

DROP TABLE PaymentSource;

