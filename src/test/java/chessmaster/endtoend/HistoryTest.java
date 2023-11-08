package chessmaster.endtoend;

import chessmaster.ConsoleCapture;
import chessmaster.game.ChessBoard;
import chessmaster.game.Color;
import chessmaster.game.Game;
import chessmaster.user.CPU;
import chessmaster.user.Human;
import chessmaster.storage.Storage;
import chessmaster.ui.TextUI;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import static chessmaster.ConsoleCapture.readExpectedOutputFromFile;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * This class contains all tests related to gameplay i.e. testing real gameplay scenarios and command usage.
 * NOTE: THIS CLASS IS CURRENTLY NOT REALLY WORKING. You can only have one test at a time :')
 * BUG FIX NEEDED.
 */
public class HistoryTest {

    Storage storage;

    @BeforeEach
    public void setUp() {
        // Create temporary storage file just for tests
        try {
            storage = new Storage(File.createTempFile("testingStorage", ".txt").getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    public void tearDown() {
        if (storage != null) {
            File file = new File(storage.getFilePath());
            if (file.exists() && file.delete()) {
                System.out.println("Storage file deleted successfully");
            } else {
                System.err.println("Failed to delete storage file");
            }
        }
    }

    private void giveSystemTestInput(String testInput) {
        ByteArrayInputStream in = new ByteArrayInputStream(testInput.getBytes());
        System.setIn(in);
    }

//    @Test
//    public void historyCommand_twoMovesWhiteStarts() {
//        // Convert user input string to an InputStream and tell Java to use it as the input
//        String testInput = "move a2 a3\n" + "history\n" + "abort\n";
//        giveSystemTestInput(testInput);
//
//        // Need to create TextUI() after setting System input stream
//        TextUI ui = new TextUI();
//
//        // Create a new board and game with your desired testing preferences
//        ChessBoard board = new ChessBoard(Color.WHITE);
//        Human human = new Human(Color.WHITE, board);
//        CPU cpu = new CPU(Color.BLACK, board);
//        Game game = new Game(Color.WHITE, Color.WHITE, board, storage, ui, 1, human, cpu);
//
//        ConsoleCapture consoleCapture = new ConsoleCapture();
//        consoleCapture.startCapture();
//
//        // Run the game. This will automatically use the `testInput` string as user input
//        game.run();
//
//        consoleCapture.stopCapture();
//
//        // Compare captured output with expected output and assert
//        String capturedOutput = consoleCapture.getCapturedOutput();
//        String expectedOutput = readExpectedOutputFromFile("src/test/resources/historyCommand_twoMovesWhiteStarts.txt");
//        assertEquals(expectedOutput, capturedOutput);
//    }

}
