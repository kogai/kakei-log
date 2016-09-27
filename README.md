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
docker-compose exec mysql bash
mysql -udocker -pdocker
```

## 依存ライブラリのインストール

```bash
activator compile
```