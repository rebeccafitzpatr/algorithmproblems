import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MazeTest {
    private final InputStream originalSystemIn = System.in;
    private final PrintStream originalSystemOut = System.out;
    private ByteArrayInputStream simulatedInput;
    private ByteArrayOutputStream simulatedOutput;

    @Before
    public void setUp() {
        // Set up input and output streams for testing
        simulatedOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(simulatedOutput));
    }

    @After
    public void tearDown() {
        // Restore original input and output streams
        System.setIn(originalSystemIn);
        System.setOut(originalSystemOut);
    }

    @Test
    public void testMain() {
        // Prepare the simulated input
        String input = "4 24\n" +
            "5 6\n" + "2 3\n" + "9 13\n" + "10 11\n" +
            "5 9\n" + "3 7\n" + "12 13\n" + "0 1\n" +
            "11 15\n" + "14 15\n" + "4 8\n" + "10 14\n" +
            "8 9\n" + "6 10\n" + "1 2\n" + "8 12\n" +
            "7 11\n" + "2 6\n" + "0 4\n" + "4 5\n" +
            "1 5\n" + "13 14\n" + "9 10\n" + "6 7\n";

        // Prepare the expected maze
        String expectedMaze =
            "+-+-+-+-+\n" +
            "|       |\n" +
            "+-+-+-+ +\n" +
            "| |   | |\n" +
            "+ + + + +\n" +
            "|   |   |\n" +
            "+-+ +-+ +\n" +
            "|   |   |\n" +
            "+-+-+-+-+\n";

        // Set the simulated input stream
        simulatedInput = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(simulatedInput);

        // Call the main method of your program
        Maze.main(new String[0]);

        // Capture the outputted maze
        String outputMaze = simulatedOutput.toString();

        // Assert that the actual output matches the expected output
            assertEquals(expectedMaze, outputMaze);
    }
}
      
      
      
      
