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
mysql -h 192.168.99.100 -P 3306 -udocker -pdocker
```

## 依存ライブラリのインストール

```bash
activator compile
```