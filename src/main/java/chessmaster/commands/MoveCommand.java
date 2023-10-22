package chessmaster.commands;

import chessmaster.exceptions.ParseCoordinateException;
import chessmaster.game.Coordinate;

public class MoveCommand extends Command {

    public static final String MOVE_COMAMND_STRING = "move";
    private static final String MOVE_PIECE_MESSAGE = "Moving %s to %s";

    private String userInput;

    public MoveCommand(String inputString) {
        this.userInput = inputString;
    }

    /**
     * Executes the command based on user input, which is expected to consist of two algebraic 
     * coordinate strings separated by whitespace.
     *
     * @return A CommandResult object containing the result of the command.
     * @throws ParseCoordinateException If the user input cannot be parsed into two coordinate objects.
     */
    @Override
    public CommandResult execute() throws ParseCoordinateException {

        String[] coordinateStrings = userInput.split("\\s+", 2);
        if (coordinateStrings.length != 2) {
            throw new ParseCoordinateException();
        }

        String fromString = coordinateStrings[0];
        String toString = coordinateStrings[1];

        Coordinate from = Coordinate.parseAlgebraicCoor(fromString);
        Coordinate to = Coordinate.parseAlgebraicCoor(toString);

        return new CommandResult(String.format(MOVE_PIECE_MESSAGE, from, to));
    }

}
