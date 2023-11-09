package chessmaster;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ConsoleCapture {
    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;

    public void startCapture() {
        outputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
    }

    public void stopCapture() {
        System.setOut(originalOut);
    }

    public String getCapturedOutput() {
        return outputStream.toString();
    }

    public static String readExpectedOutputFromFile(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            throw new RuntimeException("Failed to read expected output from file.", e);
        }
    }
}
