# Project: ChessMaster v2.1

ChessMaster is a desktop application targeted at chess beginners 
for them to be able to learn chess on the go. Users interact with it using the CLI,
and its simplicity means that even low-end machines should be able to run it.

### Code contributions: [RepoSense Link](https://nus-cs2113-ay2324s1.github.io/tp-dashboard/?search=ken-ruster&breakdown=false&sort=groupTitle%20dsc&sortWithin=title&since=2023-09-22&timeframe=commit&mergegroup=&groupSelect=groupByRepos)

Given below are my contributions to the project:
### **New Feature**: Move class
- A class representing a move to be made.
- Includes the coordinates from and to of the move, as well as the piece involved to check for validity later.
### **New Feature**: Checking for the validity of the save file.
- Justification: To make sure that the move history and current board state match. Prevents the user from making 
  illegal moves via changing the save file.
- Highlights: The implementation required a revision to the structure of the saved file. 
  It required implementing a `PromoteMove` class as well to store possible promotions.
- How it works:
  - Move history is stored in the .txt file
  - When the game is loaded, the file reads the moves of the human and CPU, and merges them to form a single array.
  - Each of the moves in the array are parsed and executed in the exact order they were stored in.
  - The board it was executed on then is passed into the Game to be run after being checked against the saved board state.
  - By doing this, we are able to ensure the History, Stepback and Captured commands are able to work normally after a saved game is loaded.
### **New feature**: Displaying valid moves for a piece
- Justification: New players may not be able to tell how certain pieces move.
  This feature assists them in learning how the game works in an accessible manner.
- Highlights: Doing this posed a challenge on how best to display the different possibilities for moves
  , eg capturable pieces, current position of the piece and empty tiles it could move to.
  Some difficulty arose while learning to go formatting for text highlighting which works
  for both Windows and Mac.
### **New feature**: Handling promotion
- Justification: Promotion is an essential mechanic of Chess.
- Highlights: Handling promotion required extensive modification of several classes relating to game function.
- How it works:
  - Game checks for pieces able to promoted at the end of each move
  - User is prompted to promote a piece, and the input is parsed to return a new piece
  - The pawn to be promoted is replaced with the new piece
### **Project Management**:
- Set up GitHub organisation and repo
- Managed repo branch security settings
- Maintained issue tracker
- Managed bug testing and checkStyle adherence for releases `1.0` and `2.0`
### **Enhancements to existing features**:
- Added additional tests for existing features, including:
  - `parsePromote`
  - `ShowMovesCommand`
  - `executeSavedMoves`, `loadHumanMoves()` and `loadCPUMoves()` in `Storage`
  - `executeMoveArray` in `ChessBoard`
### **Documentation**:
- Developer Guide:
  - Added documentation for the `Parser` and `Command` subclasses
  - Added sequence diagram for move parsing
  - Added class diagram describing `Parser` and `Command`
- User Guide:
  - Added documentation for the Features section
  - Updated the Command Summary
  - Added and wrote the FAQ section
