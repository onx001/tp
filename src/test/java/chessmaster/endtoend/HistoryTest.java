package chessmaster.endtoend;

import chessmaster.ConsoleCapture;
import chessmaster.game.ChessBoard;
import chessmaster.game.Color;
import chessmaster.game.Game;
import chessmaster.storage.Storage;
import chessmaster.ui.TextUI;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static chessmaster.ConsoleCapture.readExpectedOutputFromFile;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class contains all tests related to gameplay i.e. testing real gameplay scenarios and command usage.
 * Also known as "end-to-end" tests.
 *
 */
public class HistoryTest {

    ChessBoard board;
    Storage storage;
    TextUI ui;

    ConsoleCapture consoleCapture;

    @BeforeEach
    public void setup() {
        // Create temporary storage file just for tests
        String filepath = "testingStorage.txt";
        File file = new File(filepath);
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        this.storage = new Storage(filepath);

        consoleCapture = new ConsoleCapture();
        consoleCapture.startCapture();
    }

    @AfterEach
    public void shutdown() {
        String filepath = "testingStorage.txt";
        File file = new File(filepath);
        file.delete();
    }

    @Test
    public void historyCommand_twoMovesWhiteStarts() {
        // Convert user input string to an InputStream and tell Java to use it as the input
        String testInput = "move a2 a3\n" + "history\n" + "abort\n";
        ByteArrayInputStream in = new ByteArrayInputStream(testInput.getBytes());
        System.setIn(in);

        // Need to create TextUI() after setting System input stream
        this.ui = new TextUI();

        // Create a new board and game with your desired testing preferences
        this.board = new ChessBoard(Color.WHITE);
        Game game = new Game(Color.WHITE, Color.WHITE, board, this.storage, this.ui, 1);

        // Run the game. This will automatically use the `testInput` string as user input
        game.run();

        // Compare captured output with expected output and assert
        consoleCapture.stopCapture();
        String capturedOutput = consoleCapture.getCapturedOutput();
        String expectedOutput = readExpectedOutputFromFile("src/test/resources/historyCommand_twoMovesWhiteStarts.txt");

        assertEquals(expectedOutput, capturedOutput);
    }

    @Test
    public void historyCommand_noMoves() {
        String testInput = "history\n" + "abort\n";

        ByteArrayInputStream in = new ByteArrayInputStream(testInput.getBytes());
        System.setIn(in);
        this.ui = new TextUI();
        this.board = new ChessBoard(Color.WHITE);
        Game game = new Game(Color.WHITE, Color.WHITE, board, this.storage, this.ui, 1);

        game.run();

        consoleCapture.stopCapture();
        String capturedOutput = consoleCapture.getCapturedOutput();
        String expectedOutput = readExpectedOutputFromFile("src/test/resources/historyCommand_noMoves.txt");
        assertEquals(expectedOutput, capturedOutput);
    }


}
