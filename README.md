# bingo-ifa-server
*Work In Progress*

RESTful server of a Bingo game for IFA INSA students.

[![Pipeline status](http://git.mouseover.fr/bmarsaud/bingo-ifa-server/badges/master/build.svg)](https://git.mouseover.fr/bmarsaud/bingo-ifa-server/pipelines)
![Code coverage](http://git.mouseover.fr/bmarsaud/bingo-ifa-server/badges/master/coverage.svg)
[![Maintainability](https://api.codeclimate.com/v1/badges/90e743573944222a4323/maintainability)](https://codeclimate.com/github/TheZopo/bingo-ifa-server/maintainability)
[![Dependencies](https://app.updateimpact.com/badge/1080871293970157568/bingo-ifa-server.svg?config=test)](https://app.updateimpact.com/latest/1080871293970157568/bingo-ifa-server)

## Run server
Running server : `mvn exec:java`

Running tests : `mvn clean test`

## Entry points
* `grid`
    * `grid/{gridId}` : get grid by id
    * `grid/shuffle/{gridId}` : shuffle one box of the grid
    * `grid/check/{boxPosition}` check the box at a position
* `user`
    * `user/{userId}` : get user by id
* `sentence`
    * `sentence/{sentenceId}` : get sentence by id
    * `sentence/upvote/{sentenceId}` : upvote a sentence
    * `sentence/downvote/{sentenceId}` : downvote a sentence
* `history`
    * `history` : get all history
    * `history/byUser/{userId}` : get a user's hostory

## Dependencies
* [Jersey](https://jersey.github.io/) - Framework for developing RESTful web services with JAX-RS support
* [Grizzly2](https://javaee.github.io/grizzly/) - Highly scalable web server for J2EE applications
* [HikariCP](https://brettwooldridge.github.io/HikariCP/) - JDBC connection pool
* [MariaDB Connector/J](https://mariadb.com/kb/en/library/about-mariadb-connector-j/) - MariaDB JDBC connector
* [jersey-hmac-auth](https://github.com/bazaarvoice/jersey-hmac-auth) - HMAC auth implementation for Jersey
* [JUnit](https://junit.org/junit5/) - Test framework for java
* [Jacoco](https://www.jacoco.org/jacoco/) - Code coverage library for Java

## Contributors

|Name|Role|
|---|---|
|Bastien Marsaud|Developer|