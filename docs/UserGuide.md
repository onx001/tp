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
        - [Legend: `legend`](#view-pieces-representation-legend)
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

Next, you can choose the difficulty of the AI you will be pitched against! The current supported difficulty levels range from 1 to 4. Please enter the number corresponding to your preferred difficulty level.
```
Choose your difficulty level! [1/2/3/4]

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
  
<pre><code>
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
</code></pre>

</td>
<td>

<pre><code>

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
</code></pre>

</td>
</tr>
</table>

## Ending the game

The game will automatically end and a victor be declared when one side has been checkmated (i.e. their king is in check, 
and there is no possible escape), or if their king has been captured.

## Features 

### Make a move

To make a move, simply enter the coordinate of the piece to be moved, followed by the coordinates it is to be moved to.

Format: `[column][row] [column][row]`

Examples: 
- `a2 a4`
- `b3 g6`

ChessMaster also automatically checks if the move was valid and legal before it is executed, and shows the following error
message in the case of an invalid move:

`Oops, that move isn't valid!`


### Show available moves: `moves`

Shows the available moves for a piece on the board. This command can be used for both yours and the enemy's pieces.

Format: `moves [column][row]`

Example: 
```
moves e5
                 (a) (b) (c) (d) (e) (f) (g) (h)
                _________________________________
            (8) | R | N | B | Q | K | B | N | R | (8)
                _________________________________
            (7) | P | P | P |[ ]|   |[P]| P | P | (7)
                _________________________________
            (6) |   |   |[ ]| P |   |   |[ ]|   | (6)
                _________________________________
            (5) |   |   |   |   |{n}|   |   |   | (5)
                _________________________________
            (4) |   |   |[ ]|   |   |   |[ ]|   | (4)
                _________________________________
            (3) |   |   |   |[ ]|   |[ ]|   |   | (3)
                _________________________________
            (2) | p | p | p | p | p | p | p | p | (2)
                _________________________________
            (1) | r | n | b | q | k | b |   | r | (1)
                _________________________________
                 (a) (b) (c) (d) (e) (f) (g) (h)

_________________________________________________________________

Available coordinates for Knight at e5: 
f7 d7 f3 d3 g6 g4 c6 c4 
_________________________________________________________________
```

### Show current chess board: `show`

Shows the current state of the chess board.

Format: `show`

### Show chess rules: `rules`

Obtain a quick refresher on the rules of chess

Format: `rules`

### Show commands: `help`

Show a list of commands and what they do

Format: `help`

### View pieces representation: `legend` 

Display a legend that explains the piece representations

Format: `legend`

### Saving and loading games

Every time a turn ends, ChessMaster will save the current state of the game. When ChessMaster is restarted, the
user will be prompted to choose whether to load the saved game or begin a new one. ChessMaster only supports a single
saved game, and starting a new one will delete the existing save file.

### Aborting game: `abort`

Exit the ChessMaster application.

Format: `abort`

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: You can navigate to your root folder, and find the file `data/ChessMaster.txt`. Transfer the file to your other computer,
and find the `data` folder in the ChessMaster install folder in the other computer. Copy the file into the folder. Start ChessMaster,
and type `y` when prompted to load a saved game.

**Q**: How do I play with another person?

**A**: Currently, ChessMaster does not support multiplayer. 

**Q**: Can we play timed games?

**A**: ChessMaster does not come with an internal timer. However, you are able to use your own chess timer or stopwatch 
to simulate timed games.

## Command Summary

| Action        | Format                        |
|---------------|-------------------------------|
| Move          | `[column][row] [column][row]` |
| Show moves    | `moves [column][row]`         |
| Show board    | `show`                        |
| Rules         | `rules`                       |
| Help          | `help`                        |
| Pieces legend | `legend`                      |
| Abort         | `abort`                       |

