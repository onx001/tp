package chessmaster.commands;

public class HelpCommand extends Command {

    public static final String HELP_COMAMND_STRING = "help";

    private static final String[] HELP_STRINGS = { 
        "Seems like you need some help, here are the following commands to play: \n" +
        "Move piece - Input coordinate of piece, followed by coordinate to move to \n" +
        "   Format: [column][row] [column][row] \n" +
        "   E.g. a2 a3 \n" +
        "Abort game - Exit programme \n" +
        "   Format: abort \n" +
        "Obtain rules - Obtain a quick refresher on the rules of chess \n" +
        "   Format: rules \n"
    };

    @Override
    public CommandResult execute() {
        return new CommandResult(HELP_STRINGS);
    }

}
