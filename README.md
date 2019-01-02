# bingo-ifa-server
*Work In Progress*

RESTful server of a Bingo game for IFA INSA students.

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
* [JUnit](https://junit.org/junit5/) - Test framework for java

## Contributors

|Name|Role|
|---|---|
|Bastien Marsaud|Developer|