# Spring Boot で特定のリクエストパラメータを JSON として ModelAttribute に読み込ませるサンプル

hidden フィールド `json` の内容を Jackson で読み込み、 ModelAttribute にセットするサンプルです。下記のペインを解消するための試作品です。

* 今どきフォーム送信のためにちまちま hidden フィールドを仕込みたくない
* Ajax 通信やクライアントサイドでの画面遷移制御もあまりしたくない

`kuina.spring_webpack.binder` パッケージのソースが全てです。
フロント側にもごちゃごちゃ色々と入っていますが、あまり本題と関係ありません。

## ビルドに必要なもの
* Java 8 or later
* npm
* Yarn

## 実行方法
1. シェルで下記のコマンドを実行します
  ``` bash
  git clone git@github.com:kuinaein/spring-json-param-example.git
  cd spring-json-param-example
  ./gradlew flashRun
  ```
2. ビルド及びアプリケーション初期化を待ちます
3. ブラウザで http://localhost:8080/ を開きます
4. 一番上のボタンを押下します

## ライセンス
CC0 1.0 (**ただし、フロント側の vue-webpack-boilerplate 由来コードを除きます**)
