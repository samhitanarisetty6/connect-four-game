# Connect Four (Java)

A Java implementation of the classic Connect Four game, playable from the
command line. Two players take turns dropping colored tokens into a grid;
the first to connect four tokens in a row (horizontally, vertically, or
diagonally) wins.

This project was originally built for CS 1302 (Software Development) at the
University of Georgia and is being published here as a portfolio piece.

## Features

- Configurable board size (6-9 rows, 7-9 columns)
- Custom player token colors (BLUE, CYAN, GREEN, PURPLE, RED, YELLOW)
- Win detection in all four directions: horizontal, vertical, and both diagonals
- Draw detection when the board fills up
- Simple text-based CLI with ASCII banners

## Project structure

```
connect-four-game/
├── src/main/java/cs1302/
│   ├── game/
│   │   ├── ConnectFour.java        # core game logic and state
│   │   ├── ConnectFourCLI.java     # command-line interface / driver
│   │   └── ConnectFourTester.java  # sample unit-style tests
│   └── gameutil/
│       ├── Token.java              # enum of available token colors
│       ├── GamePhase.java          # enum of game lifecycle states
│       └── TokenGrid.java          # helper for printing the grid
├── resources/
│   ├── welcome.txt
│   ├── connectfour.txt
│   └── gameover.txt
└── README.md
```

## How to build and run

This project has no external dependencies -- just a JDK (11+).

```bash
# from the project root
mkdir -p out
javac -d out $(find src -name "*.java")

# run the interactive CLI game
java -cp out:resources cs1302.game.ConnectFourCLI

# run the sample tests
java -cp out cs1302.game.ConnectFourTester
```

On Windows, replace `out:resources` with `out;resources`.
