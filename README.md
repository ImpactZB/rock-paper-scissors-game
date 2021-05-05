## Application
Simple Rock Paper Scissors game simulator

### Port
8080

### Building & Running
```
mvn clean package
java -jar target/rock-paper-scissors-game*.jar
```

### Documentation
Swagger Documentation available at /swagger-ui.html

## How to play?
Simulator has two functionalities. It can judge a game between you and your friends or let you play with him.
For first option there can be any players number, but at least two of them. Players must have unique names.
If would you like to play with computer, please select your favourite shape and let simulator verify if you win with computer shape.

## Examples
Judge the game:
POST:
```
localhost:8080/game/judge
```
BODY:
```
{
"players": [{"name":"p1", "shape":"ROCK"}, {"name":"p2", "shape":"SCISSORS"}, {"name":"p3", "shape":"ROCK"}]
}
```

Play with computer:
POST:
```
localhost:8080/game/playWithComputer
```
BODY:
```
{
"shape":"ROCK"
}
```