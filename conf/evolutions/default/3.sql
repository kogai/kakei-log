# --- !Ups

CREATE TABLE Category (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255),
  hierarchy INT NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`)
);

INSERT INTO Category (name, hierarchy) VALUES ('食費', 0);
INSERT INTO Category (name, hierarchy) VALUES ('肉類', 1);
INSERT INTO Category (name, hierarchy) VALUES ('魚介類', 1);
INSERT INTO Category (name, hierarchy) VALUES ('野菜', 1);
INSERT INTO Category (name, hierarchy) VALUES ('乳製品', 1);
INSERT INTO Category (name, hierarchy) VALUES ('加工食品', 1);
INSERT INTO Category (name, hierarchy) VALUES ('調味料', 1);

INSERT INTO Category (name, hierarchy) VALUES ('日用品', 0);
INSERT INTO Category (name, hierarchy) VALUES ('清掃用品', 1);

# 中間テーブル
CREATE TABLE UserWithCategory (
  user_id INT,
  category_id INT,
  FOREIGN KEY (`user_id`) REFERENCES User(`id`),
  FOREIGN KEY (`category_id`) REFERENCES Category(`id`)
);

# --- !Downs

DROP TABLE Category;

