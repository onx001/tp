package chessmaster.commands;

public class AbortCommand extends Command {

    public static final String ABORT_COMAMND_STRING = "abort";

    private static final String ABORT_MESSAGE = "Exiting program... Thanks for playing!";

    @Override
    public CommandResult execute() {
        return new CommandResult(ABORT_MESSAGE);
    }

    public static boolean isAbortCommand(Command command) {
        return command instanceof AbortCommand;
    }

}
