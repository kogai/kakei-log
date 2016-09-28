CREATE DATABASE kakei_log_development;
DROP TABLE User;

# 0 = False 1 = True
CREATE TABLE User (
  id MEDIUMINT NOT NULL AUTO_INCREMENT,
  password_digest VARCHAR(255) NOT NULL,
  mail_address VARCHAR(255) NOT NULL,
  is_verified INTEGER(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY (`mail_address`)
);

INSERT INTO User (password_digest, mail_address, is_verified) VALUES (
  'test',
  'test@test.com',
  1
), (
  'test-password',
  'yet-register@test.com',
  0
);

SELECT * FROM User;