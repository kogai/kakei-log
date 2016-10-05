# --- !Ups
CREATE TABLE Category (
  category_id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(255),
  hierarchy INT NOT NULL DEFAULT 0,
  parent_id INT REFERENCES Category(`category_id`),
  user_id INT DEFAULT NULL,
  UNIQUE KEY (`name`),
  FOREIGN KEY (`user_id`) REFERENCES User(`user_id`),
  PRIMARY KEY (`category_id`)
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

# --- !Downs
DROP TABLE Category;
