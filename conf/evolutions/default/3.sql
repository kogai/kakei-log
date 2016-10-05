# --- !Ups
CREATE TABLE Category (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255),
  hierarchy INT NOT NULL DEFAULT 0,
  parent_id INT REFERENCES Category(`id`),
  UNIQUE KEY (`name`),
  PRIMARY KEY (`id`)
);

# 中間テーブル
CREATE TABLE UserWithCategory (
  user_id INT,
  category_id INT,
  FOREIGN KEY (`user_id`) REFERENCES User(`id`),
  FOREIGN KEY (`category_id`) REFERENCES Category(`id`)
);

INSERT INTO Category (name, hierarchy, parent_id) VALUES ('食費', 0, NULL);
INSERT INTO Category (name, hierarchy, parent_id) VALUES ('肉類', 1, 1);
INSERT INTO Category (name, hierarchy, parent_id) VALUES ('魚介類', 1, 1);
INSERT INTO Category (name, hierarchy, parent_id) VALUES ('野菜', 1, 1);
INSERT INTO Category (name, hierarchy, parent_id) VALUES ('乳製品', 1, 1);
INSERT INTO Category (name, hierarchy, parent_id) VALUES ('加工食品', 1, 1);
INSERT INTO Category (name, hierarchy, parent_id) VALUES ('調味料', 1, 1);
INSERT INTO Category (name, hierarchy, parent_id) VALUES ('日用品', 0, NULL);
INSERT INTO Category (name, hierarchy, parent_id) VALUES ('清掃用品', 1, 8);

INSERT INTO UserWithCategory (user_id, category_id) VALUES (NULL, 1);
INSERT INTO UserWithCategory (user_id, category_id) VALUES (NULL, 2);
INSERT INTO UserWithCategory (user_id, category_id) VALUES (NULL, 3);
INSERT INTO UserWithCategory (user_id, category_id) VALUES (NULL, 4);
INSERT INTO UserWithCategory (user_id, category_id) VALUES (NULL, 5);
INSERT INTO UserWithCategory (user_id, category_id) VALUES (NULL, 6);
INSERT INTO UserWithCategory (user_id, category_id) VALUES (NULL, 7);
INSERT INTO UserWithCategory (user_id, category_id) VALUES (NULL, 8);
INSERT INTO UserWithCategory (user_id, category_id) VALUES (NULL, 9);

# --- !Downs
DROP TABLE UserWithCategory;
DROP TABLE Category;
