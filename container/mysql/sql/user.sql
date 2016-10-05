CREATE DATABASE kakei_log_development;

SELECT * FROM User;

# テーブル定義の確認
SHOW COLUMNS FROM User;

# ユーザIDが1の支払先
SELECT *
FROM PaymentDestination
WHERE id IN (
  SELECT payment_destination_id FROM UserWithPaymentDestination WHERE user_id = 1
);

# デフォルト支払先
SELECT *
FROM PaymentDestination
WHERE id IN (
  SELECT payment_destination_id FROM UserWithPaymentDestination WHERE user_id IS NULL
);

SELECT *
FROM PaymentSource
WHERE id IN (
  SELECT payment_source_id FROM UserWithPaymentSource WHERE user_id IS NULL
);

# 親子カテゴリ
SELECT *
FROM Category
WHERE parent_id IN (
  SELECT id FROM Category WHERE name = "食費"
);
