Playのサンプルアプリケーション

## サーバの起動

```bash
activator run
```

## DBコンテナの起動

```bash
docker-compose up -d mysql
```

## DBコンテナへの接続

```bash
# コンテナの中から
docker-compose exec mysql bash
mysql -udocker -pdocker

# コンテナの外から
mysql -h $DOCKER_IP -P 3306 -udocker -pdocker -D kakei_log_development
```

## 依存ライブラリのインストール

```bash
activator compile
```

## テストの個別実行

```bas
activator "testOnly CategorySpec"
activator "~testOnly CategorySpec"
```