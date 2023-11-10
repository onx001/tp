# Project: ChessMaster v2.1

ChessMaster is a desktop application targeted at chess beginners 
for them to be able to learn chess on the go. Users interact with it using the CLI,
and its simplicity means that even low-end machines should be able to run it.

Given below are my contributions to the project:
- **New Feature**: Checking for the validity of the save file.
  - Justification: To make sure that the move history and current board state match. Prevents the user from making 
    illegal moves via changing the save file.
  - Highlights: The implementation required a revision to the structure used to save the file. 
    It required implementing a `PromoteMove` class as well to store possible promotions.
- **New feature**: Displaying valid moves for a piece
  - Justification: New players may not be able to tell how certain pieces move.
    This feature assists them in learning how the game works in an accessible manner.
  - Highlights: Doing this posed a challenge on how best to display the different possibilities for moves
    , eg capturable pieces, current position of the piece and empty tiles it could move to.
    Some difficulty arose while learning to go formatting for text highlighting which works
    for both Windows and Mac.
- **New feature**: Handling promotion
  - Justification: Promotion is an essential mechanic of Chess.
  - Highlights: Handling promotion required extensive modification of several classes relating to game function.
- **Project Management**:
  - Managed bug testing and checkStyle adherence for releases `1.0` and `2.0`
- **Enhancements to existing features**:
  - Added additional tests for existing features
- **Documentation**:
  - Developer Guide:
    - Added documentation for the `Parser` and `Command` subclasses
  - User Guide:
    - Added documentation for the Features section
    - Updated the Command Summary
    - Added the FAQ section
