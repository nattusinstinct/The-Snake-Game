# Snake Game

A console-based Snake game written in Java, structured with the MVC pattern.

## Project structure

```
src/com/snakegame/
  Main.java                  - entry point
  controller/
    GameController.java      - game loop, input handling
    ScoreController.java     - end-of-game scoring flow
  service/
    GameService.java         - movement, collisions, scoring rules
    FoodService.java         - food spawning
    ScoreService.java        - score card and ranking
  dto/
    Board.java, Cell.java, Direction.java, Food.java,
    Snake.java, Player.java, ScoreCard.java, LeaderboardEntry.java
  dao/
    LeaderboardDAO.java      - leaderboard persistence
    JsonUtil.java            - JSON read/write helpers
  view/
    GameView.java            - all console output
data/
  leaderboard.json           - saved high scores
```

## How to compile and run

Run these from the project root (the game reads `data/leaderboard.json`):

```
javac -d out $(find src -name "*.java")
java -cp out com.snakegame.Main
```

## How to play

- Enter your name when the game starts.
- The snake moves one cell per input: `L` = left, `R` = right, `T` = top, `B` = bottom. Enter `Q` to quit.
- Food appears at a random empty cell and shows its points (1-5). Move onto it to score the points and grow by one cell.
- The game ends when the snake hits the wall or runs into itself.
- When the game ends, your score card is shown and your score is saved to the leaderboard, which keeps the top 10 scores.

## Expected behavior

```
==============================
          SNAKE GAME
==============================
Enter your name: Arun
+------------+
|............|
|............|
|............|
|............|
|............|
|....ooS.....|
|............|
|......2.....|
|............|
|............|
+------------+
Score: 0
Move (L/R/T/B) or Q to quit: B
+------------+
|............|
|............|
|............|
|............|
|............|
|.....oo.....|
|......S.....|
|......2.....|
|............|
|............|
+------------+
Score: 0
Move (L/R/T/B) or Q to quit: B
Yum! That food was worth it.
+------------+
|............|
|............|
|............|
|............|
|............|
|.....oo.....|
|......o.....|
|......S.....|
|.......3....|
|............|
+------------+
Score: 2
Move (L/R/T/B) or Q to quit: B
+------------+
|............|
|............|
|............|
|............|
|............|
|......o.....|
|......o.....|
|......o.....|
|......S3....|
|............|
+------------+
Score: 2
Move (L/R/T/B) or Q to quit: R
Yum! That food was worth it.
+------------+
|............|
|............|
|...........2|
|............|
|............|
|......o.....|
|......o.....|
|......o.....|
|......oS....|
|............|
+------------+
Score: 5
Move (L/R/T/B) or Q to quit: T
+------------+
|............|
|............|
|...........2|
|............|
|............|
|............|
|......o.....|
|......oS....|
|......oo....|
|............|
+------------+
Score: 5
Move (L/R/T/B) or Q to quit: T
+------------+
|............|
|............|
|...........2|
|............|
|............|
|............|
|.......S....|
|......oo....|
|......oo....|
|............|
+------------+
Score: 5
Move (L/R/T/B) or Q to quit: T
+------------+
|............|
|............|
|...........2|
|............|
|............|
|.......S....|
|.......o....|
|.......o....|
|......oo....|
|............|
+------------+
Score: 5
Move (L/R/T/B) or Q to quit: T
+------------+
|............|
|............|
|...........2|
|............|
|.......S....|
|.......o....|
|.......o....|
|.......o....|
|.......o....|
|............|
+------------+
Score: 5
Move (L/R/T/B) or Q to quit: T
+------------+
|............|
|............|
|...........2|
|.......S....|
|.......o....|
|.......o....|
|.......o....|
|.......o....|
|............|
|............|
+------------+
Score: 5
Move (L/R/T/B) or Q to quit: T
+------------+
|............|
|............|
|.......S...2|
|.......o....|
|.......o....|
|.......o....|
|.......o....|
|............|
|............|
|............|
+------------+
Score: 5
Move (L/R/T/B) or Q to quit: R
+------------+
|............|
|............|
|.......oS..2|
|.......o....|
|.......o....|
|.......o....|
|............|
|............|
|............|
|............|
+------------+
Score: 5
Move (L/R/T/B) or Q to quit: R
+------------+
|............|
|............|
|.......ooS.2|
|.......o....|
|.......o....|
|............|
|............|
|............|
|............|
|............|
+------------+
Score: 5
Move (L/R/T/B) or Q to quit: R
+------------+
|............|
|............|
|.......oooS2|
|.......o....|
|............|
|............|
|............|
|............|
|............|
|............|
+------------+
Score: 5
Move (L/R/T/B) or Q to quit: R
Yum! That food was worth it.
+------------+
|............|
|............|
|.......ooooS|
|.......o....|
|............|
|............|
|............|
|5...........|
|............|
|............|
+------------+
Score: 7
Move (L/R/T/B) or Q to quit: Q
You quit the game.
==============================
           SCORE CARD
==============================
Player       : Arun
Score        : 7
Food eaten   : 3
Snake length : 6
Time played  : 0s
Leaderboard rank: #5
------------------------------
           LEADERBOARD
------------------------------
#    Name           Score  Date
1    Meena          27     2026-11-10
2    Arun           19     2026-11-11
3    Divya          12     2026-11-11
4    Karthik        8      2026-11-12
5    Arun           7      2026-11-13
6    Priya          5      2026-11-12
```

## Current behavior

The current build does not match the expected behavior above. Here is a sample run of the current build:

```
==============================
          SNAKE GAME
==============================
Enter your name: Arun
+----------+
|...O........|
|............|
|............|
|............|
|.....S......|
|............|
|............|
|............|
|............|
+----------+
Score: 0
Move (L/R/T/B) or Q to quit: T
+----------+
|...O........|
|............|
|............|
|............|
|............|
|.....S......|
|............|
|............|
|............|
+----------+
Score: 1
Move (L/R/T/B) or Q to quit: B
+----------+
|...O........|
|............|
|............|
|............|
|............|
|............|
|.....S......|
|............|
|............|
+----------+
Score: 2
Move (L/R/T/B) or Q to quit: T
+----------+
|...O........|
|............|
|............|
|............|
|......S.....|
|............|
|............|
|............|
|............|
+----------+
Score: 3
Move (L/R/T/B) or Q to quit: B
You hit the wall!
+----------+
|...O........|
|............|
|............|
|............|
|......S.....|
|............|
|............|
|............|
|............|
+----------+
Score: 3
==============================
           SCORE CARD
==============================
Player       : Arun
Score        : 3
Food eaten   : 3
Snake length : 0
Time played  : 4s
------------------------------
           LEADERBOARD
------------------------------
#    Name           Score  Date
```
