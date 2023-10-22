package chessmaster.ui;

public class UiMessages {

    public static final String[] WELCOME_MESSAGE = {
        "Welcome to: ",
        "    ________                      __  ___           __           ",
        "   / ____/ /_  ___  __________   /  |/  /___ ______/ /____  _____",
        "  / /   / __ \\/ _ \\/ ___/ ___/  / /|_/ / __ `/ ___/ __/ _ \\/ ___/",
        " / /___/ / / /  __(__  |__  )  / /  / / /_/ (__  ) /_/  __/ /    ",
        " \\____/_/ /_/\\___/____/____/  /_/  /_/\\__,_/____/\\__/\\___/_/     ",
    };

    public static final String EXIST_PREV_GAME_MESSAGE = "Looks like you have a previous chess game ongoing. Would you like to continue? [y/n]";
    public static final String CHOOSE_PLAYER_COLOR_MESSAGE = "Choose your starting color to start new game! [b/w]";

    public static final String PROMPT_PROMOTE_MESSAGE = "You can promote the pawn at %s! \n" +
            "Please choose what to promote it to:";
    public static final String PROMPT_PROMOTE_INVALID_MESSAGE = "Oops, we couldn't promote your piece!";
}
