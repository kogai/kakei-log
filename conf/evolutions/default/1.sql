# --- !Ups

CREATE TABLE User (
  id INT NOT NULL AUTO_INCREMENT,
  password_digest VARCHAR(255) NOT NULL,
  mail_address VARCHAR(255) NOT NULL,
  is_verified BOOLEAN NOT NULL DEFAULT FALSE,
  verifiy_id VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY (`mail_address`)
);

INSERT INTO User (password_digest, mail_address, is_verified, verifiy_id) VALUES (
  '$2a$10$5hodVnru4SFyYKNyAJxQU.iLnTcCRZ0N2dmwBTXGCGCseyxBPm5Q2',
  'test@test.com',
  1,
  'b20eb751-2aa5-43aa-adcb-c7eed50fec36'
), (
  '$2a$10$1SxrNaTLjv00.Exgpm/IEeoPmT3ipVB84ZXCVme/3k7zARl.bsxcq',
  'yet-register@test.com',
  0,
  'b20eb751-2aa5-43aa-adcb-c7eed50fec36'
);

# --- !Downs

DROP TABLE User;

