CREATE DATABASE kakei_log_development;
DROP TABLE User;

CREATE TABLE User (
  id MEDIUMINT NOT NULL AUTO_INCREMENT,
  password_digest VARCHAR(255) NOT NULL,
  mail_address VARCHAR(255) NOT NULL,
  is_verified INTEGER(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE KEY (`mail_address`)
);

INSERT INTO User (password_digest, mail_address, is_verified) VALUES (
  # 'test,
  '$2a$10$5hodVnru4SFyYKNyAJxQU.iLnTcCRZ0N2dmwBTXGCGCseyxBPm5Q2',
  'test@test.com',
  1
), (
  # 'test-password,
  '$2a$10$1SxrNaTLjv00.Exgpm/IEeoPmT3ipVB84ZXCVme/3k7zARl.bsxcq',
  'yet-register@test.com',
  0
);

SELECT * FROM User;
