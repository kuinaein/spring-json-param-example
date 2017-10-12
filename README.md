# Webpack (Vue.js) and Spring Boot Sample Project
* Not SPA - multilple HTML templates
* Context path support
* A Gradle build script for both server-side and client-side project with gradle-node-plugin

## Server Side
* Based on a Spring Initializr template
* Thymeleaf templates for server-side variable injection
* Locally-runnable HTMLs (after building the front project)
  * `./gradlew prepare buildFront` to build the front project
* Eclipse (and STS) support: `./gradlew prepare`

## Client Side
* Based on the Vue webpack template
* Little improved dev server support: `npm run dev`
* Server-side templates are generated from a EJS template and JS variables by HtmlWebpackPlugin
  * NOTE: To output '$', use `<%='$'%>` in EJS templates
* Bootstrap 3 (theme: [Honoka](http://honokak.osaka/bootstrap.html))

## Requirements
* Java 8 or later
* npm
* Yarn

## Demo
1. Enter following commands
  ``` bash
  git clone git@github.com:kuinaein/spring-webpack.git
  cd spring-webpack
  ./gradlew flashRun
  ```
2. Wait for build and app initialization
3. Open http://localhost:8080/actual-ctx/foo/create

## License
CC0 1.0 Universal (**except vue-webpack-boilerplate originated code**)

https://creativecommons.org/publicdomain/zero/1.0/
