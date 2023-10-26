# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

{Describe the design and implementation of the product. Use UML diagrams and short code snippets where applicable.}

### Architecture

### Minimax algorithm

The minimax algorithm is used to determine the best move for the AI to make. It is a recursive algorithm that works by alternatingly minimising opponent scores and maximising CPU scores. The algorithm is implemented in the `Minimax` class.

### Storage Component
**API:**

Below is a class diagram representing the Storage class.
The Storage component is responsible for handling the storage and retrieval of chess game state.
![](StorageClass.png)

![](StorageSequence.png)

* Creates the necessary parent directories for the file and the file itself if they don't exist 
* Saves the current state of the ChessBoard to the file. It includes the player's color information and current player's turn as the first line and the state of the chessboard in an 8x8 format.
* Allows resetting the game by clearing the contents of the file.
* Loads the state of the chessboard from the file by constructing a 2D array of ChessTile objects.



## Product scope
### Target user profile

{Describe the target user profile}

### Value proposition

{Describe the value proposition: what problem does it solve?}

## User Stories

|Version| As a ... | I want to ... | So that I can ...|
|--------|----------|---------------|------------------|
|v1.0|new user|see usage instructions|refer to them when I forget how to use the application|
|v1.0|player|only move valid moves|play chess properly|
|v1.0|new user|start a new game|play chess multiple times|
|v1.0|player|see the current state of the chess board on every turn|think about what move to play|
|v1.0|player|tell which symbol represents which piece|know what is where|
|v1.0|player|specify move coordinates|move the piece I want how I want it|
|v1.0|player|promote pieces when the option is available|play extended games properly|
|v1.0|player|have the option to abort the game|leave the game when I no longer want to play|
|v2.0|user|find a to-do item by name|locate a to-do without having to go through the entire list|
{more to be added}


## Non-Functional Requirements

1. Should work on any mainstream OS as long as it has Java 11 or above installed. 
2. A user with a basic understanding of chess should be able to navigate the game without difficulty. 
{More to be added}


## Glossary

* *glossary item* - Definition

## Instructions for manual testing


{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}
