# ChessMaster User Guide

## Introduction

ChessMasterCLI is a command-line interface (CLI) chess game designed to make learning and training accessible for beginners while offering an engaging experience for all skill levels. This sleek and user-friendly chess simulator provides a platform for novice players to build their skills and understanding of the game.

- [Quick Start](#quick-start)
- [Starting a game](#starting-a-game)
- [Gameplay]()
- [Features](#features)
    - Gameplay features
        - [Make a move](#make-a-move)
        - [Show available moves: `moves`](#show-available-moves-moves)
        - [Show current chess board: `show`](#show-current-chess-board-show)
    - Getting help
        - [Show chess rules: `rules`](#show-chess-rules-rules)
        - [Show commands: `help`](#show-commands-help)
    - [Aborting game: `abort`](#aborting-game-abort)
- [Command Summary](#command-summary)

## Quick Start

1. Ensure you have **Java 11 or above** installed in your system.

2. Download the latest version of `ChessMaster.jar` from [here](https://github.com/AY2324S1-CS2113-T18-1/tp/releases).

3. Open a terminal instance and navigate into the folder that contains the downloaded ChessMaster.jar file.

```bash
cd PATH_TO_JAR_FILE
```

4. Run the jar application with the following command:

```bash
java -jar ChessMaster.jar
```

You should be greeted by a welcome message from ChessMaster:
```
_________________________________________________________________

Hey there, chess geek! You have stumbled upon the one and only:
    ________                      __  ___           __
   / ____/ /_  ___  __________   /  |/  /___ ______/ /____  _____
  / /   / __ \/ _ \/ ___/ ___/  / /|_/ / __ `/ ___/ __/ _ \/ ___/
 / /___/ / / /  __(__  |__  )  / /  / / /_/ (__  ) /_/  __/ /
 \____/_/ /_/\___/____/____/  /_/  /_/\__,_/____/\__/\___/_/

where CHESS becomes an exciting journey of strategy and skill!
_________________________________________________________________
```

## Starting a game

Upon launching the application, ChessMaster checks if there is a previous game in progress. If a previous game exists, you'll be presented with the option to continue from where you left off. Please enter `y` for **yes** and `n` for **no**.

```
You have an ongoing previous chess game. Continue game? [y/n]
```

However, if you prefer to **start a fresh game** or if **no previous game is found**, ChessMaster will prompt you to select your preferred color—whether it's "White" or "Black." Please enter `b` for **black** and `w` for **white**.

```
Choose your starting color to start new game! [b/w]
```

Now, you'll be off to a rewarding journey of enhancing your chess skills and enjoying the timeless game of strategy and tactics with ChessMaster!

## Gameplay

The gameplay system of ChessMaster provides you with the flexibility to enter either a **command** or a **move** during your turn. 

- If you opt for a **command**, the system will promptly solicit your next action, allowing you to access various [features](#features) seamlessly. However, when you decide to make a move, the system will evaluate your input for validity. 
- Only when you enter a **valid move** will your turn be passed to the CPU, enabling it to respond with its strategic move. 

Additionally, every time a move is executed, ChessMaster provides a comprehensive view of the chessboard, displaying the state of the game following the previous move.

<table>
<tr>
    <th>User Move</th>
    <th>CPU move</th>
</tr>
<tr>
<td>
  
```json
d2 d4
_________________________________________________________________

You moved Pawn from d2 to d4
_________________________________________________________________

                 (a) (b) (c) (d) (e) (f) (g) (h)
                _________________________________
            (8) | R | N | B | Q | K | B | N | R | (8)
                _________________________________
            (7) | P | P | P | P | P | P | P | P | (7)
                _________________________________
            (6) |   |   |   |   |   |   |   |   | (6)
                _________________________________
            (5) |   |   |   |   |   |   |   |   | (5)
                _________________________________
            (4) |   |   |   |(p)|   |   |   |   | (4)
                _________________________________
            (3) |   |   |   |   |   |   |   |   | (3)
                _________________________________
            (2) | p | p | p |( )| p | p | p | p | (2)
                _________________________________
            (1) | r | n | b | q | k | b | n | r | (1)
                _________________________________
                 (a) (b) (c) (d) (e) (f) (g) (h)
```

</td>
<td>

```json

_________________________________________________________________

ChessMaster moved Pawn from e7 to e5
_________________________________________________________________

                 (a) (b) (c) (d) (e) (f) (g) (h)
                _________________________________
            (8) | R | N | B | Q | K | B | N | R | (8)
                _________________________________
            (7) | P | P | P | P |( )| P | P | P | (7)
                _________________________________
            (6) |   |   |   |   |   |   |   |   | (6)
                _________________________________
            (5) |   |   |   |   |(P)|   |   |   | (5)
                _________________________________
            (4) |   |   |   | p |   |   |   |   | (4)
                _________________________________
            (3) |   |   |   |   |   |   |   |   | (3)
                _________________________________
            (2) | p | p | p |   | p | p | p | p | (2)
                _________________________________
            (1) | r | n | b | q | k | b | n | r | (1)
                _________________________________
                 (a) (b) (c) (d) (e) (f) (g) (h)
```

</td>
</tr>
</table>

## Features 

### Make a move

### Show available moves: `moves`

### Show current chess board: `show`

### Show chess rules: `rules`

### Show commands: `help`

### Aborting game: `abort`

Exit the ChessMaster application.

Format: `abort`

### Adding a todo: `todo`
Adds a new item to the list of todo items.

Format: `todo n/TODO_NAME d/DEADLINE`

* The `DEADLINE` can be in a natural language format.
* The `TODO_NAME` cannot contain punctuation.  

Example of usage: 

`todo n/Write the rest of the User Guide d/next week`

`todo n/Refactor the User Guide to remove passive voice d/13/04/2020`

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: {your answer here}

## Command Summary

| Action | Format |
| --- | --- |
| Todo | `todo DESCRIPTION` |
| Deadline | `deadline DESCRIPTION /by DUE_DATE_TIME` |
| Event | `event DESCRIPTION /from START_DATE_TIME /to END_DATE_TIME` |
| Mark | `mark INDEX` |
| Unmark | `unmark INDEX` |
| Delete | `delete INDEX` |
| Find | `find QUERY` |
| Date | `date QUERY_DATE` |
| List | `list` |
| Abort | `abort` |
